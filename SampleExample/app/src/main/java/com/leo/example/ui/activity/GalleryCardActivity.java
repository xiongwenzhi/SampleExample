package com.leo.example.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.view.CustomViewPager;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;

import com.leo.example.Constans;
import com.leo.example.R;
import com.leo.example.TestUtil.TestDataUtil;
import com.leo.example.databinding.ActivityCardGalleryBinding;
import com.leo.example.ui.adapter.page.PagerViewAdapter;
import com.leo.example.ui.animation.ZoomCardPageTransformer;
import com.leo.example.ui.viewmodel.Image3DModel;
import com.leolibrary.ui.base.activity.BaseActivity;

/**
 * Created by leo on 16/5/20.
 */
public class GalleryCardActivity extends BaseActivity<ActivityCardGalleryBinding> implements CustomViewPager.OnPageChangeListener {
    private PagerViewAdapter adapter;
    private ActivityCardGalleryBinding binding;

    @Override
    public ActivityCardGalleryBinding beforInitView() {
        return DataBindingUtil.setContentView(this, R.layout.activity_card_gallery);
    }

    @Override
    public void initView() {
        //初始化ViewPager
        boolean is3D = getIntent().getBooleanExtra(Constans.IS_3D, false);
        adapter = new PagerViewAdapter(this);
        binding.viewPager.addOnPageChangeListener(this);
        binding.viewPager.setPageTransformer(true, new ZoomCardPageTransformer(binding.viewPager, is3D));
        binding.viewPager.setOffscreenPageLimit(5);
    }

    @Override
    public void initData() {
        LoadData();
    }

    @Override
    public void initListener() {
        binding.llLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return binding.viewPager.dispatchTouchEvent(event);
            }
        });
    }

    /**
     * 添加测试数据
     */
    private void LoadData() {
        adapter.addAll(Image3DModel.getImageModel(TestDataUtil.images, R.layout.item_card_gallery_view));
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setCurrentItem(adapter.size() / 2);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            binding.llLayout.invalidate();
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
