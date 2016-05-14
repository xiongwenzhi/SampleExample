package com.leo.example.callback;

/**
 * Created by leo on 16/5/14.
 */
public interface DataCallBack<T> {

    public abstract void onSuccess(T t);

    public abstract void onFailure(Throwable t);
}
