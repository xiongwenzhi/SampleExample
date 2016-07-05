package com.leo.example.ui.adapter.list;

import android.content.Context;
import android.widget.TextView;

import com.leo.example.R;
import com.leo.example.databinding.ItemCompleteTextBinding;
import com.leo.example.info.PersonInfo;
import com.leolibrary.ui.base.adapter.binding.BaseBindingFilterAdapter;
import com.leolibrary.ui.base.viewhodler.BaseDataViewHodler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 16/5/24.
 * 数据筛选适配
 */
public class PersonFilterAdapter extends BaseBindingFilterAdapter<PersonInfo, ItemCompleteTextBinding> {


    public PersonFilterAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void onBindDataToView(BaseDataViewHodler<ItemCompleteTextBinding> hodler, PersonInfo personInfo) {
        hodler.getBinding().setData(personInfo);
    }


    /**
     * 自定义筛选规则
     *
     * @param unfilteredValues 要过滤的数据
     * @param prefixString     关键词
     */
    @Override
    public List<PersonInfo> onFilterRule(String prefixString, List<PersonInfo> unfilteredValues) {
        ArrayList<PersonInfo> newValues = new ArrayList<>();
        for (PersonInfo info : unfilteredValues) {
            if (info.getName().contains(prefixString) || info.getPhone().contains(prefixString)) {
                newValues.add(info);
            }
        }
        return newValues;
    }

}
