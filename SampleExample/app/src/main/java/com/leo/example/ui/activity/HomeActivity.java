package com.leo.example.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.leo.example.R;
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
        adapter.add(new ActivityInfo("Viewpager 实现Gallery画廊效果", ViewPageGalleryActivity.class));
        adapter.add(new ActivityInfo("Shape 实现CardView阴影", ShadowActivity.class));
        adapter.add(new ActivityInfo("3D画廊效果实现", GalleryCardActivity.class));
        adapter.add(new ActivityInfo("仿QQ天气星座卡片效果", GalleryCardActivity.class));
        adapter.notifyDataSetChanged();
    }


    @Override
    public void initListener() {

    }

    @Override
    public int getLeftMenuIcon() {
        return 0;
    }

    @Override
    public View.OnClickListener getLeftMenuClick() {
        return null;
    }
}
