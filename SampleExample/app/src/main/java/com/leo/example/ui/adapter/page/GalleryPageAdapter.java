package com.leo.example.ui.adapter.page;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leo.example.R;
import com.leo.example.info.SubjectsInfo;
import com.leolibrary.utils.image.PhotoLoader;

import java.util.ArrayList;


/**
 * Created by leo on 16/5/7.
 */
public class GalleryPageAdapter extends PagerAdapter {
    private ArrayList<SubjectsInfo> subjectsInfos;
    private Context context;
    private int layoutId;

    public GalleryPageAdapter(ArrayList<SubjectsInfo> subjectsInfos, Context context, int layoutId) {
        this.subjectsInfos = subjectsInfos;
        this.context = context;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return subjectsInfos.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        view.setTag(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_movie);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        SubjectsInfo subjectsInfo = subjectsInfos.get(position);
        title.setText(subjectsInfo.getTitle());
        PhotoLoader.display(container.getContext(), imageView, subjectsInfo.getImages().getLarge(), context.getResources().getDrawable(R.mipmap.ic_loading));
        container.addView(view);
        return view;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}
