package com.leo.example.util;

import android.graphics.Rect;

import com.leo.example.info.ImageSizeInfo;

/**
 * Created by leo on 16/9/11.
 */

public class RatioUtils {
    /**
     * 获取以宽度显示缩放比
     *
     * @param startBounds
     * @param info
     * @param scale
     */
    public static float getWidthRatio(ImageSizeInfo info, Rect startBounds, float scale) {
        float width = (float) info.getWidth() * scale;
        return (float) startBounds.width() / width;
    }

    /**
     * 获取以高度为基准显示缩放比
     *
     * @param startBounds
     * @param info
     * @param scale
     */
    public static float getHeightRatio(ImageSizeInfo info, Rect startBounds, float scale) {
        float height = (float) info.getHeight() * scale;
        return (float) startBounds.height() / height;
    }


    /**
     * 获取显示缩放比
     *
     * @param
     * @param startBounds
     */
    public static float getShowRatio(Rect startBounds, Rect finalBounds) {
        float ratio;
        if ((float) finalBounds.width() / finalBounds.height() > (float) startBounds.width() / startBounds.height()) {
            ratio = (float) startBounds.height() / finalBounds.height();
        } else {
            ratio = (float) startBounds.width() / finalBounds.width();
        }
        return ratio;
    }
}
