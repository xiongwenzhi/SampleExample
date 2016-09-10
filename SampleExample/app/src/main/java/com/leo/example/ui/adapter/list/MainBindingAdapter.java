package com.leo.example.ui.adapter.list;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.leo.example.Constans;
import com.leo.example.R;
import com.leo.example.databinding.ItemRvListBinding;
import com.leo.example.info.ActivityInfo;
import com.leolibrary.ui.base.adapter.binding.BaseBindingListAdapter;
import com.leolibrary.ui.base.viewhodler.BaseDataViewHodler;

/**
 * Created by leo on 16/6/29.
 * 主页 - adapter
 */
public class MainBindingAdapter extends BaseBindingListAdapter<ActivityInfo, ItemRvListBinding> {
    public MainBindingAdapter(Context context) {
        super(context);
    }


    @Override
    public void onBindDataToView(BaseDataViewHodler<ItemRvListBinding> holder, int position, ActivityInfo activityInfo) {
        ItemRvListBinding binding = holder.getBinding();
        binding.setData(activityInfo);
    }


    @Override
    public void onBindListener(BaseDataViewHodler<ItemRvListBinding> b, int position, final ActivityInfo activityInfo) {
        b.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.equals(activityInfo.getName(), "3D画廊效果实现")) {
                    Intent intent = new Intent(getContext(), activityInfo.getmClass());
                    intent.putExtra(Constans.IS_3D, true);
                    getContext().startActivity(intent);
                } else {
                    getContext().startActivity(new Intent(getContext(), activityInfo.getmClass()));
                }
            }
        });
    }
}
