package com.leolibrary.ui.base.adapter.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.leolibrary.callback.ListCallback;
import com.leolibrary.callback.OnItemCallBack;
import com.leolibrary.ui.base.viewhodler.DataHodler;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by leo on 16/5/14.
 * 通用的adapter
 */
public abstract class BaseListAdapter<T> extends RecyclerView.Adapter<DataHodler> implements ListCallback<T>, OnItemCallBack<T> {
    private List<T> data;
    private Context context;

    public BaseListAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }


    @Override
    public DataHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataHodler(LayoutInflater.from(context).inflate(getItemLayoutId(), parent, false), this, this);
    }

    @Override
    public void onBindViewHolder(DataHodler holder, int position) {
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

    @Override
    public List<T> getData() {
        return data;
    }

    protected abstract int getItemLayoutId();

    public abstract void onBindDataToView(DataHodler dataHodler, T t);

    public Context getContext() {
        return context;
    }

}



