package com.leo.example.ui.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;

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

    @Override
    public ActivityZoomImageBinding beforInitView() {
        return DataBindingUtil.setContentView(this, R.layout.activity_zoom_image);
    }

    @Override
    public void initView() {
        imageHeight = (SystemUtil.getScreenWidth(this) - pading * 2 * 4) / 3;
        pading = Dimens.getDimensionPixelOffset(R.dimen.dp_4);
        shadowListAdapter = new ShadowListAdapter(this);
        binding.rvView.addItemDecoration(new SpacesItemDecoration(pading, 3));
        binding.rvView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.rvView.setAdapter(shadowListAdapter);
    }

    @Override
    public void initData() {
        finalBounds = new Rect();
        globalOffset = new Point();
        screenWidth = SystemUtil.getScreenWidth(this);
        DouBanApiUtil.LoadRepoData(this, new Action1<ListDTO<SubjectsInfo>>() {
            @Override
            public void call(ListDTO<SubjectsInfo> subjectsInfoListDTO) {
                shadowListAdapter.addAll(ItemSubjectsInfoViewModel.toViewModel(subjectsInfoListDTO.getList(), R.layout.item_rv_image, imageHeight, action1()));
                shadowListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initListener() {

    }


    private Action1<Integer> action1() {
        return new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                showPhotoView(shadowListAdapter.getData(), integer.intValue(), ViewUtils.getViewGlobalRect(shadowListAdapter.getItemView(integer.intValue())));
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
        Log.d("view:info:", info.toString());
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
        zoomImagePagerAdapter.addAll(PhotoZoomViewModel.toViewModel(photoData));
        binding.vpPager.setAdapter(zoomImagePagerAdapter);
        binding.vpPager.setCurrentItem(mCurrentItem = position);
        ViewHelper.setAlpha(binding.flyLayout, 1);
        binding.vpPager.setLocked(zoomImagePagerAdapter.getCount() == 1);
        binding.flyLayout.getGlobalVisibleRect(finalBounds, globalOffset);
        binding.flyLayout.setVisibility(View.VISIBLE);
//        showPhotoAnimator(rect);
    }


}
