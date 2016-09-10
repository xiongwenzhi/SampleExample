package com.leo.example.util;

/**
 * Created by leo on 16/7/29.
 * Grid网格布局相关，位置计算
 */
public class LocationUtils {

    /**
     * 判断是否是在最左边一列
     *
     * @param position
     * @param rowCount
     */
    public static boolean isLeft(int position, int rowCount) {
        if (position % rowCount == 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是在最右边一列
     */
    public static boolean isRight(int position, int rowCount) {
        if (position % rowCount == rowCount - 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是在第一行
     */
    public static boolean isFirstLine(int position, int rowCount) {
        if (position < rowCount) {
            return true;
        }
        return false;
    }

    /**
     * 是否在最后一行
     */
    public static boolean isLastLine(int position, int size, int rowCount) {
        int childCount = size - size % rowCount;
        if (position >= childCount) {
            return true;
        }
        return false;
    }
}
