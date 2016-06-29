package com.leolibrary.ui.base.adapter.common;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.leolibrary.R;
import com.leolibrary.callback.ListCallback;
import com.leolibrary.utils.filter.CustomFilterRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 16/5/23.
 * 基类 自定义数据过滤规则 用于AutoCompleteTextView
 */
public abstract class BaseFilterAdapter<T> extends BaseAdapter implements Filterable, ListCallback<T> {
    private CustomFilterRule<T> filter;
    private List<T> data;
    private int layoutId = -1;

    public BaseFilterAdapter(int layoutId) {
        this.layoutId = layoutId;
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
        if (layoutId != -1) {
            return layoutId;
        }
        return R.layout.item_complete_textview;
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
        DataHodler dataHodler;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), null);
            dataHodler = new DataHodler(convertView);
            convertView.setTag(dataHodler);
        } else {
            dataHodler = (DataHodler) convertView.getTag();
        }
        onBindDataToView(dataHodler, getItem(position));
        return convertView;
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
     * 复用ViewHodler
     */
    public class DataHodler {
        private View convertView;
        private SparseArray viewRes = new SparseArray();

        public DataHodler(View convertView) {
            this.convertView = convertView;
        }

        /**
         * 获取View
         */
        public <V extends View> V getView(int viewId) {
            V view = (V) viewRes.get(viewId);
            if (view == null) {
                view = (V) convertView.findViewById(viewId);
                viewRes.put(viewId, view);
            }
            return view;
        }
    }

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

    /**
     * 抽象方法，绑定数据。因为不知道子类会绑定哪些数据，所以公开一个抽象方法让子类去实现数据绑定
     * View
     */
    public abstract void onBindDataToView(DataHodler hodler, T t);

    /**
     * 抽象方法，自定义数据过滤规则。
     */
    public abstract List<T> onFilterRule(String prefixString, List<T> unfilteredValues);
}
