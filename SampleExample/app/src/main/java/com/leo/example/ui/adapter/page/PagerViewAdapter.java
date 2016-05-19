package com.leo.example.ui.adapter.page;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.leo.example.ui.viewmodel.ImageViewModel;
import com.leolibrary.ui.base.adapter.PagerViewModelAdapter;

/**
 * Created by leo on 16/5/19.
 */
public class PagerViewAdapter extends PagerViewModelAdapter<ImageViewModel> {
    public PagerViewAdapter(Context context) {
        super(context);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public View getItemView(ViewGroup container, int position) {
        View view = get(position).getView(container.getContext());
        view.setTag(position);
        return view;
    }
}
