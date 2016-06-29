package com.leo.example.ui.activity;

import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.AdapterView;

import com.leo.example.R;
import com.leo.example.databinding.ActivityAutoCompleteBinding;
import com.leo.example.info.PersonInfo;
import com.leo.example.ui.adapter.list.PersonFilterAdapter;
import com.leolibrary.ui.base.activity.BaseActivity;

/**
 * Created by leo on 16/5/24.
 * 支持自定义数据过滤规则的 AutoCompleteTextView demo
 */
public class AutoCompleteActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private ActivityAutoCompleteBinding binding;
    private PersonFilterAdapter filterAdapter;
    private String[] name = {"张三", "张三峰", "李四", "历史",
            "王五", "测试", "哈哈哈", "呵呵呵",
            "你好呀", "网测", "啦啦啦啦", "yes"};

    @Override
    public void beforInitView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auto_complete);
    }

    @Override
    public void initView() {
        //设置多少个字开始显示下拉列表
        binding.tvSeach.setThreshold(1);
        //初始化adapter,R.layout.item_complete_textview为下拉列表显示的布局文件
        filterAdapter = new PersonFilterAdapter(R.layout.item_complete_textview);
        binding.tvSeach.setAdapter(filterAdapter);
    }

    @Override
    public void initData() {
        //添加测试数据
        for (int i = 0; i < name.length; i++) {
            filterAdapter.add(new PersonInfo(name[i], "13337589632" + i));
        }
        //刷新Filter数据
        filterAdapter.onRefreshFilterData();
        filterAdapter.notifyDataSetChanged();
    }

    @Override
    public void initListener() {
        //下拉列表点击事件
        binding.tvSeach.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        binding.tvSeach.setText(filterAdapter.get(position).getName());
        //设置光标到末尾
        binding.tvSeach.setSelection(binding.tvSeach.getText().length());
    }
}
