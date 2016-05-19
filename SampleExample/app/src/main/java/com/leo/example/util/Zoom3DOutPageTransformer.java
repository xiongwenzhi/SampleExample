package com.leo.example.util;

import android.support.v4.view.CustomViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.leo.example.ui.adapter.page.GalleryPageAdapter;
import com.leolibrary.ui.base.adapter.BasePageAdapter;
import com.nineoldandroids.view.ViewHelper;


/**
 * Created by leo on 16/5/18.
 */
public class Zoom3DOutPageTransformer implements CustomViewPager.PageTransformer {
    private CustomViewPager viewPager;
    private int windowWidth = 0;
    private int windowHeight = 0;
    private float ALPHA = 0.7f;
    private float ROTATION = 28f;
    private int itemCount = 3;
    private float itemWidth = 0;
    private float itemHeight = 0;
    private boolean isSetLayouParams = false;
    private float translation;
    private int currentItem = 0;
    private BasePageAdapter adapter;

    public Zoom3DOutPageTransformer(CustomViewPager viewPager, BasePageAdapter adapter) {
        this.viewPager = viewPager;
        this.adapter = adapter;
        init();
    }

    private void init() {
        windowWidth = SystemUtil.getScreenWidth(viewPager.getContext());
        windowHeight = SystemUtil.getScreenHeight(viewPager.getContext());
        itemWidth = windowWidth / itemCount;
        itemHeight = windowHeight / itemCount;
        translation = itemWidth;
        adapter = (GalleryPageAdapter) viewPager.getAdapter();
        initViewPagerLayoutParams();
    }

    public void transformPage(View view, float position) {
        Log.e("transformPage:View:" + view.getTag(), position + "");
        Log.e("transformPage:cut:" + viewPager.getCurrentItem(), position + "");
        currentItem = viewPager.getCurrentItem() % adapter.getPageDataSize();
        if (position > 0.5) {
            view.setAlpha(position);
        }
        setScaleView(view);
    }

    private void setScaleView(View view) {
        int postion = (int) view.getTag();
        if (postion == currentItem) {
            setViewState(view, 1f, 0f, 0f, 1f);
        } else if (postion == currentItem - 1) {
            setViewState(view, 0.8f, ROTATION, translation, 1f);
        } else if (postion == currentItem + 1) {
            setViewState(view, 0.8f, -ROTATION, -translation, 1f);
        } else if (postion == currentItem - 2) {
            setViewState(view, 0.6f, ROTATION, (float) (translation + translation * 1.1), ALPHA);
        } else if (postion == currentItem + 2) {
            setViewState(view, 0.6f, -ROTATION, -(float) (translation + translation * 1.1), ALPHA);
        } else if (postion == currentItem - 3) {
            setViewState(view, 0.4f, ROTATION, translation, ALPHA);
        } else if (postion == currentItem + 3) {
            setViewState(view, 0.4f, -ROTATION, -translation, ALPHA);
        } else if (postion + 1 == adapter.getPageDataSize()) {
            setViewState(view, 0.8f, ROTATION, translation, 1f);
        } else {
            setViewState(view, 0.8f, -ROTATION, -translation, 1f);
        }
    }


    private void setViewState(View view, float scale, float rotation, float translation, float alpha) {
        view.setScaleX(scale);
        view.setScaleY(scale);
        view.setAlpha(alpha);
        view.setTranslationX(translation);
        view.setRotationY(rotation);
    }


    private void initViewPagerLayoutParams() {
        if (isSetLayouParams) {
            return;
        }
        if (viewPager.getWidth() <= 0 || viewPager.getHeight() <= 0) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
        if (viewPager.getWidth() < itemWidth) {
            layoutParams.width = (int) itemWidth;
        }
        if (viewPager.getHeight() < itemHeight) {
            layoutParams.height = (int) itemHeight;
        }
        viewPager.setLayoutParams(layoutParams);
        isSetLayouParams = true;
    }
}
