package com.leo.example.ui.adapter.list;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.leo.example.R;
import com.leo.example.info.ActivityInfo;
import com.leo.example.ui.activity.ShadowActivity;
import com.leo.example.ui.activity.ViewPageGalleryActivity;
import com.leolibrary.ui.base.adapter.BaseListAdapter;

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
        ((TextView) dataHodler.getView(R.id.tv_class_name)).setText(activityInfo.getClassName());
    }

    @Override
    public void itemOnClick(int position, ActivityInfo activityInfo) {
        switch (activityInfo.getType()) {
            case ViewPageGalleryMainActivity:
                getContext().startActivity(new Intent(getContext(), ViewPageGalleryActivity.class));
                break;
            case ShadowActivity:
                getContext().startActivity(new Intent(getContext(), ShadowActivity.class));
                break;
        }
    }
}
