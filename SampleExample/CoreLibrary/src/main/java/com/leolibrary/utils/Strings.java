package com.leolibrary.utils;

/**
 * Created by leo on 16/9/10.
 * 字符串工具类
 */
public class Strings {
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String... str) {
        if (str == null) {
            return true;
        }
        for (String s : str) {
            if (s == null || s.isEmpty() || s.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
