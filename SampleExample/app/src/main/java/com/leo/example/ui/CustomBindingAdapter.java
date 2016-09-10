package com.leo.example.ui;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.leolibrary.utils.image.PhotoLoader;

/**
 * Created by leo on 16/7/5.
 */
public class CustomBindingAdapter {
    @BindingAdapter("android:src")
    public static void loadImage(ImageView imageView, String url) {
        PhotoLoader.displayImageLruCache(imageView, url, null);
    }

    @BindingAdapter("android:src")
    public static void loadImage(ImageView imageView, int resId) {
        imageView.setImageResource(resId);
    }

    /**
     * 设置View高度尺寸/适用于GridLayoutManage中item
     */
    @BindingAdapter(value = {"layoutHeight"}, requireAll = false)
    public static void setViewLayoutParams(View view, int layoutHeight) {
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutHeight;
    }

}
