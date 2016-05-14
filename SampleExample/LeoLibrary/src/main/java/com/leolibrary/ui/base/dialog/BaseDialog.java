package com.leolibrary.ui.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;


import com.leolibrary.ui.base.activity.InitRes;


/**
 * Created by leo on 16/5/14.
 */
public abstract class BaseDialog extends Dialog implements InitRes {
    public BaseDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        beforInitView();
        initView();
        initListener();
        initData();
    }
}
