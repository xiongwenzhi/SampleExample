package com.leo.example.info;

/**
 * Created by leo on 16/5/14.
 */
public class ActivityInfo {
    private String name;
    private Class mClass;

    public ActivityInfo(String name, Class mClass) {
        this.name = name;
        this.mClass = mClass;
    }

    @Override
    public String toString() {
        return "ActivityInfo{" +
                "name='" + name + '\'' +
                ", mClass=" + mClass +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getmClass() {
        return mClass;
    }

    public void setmClass(Class mClass) {
        this.mClass = mClass;
    }

}