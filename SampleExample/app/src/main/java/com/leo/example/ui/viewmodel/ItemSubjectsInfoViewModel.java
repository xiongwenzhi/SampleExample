package com.leo.example.ui.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.leo.example.info.SubjectsInfo;
import com.leo.example.util.ToastUtil;
import com.leolibrary.callback.LayoutId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leo on 16/9/11.
 * item viewModel
 */
public class ItemSubjectsInfoViewModel extends BaseObservable implements LayoutId {
    private int layoutId;
    private SubjectsInfo subjectsInfo;
    private ObservableField<String> title = new ObservableField<>();
    private ObservableField<String> imageUrl = new ObservableField<>();
    private int imageHeight = 0;

    public ItemSubjectsInfoViewModel(int layoutId, SubjectsInfo subjectsInfo) {
        this.layoutId = layoutId;
        this.subjectsInfo = subjectsInfo;
        this.imageUrl.set(subjectsInfo.getImages().getLarge());
        this.title.set(subjectsInfo.getTitle());
        Log.e("ItemSubjectsInfoViewModel:title:",title.get());
        Log.e("ItemSubjectsInfoViewModel:imageUrl:",imageUrl.get());
    }

    @Override
    public int getItemLayoutId() {
        return layoutId;
    }

    public ObservableField<String> getTitle() {
        return title;
    }

    public ObservableField<String> getImageUrl() {
        return imageUrl;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showMessage(v.getContext(), title.get());
            }
        };
    }


    /**
     * toViewModel
     *
     * @param data
     * @param layoutId
     * @return
     */
    public static List<ItemSubjectsInfoViewModel> toViewModel(List<SubjectsInfo> data, int layoutId) {
        return toViewModel(data, layoutId, 0);
    }


    /**
     * toViewModel
     *
     * @param data
     * @param imageHeight
     * @param layoutId
     * @return
     */
    public static List<ItemSubjectsInfoViewModel> toViewModel(List<SubjectsInfo> data, int layoutId, int imageHeight) {
        List<ItemSubjectsInfoViewModel> viewModels = new ArrayList<>();
        for (SubjectsInfo info : data) {
            ItemSubjectsInfoViewModel viewModel = new ItemSubjectsInfoViewModel(layoutId, info);
            viewModel.setImageHeight(imageHeight);
            viewModels.add(viewModel);
        }
        return viewModels;
    }
}
