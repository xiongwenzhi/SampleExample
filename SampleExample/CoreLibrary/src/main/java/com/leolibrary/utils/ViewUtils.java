package com.leolibrary.utils;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by leo on 16/9/11.
 */

public class ViewUtils {
    /**
     * 测量控件宽度
     */
    public static int getViewWidth(View view) {
        view.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return view.getMeasuredWidth();
    }

    /**
     * 测量控件高度
     */
    public static int getViewHeight(View view) {
        view.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return view.getMeasuredHeight();
    }

    /**
     * 测量View在屏幕中显示的位置
     */
    public static Rect getViewGlobalRect(View view) {
        Rect rect = new Rect();
        if (view != null) {
            view.getGlobalVisibleRect(rect);
        }
        return rect;
    }

    /**
     * 测量View在屏幕的的尺寸信息
     */
    public static Rect getViewLocalRect(View view) {
        Rect rect = new Rect();
        if (view != null) {
            view.getLocalVisibleRect(rect);
        }
        return rect;
    }
}
