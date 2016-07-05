package com.leolibrary.callback;


import android.databinding.ViewDataBinding;

import com.leolibrary.ui.base.viewhodler.BaseDataViewHodler;

/**
 * Created by leo on 16/5/19.
 */
public interface BindingListCallback<T, B extends ViewDataBinding> extends ListCallback<T> {
    void onBindListener(BaseDataViewHodler<B> b,int position, T t);
}
