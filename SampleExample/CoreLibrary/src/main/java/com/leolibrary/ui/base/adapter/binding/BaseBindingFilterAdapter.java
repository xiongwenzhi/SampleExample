package com.leolibrary.ui.base.adapter.binding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.leolibrary.R;
import com.leolibrary.callback.ListCallback;
import com.leolibrary.ui.base.viewhodler.BaseDataViewHodler;
import com.leolibrary.utils.filter.CustomFilterRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 16/5/23.
 * 基类 自定义数据过滤规则 用于AutoCompleteTextView
 */
public abstract class BaseBindingFilterAdapter<T, B extends ViewDataBinding> extends BaseAdapter implements Filterable, ListCallback<T> {
    private CustomFilterRule<T> filter;
    private List<T> data;
    private int layoutId = -1;
    private LayoutInflater layoutInflater;

    public BaseBindingFilterAdapter(Context context, int layoutId) {
        this.layoutId = layoutId;
        layoutInflater = LayoutInflater.from(context);
        initList();
    }

    private void initList() {
        if (data == null) {
            data = new ArrayList<>();
        }
    }

    @Override
    public CustomFilterRule<T> getFilter() {
        if (filter == null) {
            filter = getCostomFliter();
        }
        return filter;
    }

    public int getLayoutId() {
        return layoutId;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseDataViewHodler dataHodler;
        if (convertView == null) {
            B b = DataBindingUtil.inflate(getLayoutInflater(), getLayoutId(), parent, false);
            dataHodler = new BaseDataViewHodler(b);
            convertView = b.getRoot();
            convertView.setTag(dataHodler);
        } else {
            dataHodler = (BaseDataViewHodler) convertView.getTag();
        }
        onBindDataToView(dataHodler, getItem(position));
        return convertView;
    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
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

    @Override
    public List<T> getData() {
        return data;
    }

    /**
     * 动态添加改变数据时，需要调用该方法重新设置Filter中的数据，否则下拉列表显示的是旧数据
     */
    public void onRefreshFilterData() {
        getFilter().setmUnfilteredData(data);
    }


    /**
     * 抽象方法，绑定数据
     * View
     */
    public abstract void onBindDataToView(BaseDataViewHodler<B> hodler, T t);

    /**
     * 抽象方法，自定义数据过滤规则。
     */
    public abstract List<T> onFilterRule(String prefixString, List<T> unfilteredValues);

    /**
     * 创建Filter筛选器
     */
    private CustomFilterRule<T> getCostomFliter() {
        CustomFilterRule<T> customFilter = new CustomFilterRule<T>(data) {
            @Override
            public List<T> onFilterData(String prefixString, List<T> unfilteredValues) {
                return onFilterRule(prefixString, unfilteredValues);
            }

            @Override
            protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
                data = (List<T>) results.values;
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return customFilter;
    }

}
