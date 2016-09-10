package com.leo.example.ui.adapter.list;

import android.content.Context;

import com.leo.example.databinding.ItemRvListBinding;
import com.leo.example.ui.viewmodel.ActivityInfoViewModel;
import com.leolibrary.ui.base.adapter.binding.BaseBindingListAdapter;
import com.leolibrary.ui.base.viewhodler.BaseDataViewHodler;

/**
 * Created by leo on 16/6/29.
 * 主页 - adapter
 */
public class MainBindingAdapter extends BaseBindingListAdapter<ActivityInfoViewModel, ItemRvListBinding> {
    public MainBindingAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindDataToView(BaseDataViewHodler<ItemRvListBinding> holder, int position, ActivityInfoViewModel activityInfoViewModel) {

    }

    @Override
    public void onBindListener(BaseDataViewHodler<ItemRvListBinding> b, int position, ActivityInfoViewModel activityInfoViewModel) {

    }
}
