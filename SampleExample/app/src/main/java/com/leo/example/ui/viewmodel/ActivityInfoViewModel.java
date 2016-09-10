package com.leo.example.ui.viewmodel;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.View;

import com.leo.example.Constans;
import com.leo.example.R;
import com.leolibrary.callback.LayoutId;

/**
 * Created by leo on 16/5/14.
 * Activity - 实体类
 */
public class ActivityInfoViewModel extends BaseObservable implements LayoutId {
    private ObservableField<String> name;
    private ObservableField<Class> mClass;
    private ObservableField<String> className;

    public ActivityInfoViewModel(String name, Class mClass) {
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


    public View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.equals(getName(), "3D画廊效果实现")) {
                    Intent intent = new Intent(v.getContext(), getmClass());
                    intent.putExtra(Constans.IS_3D, true);
                    v.getContext().startActivity(intent);
                } else {
                    v.getContext().startActivity(new Intent(v.getContext(), getmClass()));
                }
            }
        };
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_rv_list;
    }
}