package com.leo.example.ui.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.example.R;
import com.leo.example.info.ImageInfo;
import com.leolibrary.callback.LayoutId;
import com.leolibrary.utils.image.PhotoLoader;

/**
 * Created by leo on 16/5/19.
 * 图片ViewModel
 */
public class ImageViewModel implements LayoutId {
    private ImageInfo imageInfo;

    public ImageViewModel(ImageInfo imageInfo) {
        this.imageInfo = imageInfo;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_3d_gallery_view;
    }

    @Override
    public View getView(Context context) {
        View view = LayoutInflater.from(context).inflate(getItemLayoutId(), null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_movie);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        title.setText(imageInfo.getTitle());
        PhotoLoader.display(context, imageView, imageInfo.getImageUrl(), context.getResources().getDrawable(R.mipmap.ic_loading));
        return view;
    }
}
