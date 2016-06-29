package com.leo.example.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewPager;

import com.leo.example.R;
import com.leo.example.databinding.ActivityShadowBinding;
import com.leo.example.ui.adapter.page.ShadowPageAdapter;
import com.leo.tablayout.SlidingTabLayout;
import com.leolibrary.ui.base.activity.BaseActivity;

/**
 * Created by leo on 16/5/14.
 * 阴影效果Activity
 */
public class ShadowActivity extends BaseActivity {
    private ActivityShadowBinding binding;
    private ShadowPageAdapter shadowPageAdapter;

    @Override
    public void beforInitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shadow);
    }

    @Override
    public void initView() {
        shadowPageAdapter = new ShadowPageAdapter(getSupportFragmentManager());
        binding.vpShadow.setAdapter(shadowPageAdapter);
        binding.tabLayout.setCustomTabView(R.layout.item_tab, android.R.id.text1);
        binding.tabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.white));
        binding.tabLayout.setDistributeEvenly(true);
        binding.tabLayout.setViewPager(binding.vpShadow);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
