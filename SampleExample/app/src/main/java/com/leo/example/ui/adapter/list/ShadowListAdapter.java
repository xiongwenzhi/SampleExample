package com.leo.example.ui.adapter.list;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.example.R;
import com.leo.example.info.SubjectsInfo;
import com.leo.example.util.ToastUtil;
import com.leolibrary.ui.base.adapter.BaseListAdapter;
import com.leolibrary.ui.base.viewhodler.DataHodler;
import com.leolibrary.utils.image.PhotoLoader;


/**
 * Created by leo on 16/5/14.
 */
public class ShadowListAdapter extends BaseListAdapter<SubjectsInfo> {
    private int position;

    public ShadowListAdapter(Context context, int position) {
        super(context);
        this.position = position;
    }

    @Override
    protected int getItemLayoutId() {
        if (position == 0) {
            return R.layout.item_shadow_shape;
        }
        return R.layout.item_shadow_cardview;
    }

    @Override
    public Drawable getItemBackGround() {
        return null;
    }

    @Override
    public void onBindDataToView(DataHodler dataHodler, SubjectsInfo subject) {
        ((TextView) dataHodler.getView(R.id.tv_movie_ratting)).setText(getAverage(subject));
        ((TextView) dataHodler.getView(R.id.tv_movie_name)).setText(subject.getTitle());
        PhotoLoader.display(getContext(), (ImageView) dataHodler.getView(R.id.iv_move_img), subject.getImages().getLarge(), getContext().getResources().getDrawable(R.mipmap.ic_loading));
    }

    /**
     * 获取评分
     */
    private String getAverage(SubjectsInfo subject) {
        String average = String.valueOf(subject.getRating().getAverage());
        if (TextUtils.isEmpty(average) && average.contains(".")) {
            return Html.fromHtml("<strong>" + average.charAt(0) + "</strong>." +
                    "<small>" + average.charAt(2)
                    + "</small>").toString();
        } else {
            return average;
        }
    }

    @Override
    public void onItemClick(int position, SubjectsInfo subjectsInfo) {
        ToastUtil.showMessage(getContext(), subjectsInfo.getTitle());
    }
}
