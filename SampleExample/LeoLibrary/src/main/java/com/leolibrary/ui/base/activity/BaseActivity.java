package com.leolibrary.ui.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by leo on 16/5/14.
 * 基类
 */
public abstract class BaseActivity extends AppCompatActivity implements InitRes {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforInitView();
        initView();
        initListener();
        initData();
    }
}
