package com.leo.example.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.leo.example.R;
import com.leo.example.databinding.ActivityZoomImageBinding;
import com.leo.example.dto.ListDTO;
import com.leo.example.info.ImageSizeInfo;
import com.leo.example.info.SubjectsInfo;
import com.leo.example.ui.adapter.list.ShadowListAdapter;
import com.leo.example.ui.adapter.page.ZoomImagePagerAdapter;
import com.leo.example.ui.viewmodel.ItemSubjectsInfoViewModel;
import com.leo.example.ui.viewmodel.PhotoZoomViewModel;
import com.leo.example.util.DouBanApiUtil;
import com.leo.example.util.LocationUtils;
import com.leo.example.util.RatioUtils;
import com.leo.example.wdight.SpacesItemDecoration;
import com.leolibrary.ui.base.activity.BaseActivity;
import com.leolibrary.utils.Dimens;
import com.leolibrary.utils.SystemUtil;
import com.leolibrary.utils.ViewUtils;
import com.nineoldandroids.view.ViewHelper;

import java.util.List;

import rx.functions.Action1;

/**
 * Created by leo on 16/9/10.
 * 仿朋友圈图片查看效果
 */
public class ZoomImageActivity extends BaseActivity<ActivityZoomImageBinding> {
    private static final int mDuration = 400;
    private ZoomImagePagerAdapter zoomImagePagerAdapter;
    private ShadowListAdapter shadowListAdapter;
    private DecelerateInterpolator interpolator;
    private int pading = 0;
    private int imageHeight = 0;
    private int screenWidth;
    private int mCurrentItem;
    private Rect finalBounds;
    private Point globalOffset;
    private AnimatorSet animator;
    private int mRow = 3;//列数

    @Override
    public ActivityZoomImageBinding beforInitView() {
        return DataBindingUtil.setContentView(this, R.layout.activity_zoom_image);
    }

    @Override
    public void initView() {
        //用于测量View在屏幕中的坐标
        finalBounds = new Rect();
        globalOffset = new Point();

        //根据屏幕尺寸及item边距,动态适配item宽高,保证item在不同的分辨率下都为正方形
        pading = Dimens.getDimensionPixelOffset(R.dimen.dp_4);
        screenWidth = SystemUtil.getScreenWidth(this);
        imageHeight = (screenWidth - pading * 2 * 4) / mRow;

        //init ViewPager
        zoomImagePagerAdapter = new ZoomImagePagerAdapter(this);
        binding.vpPager.setAdapter(zoomImagePagerAdapter);
        binding.vpPager.addOnPageChangeListener(changeAdapter);
        binding.vpPager.setOffscreenPageLimit(3);

        //init RecyclerView
        shadowListAdapter = new ShadowListAdapter(this);
        binding.rvView.addItemDecoration(new SpacesItemDecoration(pading, mRow));
        binding.rvView.setLayoutManager(new GridLayoutManager(this, mRow));
        binding.rvView.setAdapter(shadowListAdapter);

        //init interpolator
        interpolator = new DecelerateInterpolator();
    }

    @Override
    public void initData() {
        DouBanApiUtil.LoadRepoData(this, new Action1<ListDTO<SubjectsInfo>>() {
            @Override
            public void call(ListDTO<SubjectsInfo> subjectsInfoListDTO) {
                shadowListAdapter.addAll(ItemSubjectsInfoViewModel.toViewModel(subjectsInfoListDTO.getList(), R.layout.item_rv_image, imageHeight, actionShow));
                shadowListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initListener() {

    }

    /**
     * 显示大图预览
     */
    private Action1<Integer> actionShow = new Action1<Integer>() {
        @Override
        public void call(Integer integer) {
            showPhotoView(shadowListAdapter.getData(), integer.intValue(), ViewUtils.getViewGlobalRect(shadowListAdapter.getItemView(integer.intValue())));
        }
    };

    /**
     * 隐藏图片预览界面
     *
     * @return
     */
    private Action1<View> actionHide = new Action1<View>() {
        @Override
        public void call(View view) {
            Rect originBound = ViewUtils.getViewGlobalRect(shadowListAdapter.getItemView(mCurrentItem));
            hidePhotoAnimator(originBound);
        }
    };


    /**
     * 添加需要显示的图片
     *
     * @param photoData
     * @param position
     * @param rect
     */
    public void showPhotoView(List<ItemSubjectsInfoViewModel> photoData, int position, Rect rect) {
        zoomImagePagerAdapter = new ZoomImagePagerAdapter(this);
        zoomImagePagerAdapter.addAll(PhotoZoomViewModel.toViewModel(photoData, actionHide));
        binding.vpPager.setAdapter(zoomImagePagerAdapter);
        binding.vpPager.addOnPageChangeListener(changeAdapter);
        binding.vpPager.setCurrentItem(mCurrentItem = position);
        binding.vpPager.setLocked(zoomImagePagerAdapter.getCount() == 1);
        binding.flyLayout.getGlobalVisibleRect(finalBounds, globalOffset);
        showPhotoAnimator(rect);
    }


    /**
     * 显示照片查看界面
     *
     * @param startBounds
     */
    private void showPhotoAnimator(Rect startBounds) {
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);
        ViewHelper.setAlpha(binding.viewBg, 1);
        binding.flyLayout.setVisibility(View.VISIBLE);
        float ratio = RatioUtils.getShowRatio(startBounds, finalBounds);
        calculateLocationRect(startBounds, finalBounds, ratio);
        binding.vpPager.setPivotX(0);
        binding.vpPager.setPivotY(0);
        animator = new AnimatorSet();
        animator.play(ObjectAnimator.ofFloat(binding.vpPager, View.X, startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(binding.vpPager, View.Y, startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(binding.vpPager, View.SCALE_X, ratio, 1f))
                .with(ObjectAnimator.ofFloat(binding.vpPager, View.SCALE_Y, ratio, 1f));
        animator.setDuration(mDuration);
        animator.setInterpolator(interpolator);
        animator.addListener(showAnimatorListener);
        animator.start();
    }


    /**
     * 隐藏照片查看界面
     *
     * @param originBound
     */
    private void hidePhotoAnimator(Rect originBound) {
        //如果展开动画没有展示完全就关闭,执行退出动画
        if (animator != null) {
            animator.cancel();
        }
        //将背景设为透明
        ViewHelper.setAlpha(binding.viewBg, 0);
        //测量View位置,获取坐标参数
        binding.flyLayout.getGlobalVisibleRect(finalBounds, globalOffset);
        originBound.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);
        //将ViewPager的位置移动到屏幕中心
        binding.vpPager.setPivotX(0);
        binding.vpPager.setPivotY(0);
        animator = new AnimatorSet();
        float ratio = getHideRatio(originBound, zoomImagePagerAdapter.getImageSizeInfo(mCurrentItem));
        calculateLocationRect(originBound, finalBounds, ratio);
        animator.play(ObjectAnimator.ofFloat(binding.vpPager, View.X, originBound.left))
                .with(ObjectAnimator.ofFloat(binding.vpPager, View.Y, originBound.top))
                .with(ObjectAnimator.ofFloat(binding.vpPager, View.SCALE_X, 1f, ratio))
                .with(ObjectAnimator.ofFloat(binding.vpPager, View.SCALE_Y, 1f, ratio));
        animator.setDuration(mDuration);
        animator.setInterpolator(interpolator);
        animator.addListener(hideAnimatorListener);
        animator.start();
    }

    /**
     * show AnimatorListener
     */
    private AnimatorListenerAdapter showAnimatorListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            getSystemBarTintManager().setStatusBarTintDrawable(getResources().getDrawable(R.color.black));
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            animator = null;
        }
    };


    /**
     * show AnimatorListener
     */
    private AnimatorListenerAdapter hideAnimatorListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationStart(Animator animation) {
            super.onAnimationStart(animation);
            getSystemBarTintManager().setStatusBarTintDrawable(getResources().getDrawable(R.drawable.shape_gradient));
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            onHidePhotoAnimationEnd();
        }
    };


    /**
     * 隐藏图片动画结束回调
     */
    private void onHidePhotoAnimationEnd() {
        animator = null;
        binding.vpPager.clearAnimation();
        binding.flyLayout.setVisibility(View.GONE);
        zoomImagePagerAdapter.onDestoryPhotoView();
        binding.vpPager.removeOnPageChangeListener(changeAdapter);
        zoomImagePagerAdapter.getData().clear();
    }


    /**
     * 根据View的宽高,计算动画及显示位置的偏移量
     *
     * @param startBounds
     * @param finalBounds
     * @param ratio
     */
    private void calculateLocationRect(Rect startBounds, Rect finalBounds, float ratio) {
        if ((float) finalBounds.width() / finalBounds.height() > (float) startBounds.width() / startBounds.height()) {
            float startWidth = ratio * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
            return;
        }
        float startHeight = ratio * finalBounds.height();
        float deltaHeight = (startHeight - startBounds.height()) / 2;
        startBounds.top -= deltaHeight;
        startBounds.bottom += deltaHeight;
        float startWidth = ratio * finalBounds.width();
        float deltaWidth = (startWidth - startBounds.width()) / 2;
        //根据图片所在位置计算位置偏移量
        if (mRow > 1 && LocationUtils.isRight(mCurrentItem, mRow)) {
            startBounds.left -= startWidth - startBounds.width();
        } else if (!LocationUtils.isRight(mCurrentItem, mRow) && !LocationUtils.isLeft(mCurrentItem, mRow) || 3 == 1) {
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        }
    }


    /**
     * 获取隐藏缩放比
     *
     * @param info
     * @param endBounds
     */
    private float getHideRatio(Rect endBounds, ImageSizeInfo info) {
        float ratio;
        //可能出现图片未加载完毕,拿不到图片宽高数据的情况,此时默认拿外层ViewGroup的宽高,保证基本的效果即可
        if (info == null) {
            info = new ImageSizeInfo();
        }
        if (info.getWidth() == 0 || info.getHeight() == 0) {
            info.setWidth(binding.flyLayout.getWidth());
            info.setHeight(binding.flyLayout.getHeight());
        }
        //根据图片显示的尺寸计算,隐藏时的缩放比例
        //为保证缩放效果,以图片宽、高中,以参数小的为计算基准
        float scale = (float) screenWidth / info.getWidth();
        if (info.getWidth() > info.getHeight()) {
            ratio = RatioUtils.getHeightRatio(info, endBounds, scale);
            //如果计算的缩放的最终宽度,要小于RecyclerView中item宽度
            //则重新以图片高度为基准,进行计算
            if (ratio * info.getWidth() * scale < endBounds.width()) {
                ratio = RatioUtils.getWidthRatio(info, endBounds, scale);
            }
            return ratio;
        }
        ratio = RatioUtils.getWidthRatio(info, endBounds, scale);
        //如果计算的缩放的最终高度,要小于RecyclerView中item高度
        //则重新以图片宽度为基准,进行计算
        if (ratio * info.getHeight() * scale < endBounds.height()) {
            ratio = RatioUtils.getHeightRatio(info, endBounds, scale);
        }
        return ratio;
    }


    /**
     * ViewPage滑动监听
     */
    private ViewPager.OnPageChangeListener changeAdapter = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            if (position >= shadowListAdapter.size() || position == mCurrentItem) {
                return;
            }
            mCurrentItem = position;
            //因实现图片归位的动画效果,需要先测量View在屏幕中的坐标,所以需要保证RecyclerView中的item必须显示在屏幕中
            //所以图片预览切换图片时,需要同时滑动RecyclerView
            GridLayoutManager layoutManager = (GridLayoutManager) binding.rvView.getLayoutManager();
            layoutManager.scrollToPositionWithOffset(position, 0);
        }
    };

    @Override
    public void onBackPressed() {
        if (binding.flyLayout.getVisibility() == View.VISIBLE) {
            actionHide.call(null);
            return;
        }
        super.onBackPressed();
    }
}
