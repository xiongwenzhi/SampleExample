package com.leo.example.ui.activity;

import android.databinding.DataBindingUtil;

import com.leo.example.R;
import com.leo.example.databinding.ActivityBitmapBinding;
import com.leolibrary.ui.base.activity.BaseActivity;

/**
 * Created by leo on 16/7/1.
 */
public class BitmapActivity extends BaseActivity<ActivityBitmapBinding> {

    @Override
    public ActivityBitmapBinding beforInitView() {
        return DataBindingUtil.setContentView(this, R.layout.activity_bitmap);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
