package com.leolibrary.ui.base.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.leolibrary.callback.ListCallback;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by leo on 16/5/14.
 * 通用的adapter
 */
public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<BaseListAdapter.DataHodler> implements ListCallback<T> {
    private List<T> data;
    private Context context;

    public BaseListAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }


    /**
     * 一定要BaseListAdapter.DataHodler 否则会编译不通过，因为要覆盖抽象方法
     */
    @Override
    public BaseListAdapter.DataHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataHodler(LayoutInflater.from(context).inflate(getItemLayoutId(), parent, false));
    }

    @Override
    public void onBindViewHolder(BaseListAdapter.DataHodler holder, int position) {
        onBindDataToView(holder, get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public T get(int position) {
        return data.get(position);
    }

    @Override
    public void add(T t) {
        data.add(t);
    }

    @Override
    public void add(int position, T t) {
        data.add(position, t);
    }

    @Override
    public void addAll(List<T> allData) {
        this.data.addAll(allData);
    }

    @Override
    public void remove(T t) {
        data.remove(t);
    }

    @Override
    public void remove(int position) {
        data.remove(position);
    }

    @Override
    public int size() {
        return data.size();
    }

    protected abstract int getItemLayoutId();

    public abstract Drawable getItemBackGround();

    public abstract void onBindDataToView(DataHodler dataHodler, T t);

    public List<T> getData() {
        return data;
    }

    public Context getContext() {
        return context;
    }

    public class DataHodler extends RecyclerView.ViewHolder {
        private final SparseArray<View> mViews;

        public DataHodler(View itemView) {
            super(itemView);
            mViews = new SparseArray<>();
            initView();
        }


        public void initView() {
            if (getItemBackGround() != null) {
                itemView.setBackgroundDrawable(getItemBackGround());
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemOnClick(getAdapterPosition(), data.get(getAdapterPosition()));
                }
            });
        }

        public <V extends View> V getView(int viewId) {
            V view = (V) mViews.get(viewId);
            if (view == null) {
                view = (V) itemView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return view;
        }
    }

    public void itemOnClick(int position, T t) {

    }
}



