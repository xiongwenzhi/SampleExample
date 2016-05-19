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
import com.leolibrary.ui.base.adapter.BasePageAdapter;
import com.leolibrary.utils.image.PhotoLoader;

import java.util.ArrayList;


/**
 * Created by leo on 16/5/7.
 */
public class GalleryPageAdapter extends BasePageAdapter {
    private ArrayList<SubjectsInfo> subjectsInfos;
    private Context context;
    private int layoutId;

    public GalleryPageAdapter(ArrayList<SubjectsInfo> subjectsInfos, Context context, int layoutId) {
        this.subjectsInfos = subjectsInfos;
        this.context = context;
        this.layoutId = layoutId;
    }

    public ArrayList<SubjectsInfo> getSubjectsInfos() {
        return subjectsInfos;
    }

    @Override
    public int getCount() {
        if (subjectsInfos.size() <= 0) {
            return 0;
        }
        return Integer.MAX_VALUE;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int pos = position % subjectsInfos.size();
        SubjectsInfo subjectsInfo = subjectsInfos.get(pos);
        View view = LayoutInflater.from(context).inflate(layoutId, null);
        view.setTag(pos);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_movie);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
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

    @Override
    public int getPageDataSize() {
        return subjectsInfos == null ? 0 : subjectsInfos.size();
    }
}
