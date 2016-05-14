package com.leo.example.ui.activity;

import android.support.v4.view.ViewPager;

import com.leo.example.R;
import com.leo.example.ui.adapter.page.ShadowPageAdapter;
import com.leo.tablayout.SlidingTabLayout;
import com.leolibrary.ui.base.activity.BaseActivity;

/**
 * Created by leo on 16/5/14.
 * 画阴影效果Activity
 */
public class ShadowActivity extends BaseActivity {
    SlidingTabLayout tabLayout;
    ViewPager viewPager;
    private ShadowPageAdapter shadowPageAdapter;

    @Override
    public void beforInitView() {
        setContentView(R.layout.activity_shadow);
    }

    @Override
    public void initView() {
        tabLayout = (SlidingTabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.vp_shadow);
        shadowPageAdapter = new ShadowPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(shadowPageAdapter);
        tabLayout.setCustomTabView(R.layout.item_tab, android.R.id.text1);
        tabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorPrimary));
        tabLayout.setDistributeEvenly(true);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
