package com.leolibrary.utils;

import android.support.annotation.DimenRes;

import com.leolibrary.ui.base.BaseContext;

/**
 * Created by leo on 16/9/10.
 * 获取dimen相关参数
 */
public class Dimens {

    /**
     * getDimensionPixelSize
     *
     * @param dimens
     * @return
     */
    public static int getDimensionPixelSize(@DimenRes int dimens) {
        return BaseContext.getAppContext().getResources().getDimensionPixelSize(dimens);
    }


    /**
     * getDimension
     *
     * @param dimens
     * @return
     */
    public static float getDimension(@DimenRes int dimens) {
        return BaseContext.getAppContext().getResources().getDimension(dimens);
    }


    /**
     * getDimensionPixelOffset
     *
     * @param dimens
     * @return
     */
    public static int getDimensionPixelOffset(@DimenRes int dimens) {
        return BaseContext.getAppContext().getResources().getDimensionPixelOffset(dimens);
    }
}
