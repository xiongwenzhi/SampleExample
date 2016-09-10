package com.leo.example.ui.viewmodel;

import android.app.Activity;
import android.databinding.ObservableField;
import android.view.View;

import com.leo.example.R;
import com.leolibrary.callback.LayoutId;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by leo on 16/9/11.
 * 图片预览
 */
public class PhotoZoomViewModel implements LayoutId {
    private ObservableField<String> imageUrl = new ObservableField<>();

    public PhotoZoomViewModel(String imageUrl) {
        this.imageUrl.set(imageUrl);
    }

    public ObservableField<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ObservableField<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int getItemLayoutId() {
        return R.layout.item_show_photo_image;
    }

    public PhotoViewAttacher.OnPhotoTapListener OnViewTapListener() {
        return new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                ((Activity) view.getContext()).finish();
            }
        };
    }

    /**
     * toViewModel
     *
     * @param infoViewModels
     * @return
     */
    public static List<PhotoZoomViewModel> toViewModel(List<ItemSubjectsInfoViewModel> infoViewModels) {
        List<PhotoZoomViewModel> viewModels = new ArrayList<>();
        for (ItemSubjectsInfoViewModel viewModel : infoViewModels) {
            viewModels.add(new PhotoZoomViewModel(viewModel.getImageUrl().get()));
        }
        return viewModels;
    }
}
