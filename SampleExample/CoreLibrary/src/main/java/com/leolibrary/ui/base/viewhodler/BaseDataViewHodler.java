package com.leolibrary.ui.base.viewhodler;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;


/**
 * Created by leo on 16/6/29.
 * 基类
 */
public class BaseDataViewHodler<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private B binding;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
        this.binding.setVariable(BR.data,data);
    }

    public BaseDataViewHodler(B binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public B getBinding() {
        return binding;
    }
}

