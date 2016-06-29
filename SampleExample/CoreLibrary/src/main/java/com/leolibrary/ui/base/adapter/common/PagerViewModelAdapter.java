package com.leolibrary.ui.base.adapter.common;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.leolibrary.callback.LayoutId;
import com.leolibrary.callback.ListCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 16/5/19.
 * 基类
 */
public abstract class PagerViewModelAdapter<T extends LayoutId> extends PagerAdapter implements ListCallback<T> {
    private List<T> data;
    private Context context;

    public PagerViewModelAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
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
        View view = getItemView(container, position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    public abstract View getItemView(ViewGroup container, int position);

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

}
