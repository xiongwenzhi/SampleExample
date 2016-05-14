package com.leo.example.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.leo.example.R;
import com.leo.example.enums.ActivityType;
import com.leo.example.info.ActivityInfo;
import com.leo.example.ui.adapter.list.MainListAdapter;
import com.leolibrary.ui.base.activity.BaseActivity;


public class HomeActivity extends BaseActivity {
    RecyclerView rvList;
    private MainListAdapter adapter;

    @Override
    public void beforInitView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    public void initView() {
        adapter = new MainListAdapter(this);
        rvList = (RecyclerView) findViewById(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);
    }

    @Override
    public void initData() {
        adapter.add(new ActivityInfo("Viewpager 实现Gallery画廊效果", ActivityType.ViewPageGalleryMainActivity, HomeActivity.class.getName()));
        adapter.add(new ActivityInfo("Shape 实现CardView阴影", ActivityType.ShadowActivity, ShadowActivity.class.getName()));
        adapter.notifyDataSetChanged();
    }


    @Override
    public void initListener() {

    }

}
