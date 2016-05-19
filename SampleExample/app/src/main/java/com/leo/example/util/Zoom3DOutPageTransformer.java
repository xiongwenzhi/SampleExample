package com.leo.example.util;

import android.support.v4.view.CustomViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


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

    public Zoom3DOutPageTransformer(CustomViewPager viewPager) {
        this.viewPager = viewPager;
        init();
    }

    private void init() {
        windowWidth = SystemUtil.getScreenWidth(viewPager.getContext());
        windowHeight = SystemUtil.getScreenHeight(viewPager.getContext());
        itemWidth = windowWidth / itemCount;
        itemHeight = windowHeight / itemCount;
        initViewPagerLayoutParams();
    }

    public void transformPage(View view, float position) {
//        Log.e("transformPage:", viewPager.getCurrentItem() + "");
//        Log.e("transformPage:View", view.getTag() + "");
//        Log.e("transformPage:View:pageWidth", view.getWidth() + "");
//        Log.e("transformPage:View:pageHeight", view.getHeight() + "");
        setScaleView(view);
    }

    private void setScaleView(View view) {
        int postion = (int) view.getTag();
        float translation = itemWidth * 0.7f;
        if (postion == viewPager.getCurrentItem()) {
            setViewState(view, 1.5f, 0f, 0f, 1f);
        } else if (postion == viewPager.getCurrentItem() - 1) {
            setViewState(view, 1.1f, ROTATION, translation, ALPHA);
        } else if (postion == viewPager.getCurrentItem() + 1) {
            setViewState(view, 1.1f, -ROTATION, -translation, ALPHA);
        } else if (postion == viewPager.getCurrentItem() - 2) {
            setViewState(view, 1f, ROTATION, (float) (translation + translation * 1.1), ALPHA);
        } else if (postion == viewPager.getCurrentItem() + 2) {
            setViewState(view, 1f, -ROTATION, -(float) (translation + translation * 1.1), ALPHA);
        } else if (postion == viewPager.getCurrentItem() - 3) {
            setViewState(view, 0.9f, ROTATION, translation, ALPHA);
        } else if (postion == viewPager.getCurrentItem() + 3) {
            setViewState(view, 0.9f, -ROTATION, -translation, ALPHA);
        }
        Log.e("setScaleView:", (postion % itemCount) + "");
    }


    private void setViewState(View view, float scale, float rotation, float translation, float alpha) {
        view.setScaleX(scale);
        view.setScaleY(scale);
        view.setAlpha(alpha);
        view.setTranslationX(translation);
        view.setRotationY(rotation);
    }


    private void initViewPagerLayoutParams() {
//        Log.e("transformPage:viewPager:pageWidth", viewPager.getWidth() + "");
//        Log.e("transformPage:viewPager:pageHeight", viewPager.getHeight() + "");
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
