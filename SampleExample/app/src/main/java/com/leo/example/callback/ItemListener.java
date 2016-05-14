package com.leo.example.callback;

/**
 * Created by leo on 16/5/14.
 */
public interface ItemListener<T> {
    void itemOnClick(int position, T t);
}
