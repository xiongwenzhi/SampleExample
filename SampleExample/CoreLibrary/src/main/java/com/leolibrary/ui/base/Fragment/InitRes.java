package com.leolibrary.ui.base.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by leo on 16/5/14.
 */
public interface InitRes {
    View createView(LayoutInflater inflater, ViewGroup container);

    int getLayoutId();

    void initView();

    void initData();


    void initListener();
}
