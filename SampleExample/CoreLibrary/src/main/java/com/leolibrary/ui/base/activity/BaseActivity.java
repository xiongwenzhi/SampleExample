package com.leolibrary.ui.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.leolibrary.R;
import com.leolibrary.utils.SystemUtil;


/**
 * Created by leo on 16/5/14.
 * 基类
 */
public abstract class BaseActivity extends AppCompatActivity implements InitRes {
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforInitView();
        SystemUtil.setStatusDrawable(this, getResources().getDrawable(R.drawable.shape_gradient));
        initToolbar();
        initView();
        initListener();
        initData();
    }

    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        toolbar.setTitle(getClass().getSimpleName());
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolar_text_color));
        toolbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.shape_gradient));
        if (getLeftMenuIcon() != 0) {
            toolbar.setNavigationIcon(getLeftMenuIcon());
        }
        toolbar.setNavigationOnClickListener(getLeftMenuClick());
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * toolbar icon
     */
    public int getLeftMenuIcon() {
        return R.drawable.ic_back;
    }

    /**
     * toolbar left onclick
     */
    public View.OnClickListener getLeftMenuClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        };
    }


}
