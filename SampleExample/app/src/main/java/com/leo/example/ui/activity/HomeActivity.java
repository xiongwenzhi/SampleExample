package com.leo.example.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.leo.example.R;
import com.leo.example.databinding.ActivityHomeBinding;
import com.leo.example.info.ActivityInfo;
import com.leo.example.ui.adapter.list.MainBindingAdapter;
import com.leolibrary.ui.base.activity.BaseActivity;

/**
 * 主界面
 */
public class HomeActivity extends BaseActivity<ActivityHomeBinding> {
    private MainBindingAdapter adapter;


    @Override
    public ActivityHomeBinding beforInitView() {
        return DataBindingUtil.setContentView(this, R.layout.activity_home);
    }

    @Override
    public void initView() {
        adapter = new MainBindingAdapter(this);
        binding.rvList.setLayoutManager(new LinearLayoutManager(this));
        binding.rvList.setAdapter(adapter);
    }

    @Override
    public void initData() {
        adapter.add(new ActivityInfo("Viewpager 实现Gallery画廊效果", ViewPageGalleryActivity.class));
        adapter.add(new ActivityInfo("Shape 实现CardView阴影", ShadowActivity.class));
        adapter.add(new ActivityInfo("3D画廊效果实现", GalleryCardActivity.class));
        adapter.add(new ActivityInfo("QQ天气星座卡片效果", GalleryCardActivity.class));
        adapter.add(new ActivityInfo("支持自定义数据过滤规则的AutoCompleteTextView-Demo", AutoCompleteActivity.class));
        adapter.add(new ActivityInfo("朋友圈图片查看缩放效果", ZoomImageActivity.class));
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
