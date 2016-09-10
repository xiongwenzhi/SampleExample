package com.leo.example.ui.adapter.page;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.leo.example.ui.viewmodel.Image3DModel;
import com.leolibrary.ui.base.adapter.common.PagerViewModelAdapter;
import com.leolibrary.ui.base.viewhodler.BaseDataViewHodler;

/**
 * Created by leo on 16/5/19.
 */
public class PagerViewAdapter extends PagerViewModelAdapter<ViewDataBinding, Image3DModel> {
    public PagerViewAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindDataToView(BaseDataViewHodler<ViewDataBinding> holder, int position, Image3DModel image3DModel) {
        holder.getBinding().getRoot().setTag(position);
    }
}
