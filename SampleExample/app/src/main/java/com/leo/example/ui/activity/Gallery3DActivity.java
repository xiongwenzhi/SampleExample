package com.leo.example.ui.activity;

import android.support.v4.view.CustomViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.leo.example.R;
import com.leo.example.info.ImageInfo;
import com.leo.example.info.SubjectsInfo;
import com.leo.example.ui.adapter.page.PagerViewAdapter;
import com.leo.example.ui.viewmodel.ImageViewModel;
import com.leo.example.util.Zoom3DOutPageTransformer;
import com.leolibrary.ui.base.activity.BaseActivity;

import java.util.ArrayList;

/**
 * Created by leo on 16/5/18.
 */
public class Gallery3DActivity extends BaseActivity {
    private CustomViewPager viewPager;
    private PagerViewAdapter adapter;
    private ArrayList<SubjectsInfo> list = new ArrayList<>();
    private LinearLayout ll_layout;

    @Override
    public void beforInitView() {
        setContentView(R.layout.activity_3d_gallery);
    }

    @Override
    public void initView() {
        //初始化ViewPager
        ll_layout = (LinearLayout) findViewById(R.id.ll_layout);
        viewPager = (CustomViewPager) findViewById(R.id.view_pager);
        adapter = new PagerViewAdapter(this);
        viewPager.setPageTransformer(true, new Zoom3DOutPageTransformer(viewPager, adapter));
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
        adapter.add(new ImageViewModel(new ImageInfo("小狼狗", "http://rescdn.qqmail.com/dyimg/20140409/72B8663B7F23.jpg")));
        adapter.add(new ImageViewModel(new ImageInfo("小狼狗", "http://www.gscn.com.cn/pic/0/10/13/74/10137424_638302.jpg")));
        adapter.add(new ImageViewModel(new ImageInfo("小狼狗", "http://z.k1982.com/design_img/201412/2014120822492137209.jpg")));
        adapter.add(new ImageViewModel(new ImageInfo("小狼狗", "http://hdimages.takungpao.com/2014/0114/20140114040855269.jpg")));
        adapter.add(new ImageViewModel(new ImageInfo("小狼狗", "http://www.ellf.ru/uploads/posts/2010-02/1265238071_9454766-md.jpg")));
        adapter.add(new ImageViewModel(new ImageInfo("小狼狗", "http://pic25.nipic.com/20121112/9211_140221458131_2.jpg")));
        adapter.add(new ImageViewModel(new ImageInfo("小狼狗", "http://image.tianjimedia.com/uploadImages/2011/302/6ZI1A37D2433.jpg")));
        adapter.add(new ImageViewModel(new ImageInfo("小狼狗", "http://photo.l99.com/bigger/9e2/1427726129531_9sgt2j.jpg")));
        adapter.add(new ImageViewModel(new ImageInfo("小狼狗", "http://www.ueuicg.com/uploads/allimg/120505/3-1205051444163J.jpg")));
        viewPager.setAdapter(adapter);
    }
}
