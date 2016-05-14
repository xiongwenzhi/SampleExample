package com.leo.example.info;

import android.app.Activity;

import com.leo.example.enums.ActivityType;

/**
 * Created by leo on 16/5/14.
 */
public class ActivityInfo {
    private String name;
    private ActivityType type;
    private String className;

    public ActivityInfo(String name, ActivityType type, String className) {
        this.name = name;
        this.type = type;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "ActivityInfo{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", className='" + className + '\'' +
                '}';
    }
}
