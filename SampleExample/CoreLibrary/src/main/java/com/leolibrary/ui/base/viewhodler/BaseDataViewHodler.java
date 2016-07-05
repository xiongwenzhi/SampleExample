package com.leolibrary.ui.base.viewhodler;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;


/**
 * Created by leo on 16/6/29.
 * 基类
 */
public class BaseDataViewHodler<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private B binding;

    public BaseDataViewHodler(B binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public B getBinding() {
        return binding;
    }
}
