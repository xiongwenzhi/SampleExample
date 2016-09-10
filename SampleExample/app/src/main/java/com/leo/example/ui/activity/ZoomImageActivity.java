package com.leo.example.ui.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;

import com.leo.example.R;
import com.leo.example.databinding.ActivityZoomImageBinding;
import com.leo.example.dto.ListDTO;
import com.leo.example.info.SubjectsInfo;
import com.leo.example.ui.adapter.list.ShadowListAdapter;
import com.leo.example.ui.viewmodel.ItemSubjectsInfoViewModel;
import com.leo.example.util.DouBanApiUtil;
import com.leo.example.wdight.SpacesItemDecoration;
import com.leolibrary.ui.base.activity.BaseActivity;
import com.leolibrary.utils.Dimens;
import com.leolibrary.utils.SystemUtil;

import rx.functions.Action1;

/**
 * Created by leo on 16/9/10.
 * 仿朋友圈图片查看效果
 */
public class ZoomImageActivity extends BaseActivity<ActivityZoomImageBinding> {
    private ShadowListAdapter shadowListAdapter;
    private int pading = 0;
    private int imageHeight = 0;

    @Override
    public ActivityZoomImageBinding beforInitView() {
        return DataBindingUtil.setContentView(this, R.layout.activity_zoom_image);
    }

    @Override
    public void initView() {
        imageHeight = (SystemUtil.getScreenWidth(this) - pading * 2 * 4) / 3;
        pading = Dimens.getDimensionPixelOffset(R.dimen.dp_4);
        shadowListAdapter = new ShadowListAdapter(this);
        binding.rvView.addItemDecoration(new SpacesItemDecoration(pading, 3));
        binding.rvView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.rvView.setAdapter(shadowListAdapter);
    }

    @Override
    public void initData() {
        DouBanApiUtil.LoadRepoData(this, new Action1<ListDTO<SubjectsInfo>>() {
            @Override
            public void call(ListDTO<SubjectsInfo> subjectsInfoListDTO) {
                shadowListAdapter.addAll(ItemSubjectsInfoViewModel.toViewModel(subjectsInfoListDTO.getList(), R.layout.item_rv_image, imageHeight));
                shadowListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initListener() {

    }
}
