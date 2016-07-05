package com.leo.example.ui.adapter.list;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.leo.example.R;
import com.leo.example.databinding.ItemShadowCardviewBinding;
import com.leo.example.databinding.ItemShadowShapeBinding;
import com.leo.example.info.SubjectsInfo;
import com.leo.example.util.ToastUtil;
import com.leolibrary.ui.base.adapter.binding.BaseBindingListAdapter;
import com.leolibrary.ui.base.viewhodler.BaseDataViewHodler;


/**
 * Created by leo on 16/5/14.
 */
public class ShadowListAdapter extends BaseBindingListAdapter<SubjectsInfo, ViewDataBinding> {
    private int position;

    public ShadowListAdapter(Context context, int position) {
        super(context);
        this.position = position;
    }


    /**
     * 获取评分
     */
    private String getAverage(SubjectsInfo subject) {
        String average = String.valueOf(subject.getRating().getAverage());
        if (TextUtils.isEmpty(average) && average.contains(".")) {
            return Html.fromHtml("<strong>" + average.charAt(0) + "</strong>." +
                    "<small>" + average.charAt(2)
                    + "</small>").toString();
        } else {
            return average;
        }
    }

    @Override
    protected int getItemLayoutId(int position) {
        if (position == 0) {
            return R.layout.item_shadow_shape;
        }
        return R.layout.item_shadow_cardview;
    }

    @Override
    public void onBindDataToView(BaseDataViewHodler<ViewDataBinding> holder, int position, SubjectsInfo subjectsInfo) {
        if (getItemViewType(position) == R.layout.item_shadow_shape) {
            ((ItemShadowShapeBinding) holder.getBinding()).setData(subjectsInfo);
        } else {
            ((ItemShadowCardviewBinding) holder.getBinding()).setData(subjectsInfo);
        }
    }


    @Override
    public void onBindListener(BaseDataViewHodler<ViewDataBinding> b, int position, final SubjectsInfo subjectsInfo) {
        b.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMessage(getContext(), subjectsInfo.getTitle());
            }
        });
    }
}
