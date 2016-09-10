package com.leolibrary.ui.base.adapter.common;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leolibrary.callback.LayoutId;
import com.leolibrary.callback.ListCallback;
import com.leolibrary.ui.base.viewhodler.BaseDataViewHodler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 16/5/19.
 * 基类
 */
public abstract class PagerViewModelAdapter<B extends ViewDataBinding, T extends LayoutId> extends PagerAdapter implements ListCallback<T> {
    private List<T> data;
    private Context context;
    private LayoutInflater inflater;

    public PagerViewModelAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public List<T> getData() {
        return data;
    }

    public Context getContext() {
        return context;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        B b = DataBindingUtil.inflate(inflater, get(position).getItemLayoutId(), container, false);
        BaseDataViewHodler<B> hodler = new BaseDataViewHodler<>(b);
        hodler.setData(get(position));
        onBindDataToView(hodler, position, get(position));
        container.addView(b.getRoot());
        return b.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
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
        data.addAll(allData);
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

    public abstract void onBindDataToView(BaseDataViewHodler<B> holder, int position, T t);

}
