package com.leolibrary.ui.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.leolibrary.R;


/**
 * Created by leo on 16/5/14.
 * 基类
 */
public abstract class BaseActivity extends AppCompatActivity implements InitRes {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforInitView();
        initToolbar();
        initView();
        initListener();
        initData();
    }

    public void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(getClass().getSimpleName());
            toolbar.setTitleTextColor(getResources().getColor(R.color.toolar_text_color));
            toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
    }
}
