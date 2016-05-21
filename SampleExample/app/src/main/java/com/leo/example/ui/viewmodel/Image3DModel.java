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
public class Image3DModel implements LayoutId {
    private ImageInfo imageInfo;
    private int layoutId = -1;

    public Image3DModel(ImageInfo imageInfo, int layoutId) {
        this.imageInfo = imageInfo;
        this.layoutId = layoutId;
    }

    @Override
    public int getItemLayoutId() {
        if (layoutId == -1) {
            return R.layout.item_3d_gallery_view;
        }
        return layoutId;
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
