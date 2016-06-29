package com.leo.example.ui.activity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.leo.example.R;
import com.leo.example.callback.DataCallBack;
import com.leo.example.info.SubjectsInfo;
import com.leo.example.ui.adapter.page.GalleryPageAdapter;
import com.leo.example.ui.dialog.LoadingDialog;
import com.leo.example.util.DouBanApiUtil;
import com.leo.example.util.ToastUtil;
import com.leo.example.ui.animation.ZoomOutPageTransformer;
import com.leolibrary.ui.base.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Viewpager 实现Gallery画廊效果
 **/
public class ViewPageGalleryActivity extends BaseActivity {
    private ViewPager viewPager;
    private GalleryPageAdapter adapter;
    private LinearLayout ll_layout;
    private ArrayList<SubjectsInfo> list = new ArrayList<>();

    @Override
    public void beforInitView() {
        setContentView(R.layout.activity_viewpager_gallery);
    }

    @Override
    public void initView() {
        //初始化ViewPager
        ll_layout = (LinearLayout) findViewById(R.id.ll_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new GalleryPageAdapter(list, ViewPageGalleryActivity.this, R.layout.item_view);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setPageTransformer(false, new ZoomOutPageTransformer());
    }

    @Override
    public void initData() {
        LoadData(this);
    }

    @Override
    public void initListener() {
        ll_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPager.dispatchTouchEvent(event);
            }
        });
    }

    /**
     * 获取豆瓣Api数据
     */
    private void LoadData(final Context context) {
        DouBanApiUtil.LoadRepoData(this, new DataCallBack<List<SubjectsInfo>>() {
            @Override
            public void onSuccess(List<SubjectsInfo> subjectsInfos) {
                list.addAll(subjectsInfos);
                viewPager.setAdapter(adapter);
                LoadingDialog.hideLoadding();
            }

            @Override
            public void onFailure(Throwable t) {
                ToastUtil.showMessage(context, t.getMessage());
            }
        });
    }

}
