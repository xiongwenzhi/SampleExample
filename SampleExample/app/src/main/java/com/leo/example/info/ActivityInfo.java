package com.leo.example.info;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.leo.example.R;
import com.leolibrary.callback.LayoutId;

/**
 * Created by leo on 16/5/14.
 * Activity - 实体类
 */
public class ActivityInfo extends BaseObservable implements LayoutId{
    private ObservableField<String> name;
    private ObservableField<Class> mClass;
    private ObservableField<String> className;

    public ActivityInfo(String name, Class mClass) {
        this.name = new ObservableField<>(name);
        this.mClass = new ObservableField<>(mClass);
        this.className = new ObservableField<>(mClass.getName());
    }

    public String getClassName() {
        return className.get();
    }

    public void setClassName(String className) {
        this.className = new ObservableField<>(className);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new ObservableField<>(name);
    }

    public Class getmClass() {
        return mClass.get();
    }

    @Override
    public String toString() {
        return "ActivityInfo{" +
                "name='" + name + '\'' +
                ", mClass=" + mClass +
                '}';
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_rv_list;
    }
}