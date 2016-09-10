package com.leo.example.ui.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.View;

import com.leo.example.R;
import com.leo.example.info.SubjectsInfo;
import com.leo.example.util.ToastUtil;
import com.leolibrary.callback.LayoutId;
import com.leolibrary.ui.base.viewhodler.BaseDataViewHodler;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by leo on 16/9/11.
 * item viewModel
 */
public class ItemSubjectsInfoViewModel extends BaseObservable implements LayoutId {
    private int layoutId;
    private BaseDataViewHodler<ViewDataBinding> holder;
    private SubjectsInfo subjectsInfo;
    private ObservableField<String> title = new ObservableField<>();
    private ObservableField<String> imageUrl = new ObservableField<>();
    private int imageHeight = 0;
    private Action1<Integer> action1;

    public ItemSubjectsInfoViewModel(int layoutId, SubjectsInfo subjectsInfo) {
        this.layoutId = layoutId;
        this.subjectsInfo = subjectsInfo;
        this.imageUrl.set(subjectsInfo.getImages().getLarge());
        this.title.set(subjectsInfo.getTitle());
        Log.e("ItemSubjectsInfoViewModel:title:", title.get());
        Log.e("ItemSubjectsInfoViewModel:imageUrl:", imageUrl.get());
    }

    public Action1<Integer> getAction1() {
        return action1;
    }

    public void setAction1(Action1<Integer> action1) {
        this.action1 = action1;
    }

    public BaseDataViewHodler<ViewDataBinding> getHolder() {
        return holder;
    }

    public void setHolder(BaseDataViewHodler<ViewDataBinding> holder) {
        this.holder = holder;
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
                if (action1 != null && layoutId == R.layout.item_rv_image) {
                    action1.call(holder.getAdapterPosition());
                } else {
                    ToastUtil.showMessage(v.getContext(), title.get());
                }
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
        return toViewModel(data, layoutId, 0, null);
    }

    /**
     * toViewModel
     *
     * @param data
     * @param layoutId
     * @return
     */
    public static List<ItemSubjectsInfoViewModel> toViewModel(List<SubjectsInfo> data, int layoutId, Action1<Integer> action1) {
        return toViewModel(data, layoutId, 0, action1);
    }


    /**
     * toViewModel
     *
     * @param data
     * @param imageHeight
     * @param layoutId
     * @return
     */
    public static List<ItemSubjectsInfoViewModel> toViewModel(List<SubjectsInfo> data, int layoutId, int imageHeight, Action1<Integer> action1) {
        List<ItemSubjectsInfoViewModel> viewModels = new ArrayList<>();
        for (SubjectsInfo info : data) {
            ItemSubjectsInfoViewModel viewModel = new ItemSubjectsInfoViewModel(layoutId, info);
            viewModel.setAction1(action1);
            viewModel.setImageHeight(imageHeight);
            viewModels.add(viewModel);
        }
        return viewModels;
    }
}
