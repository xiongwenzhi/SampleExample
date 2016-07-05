package com.leolibrary.ui.base.adapter.binding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.leolibrary.callback.BindingListCallback;
import com.leolibrary.ui.base.viewhodler.BaseDataViewHodler;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by leo on 16/5/14.
 * 通用的adapter - 配合DataBinding使用
 */
public abstract class BaseBindingListAdapter<T, B extends ViewDataBinding> extends RecyclerView.Adapter<BaseDataViewHodler<B>> implements BindingListCallback<T, B> {
    private List<T> data;
    private Context context;
    private LayoutInflater mInflater;

    public BaseBindingListAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseDataViewHodler<B> onCreateViewHolder(ViewGroup parent, int viewType) {
        B bing = DataBindingUtil.inflate(getInflater(), viewType, parent, false);
        BaseDataViewHodler viewHodler = new BaseDataViewHodler(bing);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(BaseDataViewHodler<B> holder, int position) {
        onBindDataToView(holder, position, get(position));
        onBindListener(holder, position, get(position));
    }

    public LayoutInflater getInflater() {
        return mInflater;
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

    @Override
    public int getItemViewType(int position) {
        return getItemLayoutId(position);
    }

    protected abstract int getItemLayoutId(int position);

    public abstract void onBindDataToView(BaseDataViewHodler<B> holder, int position, T t);

    public Context getContext() {
        return context;
    }

}



