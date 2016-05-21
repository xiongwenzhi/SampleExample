package com.leo.example.ui.activity;

import android.support.v4.view.CustomViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.leo.example.Constans;
import com.leo.example.R;
import com.leo.example.TestUtil.TestDataUtil;
import com.leo.example.ui.adapter.page.PagerViewAdapter;
import com.leo.example.ui.animation.ZoomCardPageTransformer;
import com.leolibrary.ui.base.activity.BaseActivity;

/**
 * Created by leo on 16/5/20.
 */
public class GalleryCardActivity extends BaseActivity {
    private CustomViewPager viewPager;
    private PagerViewAdapter adapter;
    private LinearLayout ll_layout;

    @Override
    public void beforInitView() {
        setContentView(R.layout.activity_card_gallery);
    }

    @Override
    public void initView() {
        //初始化ViewPager
        boolean is3D = getIntent().getBooleanExtra(Constans.IS_3D, false);
        ll_layout = (LinearLayout) findViewById(R.id.ll_layout);
        viewPager = (CustomViewPager) findViewById(R.id.view_pager);
        adapter = new PagerViewAdapter(this);
        viewPager.setPageTransformer(true, new ZoomCardPageTransformer(viewPager, adapter, is3D));
        viewPager.setOffscreenPageLimit(5);
    }

    @Override
    public void initData() {
        LoadData();
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
     * 添加测试数据
     */
    private void LoadData() {
        adapter.addAll(TestDataUtil.getImageModel(R.layout.item_card_gallery_view));
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(adapter.size() / 2);
    }
}
