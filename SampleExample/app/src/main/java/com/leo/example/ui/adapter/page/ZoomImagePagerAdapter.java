package com.leo.example.ui.adapter.page;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.widget.ImageView;

import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.leo.example.databinding.ItemShowPhotoImageBinding;
import com.leo.example.info.ImageSizeInfo;
import com.leo.example.ui.viewmodel.PhotoZoomViewModel;
import com.leolibrary.ui.base.adapter.common.PagerViewModelAdapter;
import com.leolibrary.ui.base.viewhodler.BaseDataViewHodler;
import com.leolibrary.utils.LruCacheUtils;
import com.leolibrary.utils.image.PhotoLoader;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by leo on 16/9/11.
 * 图片预览 - adapter
 */
public class ZoomImagePagerAdapter extends PagerViewModelAdapter<ItemShowPhotoImageBinding, PhotoZoomViewModel> {
    private SparseArray<ImageSizeInfo> infoSparseArray = new SparseArray<>();
    private SparseArray<PhotoView> viewSparseArray = new SparseArray<>();

    public ZoomImagePagerAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindDataToView(BaseDataViewHodler<ItemShowPhotoImageBinding> holder, int position, PhotoZoomViewModel photoViewModel) {
        String url = photoViewModel.getImageUrl().get();
        Bitmap bitmap = LruCacheUtils.getInstance().getBitmapFromMemCache(url);
        PhotoView photoView = holder.getBinding().showPhoto;
        viewSparseArray.put(position, photoView);
        if (bitmap != null) {
            cacheImageData(bitmap, url, position);
            photoView.setImageBitmap(bitmap);
        } else {
            PhotoLoader.displayImageLruCaches(photoView, url, getTarget(photoView, url, position));
        }
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public ImageSizeInfo getImageSizeInfo(int position) {
        return infoSparseArray.get(position);
    }


    /**
     * 将View对象置为null
     */
    public void onDestoryPhotoView() {
        for (int i = 0; i < viewSparseArray.size(); i++) {
            PhotoView view = viewSparseArray.get(i);
            if (view != null) {
                view = null;
            }
        }
        infoSparseArray.clear();
        viewSparseArray.clear();
    }


    /**
     * 获取BitmapImageViewTarget
     */
    private BitmapImageViewTarget getTarget(ImageView imageView, final String url, final int position) {
        return new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                super.setResource(resource);
                cacheImageData(resource, url, position);
            }
        };
    }


    /**
     * 缓存图片数据
     */
    private void cacheImageData(Bitmap resource, String url, int position) {
        ImageSizeInfo info = infoSparseArray.get(position);
        if (info == null) {
            info = new ImageSizeInfo();
        }
        if (resource != null) {
            info.setWidth(resource.getWidth());
            info.setHeight(resource.getHeight());
            infoSparseArray.put(position, info);
            LruCacheUtils.getInstance().addBitmapToMemoryCache(url, resource);
        }
    }
}
