package com.leolibrary.callback;

import android.content.Context;
import android.view.View;

/**
 * Created by leo on 16/5/19.
 */
public interface LayoutId {
    int getItemLayoutId();

    View getView(Context context);

}
