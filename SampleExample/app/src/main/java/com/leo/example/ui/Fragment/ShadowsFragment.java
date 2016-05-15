package com.leo.example.ui.Fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.leo.example.R;
import com.leo.example.callback.DataCallBack;
import com.leo.example.info.SubjectsInfo;
import com.leo.example.ui.adapter.list.ShadowListAdapter;
import com.leo.example.util.DouBanApiUtil;
import com.leo.example.util.ToastUtil;
import com.leolibrary.ui.base.Fragment.BaseFragment;

import java.util.List;

/**
 * Created by leo on 16/5/14.
 * 引用Fragment
 */
public class ShadowsFragment extends BaseFragment {
    public int postion;
    public RecyclerView rvShadow;
    private ShadowListAdapter shadowListAdapter;

    public static ShadowsFragment getInstance(int postion) {
        ShadowsFragment fragment = new ShadowsFragment();
        fragment.setPostion(postion);
        return fragment;
    }


    public void setPostion(int postion) {
        this.postion = postion;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shadow;
    }

    @Override
    public void initView() {
        rvShadow = (RecyclerView) getView().findViewById(R.id.rv_shadow);
        shadowListAdapter = new ShadowListAdapter(getContext(), postion);
        rvShadow.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvShadow.setAdapter(shadowListAdapter);
    }

    @Override
    public void initData() {
        DouBanApiUtil.LoadRepoData(getContext(), new DataCallBack<List<SubjectsInfo>>() {
            @Override
            public void onSuccess(final List<SubjectsInfo> subjectsInfos) {
                shadowListAdapter.addAll(subjectsInfos);
                shadowListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Throwable t) {
                ToastUtil.showMessage(getContext(), t.getMessage());
            }
        });
    }

    @Override
    public void initListener() {

    }
}
