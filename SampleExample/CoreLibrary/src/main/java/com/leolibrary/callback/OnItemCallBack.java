package com.leolibrary.callback;

import android.graphics.drawable.Drawable;

/**
 * Created by leo on 16/5/24.
 * 列表Item onclick,backGround相关
 */
public interface OnItemCallBack<T> {
    void onItemClick(int position, T t);

    Drawable getItemBackGround();
}
