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
    private ZoomImagePagerAdapter zoomImagePagerAdapter;
    private ShadowListAdapter shadowListAdapter;
    private int pading = 0;
    private int imageHeight = 0;
    private int screenWidth;
    private int mCurrentItem;
    private Rect finalBounds;
    private Point globalOffset;
    private boolean isShow = false;
    private AnimatorSet curAnimator;

    @Override
    public ActivityZoomImageBinding beforInitView() {
        return DataBindingUtil.setContentView(this, R.layout.activity_zoom_image);
    }

    @Override
    public void initView() {
        imageHeight = (SystemUtil.getScreenWidth(this) - pading * 2 * 4) / 3;
        pading = Dimens.getDimensionPixelOffset(R.dimen.dp_4);
        shadowListAdapter = new ShadowListAdapter(this);
        zoomImagePagerAdapter = new ZoomImagePagerAdapter(this);
        binding.rvView.addItemDecoration(new SpacesItemDecoration(pading, 3));
        binding.rvView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.rvView.setAdapter(shadowListAdapter);
        binding.vpPager.setAdapter(zoomImagePagerAdapter);
        binding.vpPager.addOnPageChangeListener(changeAdapter);
        binding.vpPager.setOffscreenPageLimit(3);
    }

    @Override
    public void initData() {
        finalBounds = new Rect();
        globalOffset = new Point();
        screenWidth = SystemUtil.getScreenWidth(this);
        DouBanApiUtil.LoadRepoData(this, new Action1<ListDTO<SubjectsInfo>>() {
            @Override
            public void call(ListDTO<SubjectsInfo> subjectsInfoListDTO) {
                shadowListAdapter.addAll(ItemSubjectsInfoViewModel.toViewModel(subjectsInfoListDTO.getList(), R.layout.item_rv_image, imageHeight, actionShow()));
                shadowListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initListener() {

    }


    private Action1<Integer> actionShow() {
        return new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                showPhotoView(shadowListAdapter.getData(), integer.intValue(), ViewUtils.getViewGlobalRect(shadowListAdapter.getItemView(integer.intValue())));
            }
        };
    }


    private Action1<View> actionHide() {
        return new Action1<View>() {
            @Override
            public void call(View view) {
                doHidePhotos();
            }
        };
    }

    /**
     * 获取隐藏缩放比
     *
     * @param
     * @param startBounds
     */
    private float getHideRatio(Rect startBounds, ImageSizeInfo info) {
        float ratio;
        if (info == null) {
            info = new ImageSizeInfo();
        }
        if (info.getWidth() == 0 || info.getHeight() == 0) {
            info.setWidth(binding.flyLayout.getWidth());
            info.setHeight(binding.flyLayout.getHeight());
        }
        float scale = (float) screenWidth / info.getWidth();
        if (info.getWidth() > info.getHeight()) {
            ratio = RatioUtils.getHeightRatio(info, startBounds, scale);
            if (ratio * info.getWidth() * scale < startBounds.width()) {
                ratio = RatioUtils.getWidthRatio(info, startBounds, scale);
            }
        } else {
            ratio = RatioUtils.getWidthRatio(info, startBounds, scale);
            if (ratio * info.getHeight() * scale < startBounds.height()) {
                ratio = RatioUtils.getHeightRatio(info, startBounds, scale);
            }
        }
        return ratio;
    }


    /**
     * 添加需要显示的图片
     *
     * @param photoData
     * @param position
     */
    public void showPhotoView(List<ItemSubjectsInfoViewModel> photoData, int position, Rect rect) {
        zoomImagePagerAdapter = new ZoomImagePagerAdapter(this);
        zoomImagePagerAdapter.addAll(PhotoZoomViewModel.toViewModel(photoData, actionHide()));
        binding.vpPager.setAdapter(zoomImagePagerAdapter);
        binding.vpPager.addOnPageChangeListener(changeAdapter);
        binding.vpPager.setCurrentItem(mCurrentItem = position);
        binding.vpPager.setLocked(zoomImagePagerAdapter.getCount() == 1);
        binding.flyLayout.getGlobalVisibleRect(finalBounds, globalOffset);
        ViewHelper.setAlpha(binding.viewBg, 1);
        showPhotoAnimator(rect);
    }


    /**
     * 显示照片查看界面
     *
     * @param startBounds
     */
    private void showPhotoAnimator(Rect startBounds) {
        isShow = true;
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);
        float ratio = RatioUtils.getShowRatio(startBounds, finalBounds);
        intiHideCalculate(startBounds, finalBounds, ratio);
        binding.vpPager.setPivotX(0);
        binding.vpPager.setPivotY(0);
        binding.flyLayout.setVisibility(View.VISIBLE);
        AnimatorSet set = new AnimatorSet();
        set.play(ObjectAnimator.ofFloat(binding.vpPager, View.X, startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(binding.vpPager, View.Y, startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(binding.vpPager, View.SCALE_X, ratio, 1f))
                .with(ObjectAnimator.ofFloat(binding.vpPager, View.SCALE_Y, ratio, 1f));
        set.setDuration(400);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(animatorListener);
        set.start();
    }


    /**
     * AnimatorListener
     */
    private Animator.AnimatorListener animatorListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            animationEnd();
        }

        @Override
        public void onAnimationStart(Animator animation) {
            curAnimator = (AnimatorSet) animation;
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            animationEnd();
        }
    };


    /**
     * 隐藏图片预览
     */
    public void doHidePhotos() {
        Rect originBound;
        originBound = ViewUtils.getViewGlobalRect(shadowListAdapter.getItemView(mCurrentItem));
        hidePhotoAnimator(originBound);
    }


    /**
     * 动画结束执行回调
     */
    private void animationEnd() {
        if (isShow) {
            curAnimator = null;
        } else {
            onHidePhotoAnimationEnd();
        }
    }


    /**
     * 隐藏图片动画结束回调
     */
    private void onHidePhotoAnimationEnd() {
        curAnimator = null;
        binding.vpPager.clearAnimation();
        binding.flyLayout.setVisibility(View.GONE);
        zoomImagePagerAdapter.onDestoryPhotoView();
        binding.vpPager.removeOnPageChangeListener(changeAdapter);
        zoomImagePagerAdapter.getData().clear();
    }

    /**
     * 隐藏图片预览
     */
    private ViewPager.OnPageChangeListener changeAdapter = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position >= shadowListAdapter.size() || position == mCurrentItem) {
                return;
            }
            mCurrentItem = position;
            GridLayoutManager layoutManager = (GridLayoutManager) binding.rvView.getLayoutManager();
            layoutManager.scrollToPositionWithOffset(position, 0);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };

    /**
     * 位置计算
     */
    private void intiHideCalculate(Rect startBounds, Rect finalBounds, float ratio) {
        if ((float) finalBounds.width() / finalBounds.height() > (float) startBounds.width() / startBounds.height()) {
            float startWidth = ratio * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            float startHeight = ratio * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
            float startWidth = ratio * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            if (3 > 1 && LocationUtils.isRight(mCurrentItem, 3)) {
                startBounds.left -= startWidth - startBounds.width();
            } else if (!LocationUtils.isRight(mCurrentItem, 3) && !LocationUtils.isLeft(mCurrentItem, 3) || 3 == 1) {
                startBounds.left -= deltaWidth;
                startBounds.right += deltaWidth;
            }
        }
    }

    /**
     * 隐藏照片查看界面
     *
     * @param originBound
     */
    private void hidePhotoAnimator(Rect originBound) {
        isShow = false;
        //如果展开动画没有展示完全就关闭，那么就停止展开动画进而执行退出动画
        if (curAnimator != null) {
            curAnimator.cancel();
        }
        ViewHelper.setAlpha(binding.viewBg, 0);
        binding.flyLayout.getGlobalVisibleRect(finalBounds, globalOffset);
        originBound.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);
        binding.vpPager.setPivotX(0);
        binding.vpPager.setPivotY(0);
        AnimatorSet set = new AnimatorSet();
        float ratio = getHideRatio(originBound, zoomImagePagerAdapter.getImageSizeInfo(mCurrentItem));
        intiHideCalculate(originBound, finalBounds, ratio);
        set.play(ObjectAnimator.ofFloat(binding.vpPager, View.X, originBound.left).setDuration(100))
                .with(ObjectAnimator.ofFloat(binding.vpPager, View.Y, originBound.top))
                .with(ObjectAnimator.ofFloat(binding.vpPager, View.SCALE_X, 1f, ratio))
                .with(ObjectAnimator.ofFloat(binding.vpPager, View.SCALE_Y, 1f, ratio));
        set.setDuration(400);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(animatorListener);
        set.start();
    }

    @Override
    public void onBackPressed() {
        if (binding.flyLayout.getVisibility() == View.VISIBLE) {
            doHidePhotos();
            return;
        }
        super.onBackPressed();
    }
}
