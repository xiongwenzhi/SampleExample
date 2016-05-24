package com.leolibrary.ui.base.viewhodler;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

import com.leolibrary.callback.ListCallback;
import com.leolibrary.callback.OnItemCallBack;


/**
 * Created by leo on 16/5/24.
 * ViewHodler 缓存类
 */
public class DataHodler extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final SparseArray<View> mViews = new SparseArray<>();
    private ListCallback callback;
    private OnItemCallBack itemOnClick;

    public DataHodler(View itemView, ListCallback callback, OnItemCallBack itemOnClick) {
        super(itemView);
        this.callback = callback;
        this.itemOnClick = itemOnClick;
        initView();
    }


    public void initView() {
        if (itemOnClick.getItemBackGround() != null) {
            itemView.setBackgroundDrawable(itemOnClick.getItemBackGround());
        }
        itemView.setOnClickListener(this);
    }

    /**
     * 通过控件id获取View
     */
    public <V extends View> V getView(int viewId) {
        V view = (V) mViews.get(viewId);
        if (view == null) {
            view = (V) itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        itemOnClick.onItemClick(getAdapterPosition(), callback.getData().get(getAdapterPosition()));
    }
}
