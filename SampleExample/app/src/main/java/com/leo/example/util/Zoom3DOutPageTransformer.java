package com.leo.example.util;

import android.support.v4.view.CustomViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.leo.example.R;
import com.leo.example.ui.adapter.page.GalleryPageAdapter;
import com.leolibrary.callback.ListCallback;
import com.leolibrary.ui.base.adapter.BasePageAdapter;
import com.nineoldandroids.view.ViewHelper;


/**
 * Created by leo on 16/5/18.
 */
public class Zoom3DOutPageTransformer implements CustomViewPager.PageTransformer {
    private CustomViewPager viewPager;
    private int windowWidth = 0;
    private int windowHeight = 0;
    private float ALPHA = 0.1f;
    private float ROTATION = 28f;
    private int itemCount = 3;
    private float itemWidth = 0;
    private float itemHeight = 0;
    private boolean isSetLayouParams = false;
    private float translation;
    private int currentItem = 0;
    private ListCallback callback;

    public Zoom3DOutPageTransformer(CustomViewPager viewPager, ListCallback callback) {
        this.viewPager = viewPager;
        this.callback = callback;
        init();
    }

    private void init() {
        windowWidth = SystemUtil.getScreenWidth(viewPager.getContext());
        windowHeight = SystemUtil.getScreenHeight(viewPager.getContext());
        itemWidth = windowWidth / itemCount;
        itemHeight = windowHeight / itemCount;
        translation = itemWidth;
        initViewPagerLayoutParams();
    }

    public void transformPage(View view, float position) {
        Log.e("transformPage:View:" + view.getTag(), position + "");
        Log.e("transformPage:cut:" + viewPager.getCurrentItem(), position + "");
        currentItem = viewPager.getCurrentItem() % callback.size();
        if (position <= 1) {
            if (position < 0) {
                setScaleView(view, position * (180 - ROTATION), -position*(1-ALPHA));
            } else {
                setScaleView(view, -position * (180 - ROTATION), position*(1-ALPHA));
            }
        } else {
            setScaleView(view, -position * (180 - ROTATION), position*(1-ALPHA));
        }
    }

    private void setScaleView(View view, float r, float alpha) {
        int postion = (int) view.getTag();
        if (postion == currentItem) {
            setViewState(view, 1f, 0, 0f, 1f);
        } else if (postion == currentItem - 1) {
            setViewState(view, 0.8f, r, translation, alpha);
        } else if (postion == currentItem + 1) {
            setViewState(view, 0.8f, -r, -translation, alpha);
        } else if (postion == currentItem - 2) {
            setViewState(view, 0.6f, r, translation * 2, alpha);
        } else if (postion == currentItem + 2) {
            setViewState(view, 0.6f, -r, -translation * 2, alpha);
        } else if (postion == currentItem - 3) {
            setViewState(view, 0.4f, r, translation, alpha);
        } else if (postion == currentItem + 3) {
            setViewState(view, 0.4f, -r, -translation, alpha);
        } else if (postion + 1 == callback.size()) {
            setViewState(view, 0.8f, r, translation, alpha);
        } else {
            setViewState(view, 0.8f, -r, -translation, alpha);
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
