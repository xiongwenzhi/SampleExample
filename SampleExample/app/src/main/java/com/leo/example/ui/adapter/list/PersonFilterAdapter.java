package com.leo.example.ui.adapter.list;

import android.widget.TextView;

import com.leo.example.R;
import com.leo.example.info.PersonInfo;
import com.leolibrary.ui.base.adapter.common.BaseFilterAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 16/5/24.
 * 数据筛选适配
 */
public class PersonFilterAdapter extends BaseFilterAdapter<PersonInfo> {
    public PersonFilterAdapter(int layoutId) {
        super(layoutId);
    }

    @Override
    public void onBindDataToView(DataHodler hodler, PersonInfo personInfo) {
        TextView name = hodler.getView(R.id.tv_name);
        TextView phone = hodler.getView(R.id.tv_phone);
        name.setText(personInfo.getName());
        phone.setText(personInfo.getPhone());
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
