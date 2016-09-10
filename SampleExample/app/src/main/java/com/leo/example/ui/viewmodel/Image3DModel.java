package com.leo.example.ui.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.leo.example.R;
import com.leo.example.info.ImageInfo;
import com.leo.example.util.ToastUtil;
import com.leolibrary.callback.LayoutId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 16/5/19.
 * 图片ViewModel
 */
public class Image3DModel extends BaseObservable implements LayoutId {
    private ImageInfo imageInfo;
    private int layoutId = -1;
    private ObservableField<String> title = new ObservableField<>();
    private ObservableField<String> imageUrl = new ObservableField<>();

    public Image3DModel(ImageInfo imageInfo, int layoutId) {
        this.imageInfo = imageInfo;
        this.layoutId = layoutId;
        this.title.set(imageInfo.getTitle());
        this.imageUrl.set(imageInfo.getImageUrl());
    }

    @Override
    public int getItemLayoutId() {
        if (layoutId == -1) {
            return R.layout.item_3d_gallery_view;
        }
        return layoutId;
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    public void setTitle(ObservableField<String> title) {
        this.title = title;
    }

    public ObservableField<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ObservableField<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ImageInfo getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(ImageInfo imageInfo) {
        this.imageInfo = imageInfo;
    }

    /**
     * 图片测试数据
     */
    public static List<Image3DModel> getImageModel(String[] images, int layoutId) {
        List<Image3DModel> list = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            list.add(new Image3DModel(new ImageInfo(String.valueOf(i + 1), images[i]), layoutId));
        }
        return list;
    }

    public View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMessage(v.getContext(), title.get());
            }
        };
    }
}
