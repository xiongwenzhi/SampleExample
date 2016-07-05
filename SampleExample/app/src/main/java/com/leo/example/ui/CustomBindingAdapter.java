package com.leo.example.ui;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by leo on 16/7/5.
 */
public class CustomBindingAdapter {
    @BindingAdapter("android:src")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
    }

    @BindingAdapter("android:src")
    public static void loadImage(ImageView imageView, int resId) {
        imageView.setImageResource(resId);
    }

}
