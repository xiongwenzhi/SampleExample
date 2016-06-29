package com.leo.example;

import com.leolibrary.ui.base.BaseContext;

/**
 * Created by leo on 16/6/29.
 * App上下文环境
 */
public class AppContext extends BaseContext {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static AppContext getContext() {
        return BaseContext.getAppContext();
    }
}
