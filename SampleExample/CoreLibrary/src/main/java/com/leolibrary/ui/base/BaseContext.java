package com.leolibrary.ui.base;

import android.app.Application;

/**
 * Created by leo on 16/6/29.
 * 基类
 */
public class BaseContext extends Application {
    private static BaseContext appContext;

    public BaseContext() {
        appContext = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static <T extends Application> T getAppContext() {
        return (T) appContext;
    }
}
