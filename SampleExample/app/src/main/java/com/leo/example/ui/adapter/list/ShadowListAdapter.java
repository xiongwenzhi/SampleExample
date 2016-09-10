package com.leo.example.ui.adapter.list;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.text.Html;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;

import com.leo.example.BR;
import com.leo.example.R;
import com.leo.example.databinding.ItemRvImageBinding;
import com.leo.example.databinding.ItemShadowCardviewBinding;
import com.leo.example.databinding.ItemShadowShapeBinding;
import com.leo.example.info.SubjectsInfo;
import com.leo.example.ui.viewmodel.ItemSubjectsInfoViewModel;
import com.leo.example.util.ToastUtil;
import com.leolibrary.ui.base.adapter.binding.BaseBindingListAdapter;
import com.leolibrary.ui.base.viewhodler.BaseDataViewHodler;


/**
 * Created by leo on 16/5/14.
 */
public class ShadowListAdapter extends BaseBindingListAdapter<ItemSubjectsInfoViewModel, ViewDataBinding> {

    public ShadowListAdapter(Context context) {
        super(context);
    }


    @Override
    public void onBindDataToView(BaseDataViewHodler<ViewDataBinding> holder, int position, ItemSubjectsInfoViewModel itemSubjectsInfoViewModel) {

    }

    @Override
    public void onBindListener(BaseDataViewHodler<ViewDataBinding> b, int position, ItemSubjectsInfoViewModel itemSubjectsInfoViewModel) {

    }
}
