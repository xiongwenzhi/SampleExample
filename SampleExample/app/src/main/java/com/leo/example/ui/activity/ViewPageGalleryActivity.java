package com.leo.example.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.view.CustomViewPager;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.leo.example.R;
import com.leo.example.databinding.ActivityViewpagerGalleryBinding;
import com.leo.example.dto.ListDTO;
import com.leo.example.info.SubjectsInfo;
import com.leo.example.ui.adapter.page.GalleryPageAdapter;
import com.leo.example.ui.animation.ZoomOutPageTransformer;
import com.leo.example.ui.dialog.LoadingDialog;
import com.leo.example.util.DouBanApiUtil;
import com.leolibrary.ui.base.activity.BaseActivity;

import java.util.ArrayList;

import rx.functions.Action1;


/**
 * Viewpager 实现Gallery画廊效果
 **/
public class ViewPageGalleryActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private ActivityViewpagerGalleryBinding binding;
    private GalleryPageAdapter adapter;
    private ArrayList<SubjectsInfo> list = new ArrayList<>();

    @Override
    public void beforInitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_viewpager_gallery);
    }

    @Override
    public void initView() {
        //初始化ViewPager
        adapter = new GalleryPageAdapter(list, ViewPageGalleryActivity.this, R.layout.item_view);
        binding.viewPager.setOffscreenPageLimit(3);
        binding.viewPager.addOnPageChangeListener(this);
        binding.viewPager.setPageTransformer(false, new ZoomOutPageTransformer());
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
     * 获取豆瓣Api数据
     */
    private void LoadData() {
        DouBanApiUtil.LoadRepoData(this, new Action1<ListDTO<SubjectsInfo>>() {
            @Override
            public void call(ListDTO<SubjectsInfo> subjectsInfoListDTO) {
                list.addAll(subjectsInfoListDTO.getList());
                binding.viewPager.setAdapter(adapter);
            }
        });
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
