package com.leolibrary.ui.base.activity;

import android.databinding.ViewDataBinding;

/**
 * Created by leo on 16/5/14.
 */
public interface InitRes<V extends ViewDataBinding> {
    V beforInitView();

    void initView();

    void initData();


    void initListener();
}
