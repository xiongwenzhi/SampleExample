package com.leo.example.ui.adapter.list;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.TextView;

import com.leo.example.Constans;
import com.leo.example.R;
import com.leo.example.info.ActivityInfo;
import com.leolibrary.ui.base.adapter.BaseListAdapter;
import com.leolibrary.ui.base.viewhodler.DataHodler;

/**
 * Created by leo on 16/5/14.
 */
public class MainListAdapter extends BaseListAdapter<ActivityInfo> {


    public MainListAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.item_rv_list;
    }

    @Override
    public Drawable getItemBackGround() {
        return getContext().getResources().getDrawable(R.drawable.selector_layout_bg);
    }

    @Override
    public void onBindDataToView(DataHodler dataHodler, ActivityInfo activityInfo) {
        ((TextView) dataHodler.getView(R.id.tv_name)).setText(activityInfo.getName());
        ((TextView) dataHodler.getView(R.id.tv_class_name)).setText(activityInfo.getmClass().getName());
    }

    @Override
    public void onItemClick(int position, ActivityInfo activityInfo) {
        if (TextUtils.equals(activityInfo.getName(), "3D画廊效果实现")) {
            Intent intent = new Intent(getContext(), activityInfo.getmClass());
            intent.putExtra(Constans.IS_3D, true);
            getContext().startActivity(intent);
        } else {
            getContext().startActivity(new Intent(getContext(), activityInfo.getmClass()));
        }
    }
}

