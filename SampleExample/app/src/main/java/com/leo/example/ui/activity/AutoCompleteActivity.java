package com.leo.example.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.leo.example.R;
import com.leo.example.info.PersonInfo;
import com.leo.example.ui.adapter.list.PersonFilterAdapter;
import com.leolibrary.ui.base.activity.BaseActivity;

/**
 * Created by leo on 16/5/24.
 * 支持自定义数据过滤规则的 AutoCompleteTextView demo
 */
public class AutoCompleteActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private AutoCompleteTextView tv_seach;
    private PersonFilterAdapter filterAdapter;
    private String[] name = {"张三", "张三峰", "李四", "历史",
            "王五", "王八蛋", "哈哈哈", "呵呵呵",
            "你好呀", "就是这么酷", "啦啦啦啦", "yes"};

    @Override
    public void beforInitView() {
        setContentView(R.layout.activity_auto_complete);
    }

    @Override
    public void initView() {
        tv_seach = (AutoCompleteTextView) findViewById(R.id.tv_seach);
        tv_seach.setThreshold(1);
        filterAdapter = new PersonFilterAdapter(R.layout.item_complete_textview);
        tv_seach.setAdapter(filterAdapter);
    }

    @Override
    public void initData() {
        for (int i = 0; i < name.length; i++) {
            filterAdapter.add(new PersonInfo(name[i]));
        }
        filterAdapter.refreshFilterData();
        filterAdapter.notifyDataSetChanged();
    }

    @Override
    public void initListener() {
        tv_seach.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        tv_seach.setText(filterAdapter.get(position).getName());
        tv_seach.setSelection(tv_seach.getText().length());//设置光标到末尾
    }
}
