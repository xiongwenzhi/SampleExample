package com.leo.example.ui.Fragment;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leo.example.R;
import com.leo.example.databinding.FragmentShadowBinding;
import com.leo.example.dto.ListDTO;
import com.leo.example.info.SubjectsInfo;
import com.leo.example.ui.adapter.list.ShadowListAdapter;
import com.leo.example.util.DouBanApiUtil;
import com.leolibrary.ui.base.Fragment.BaseFragment;

import rx.functions.Action1;

/**
 * Created by leo on 16/5/14.
 * Card - Fragment
 */
public class ShadowsFragment extends BaseFragment {
    private FragmentShadowBinding binding;
    public int postion;
    private ShadowListAdapter shadowListAdapter;

    public static ShadowsFragment getInstance(int postion) {
        ShadowsFragment fragment = new ShadowsFragment();
        fragment.setPostion(postion);
        return fragment;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return binding.getRoot();
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
        shadowListAdapter = new ShadowListAdapter(getContext(), postion);
        binding.rvShadow.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.rvShadow.setAdapter(shadowListAdapter);
    }

    @Override
    public void initData() {
        DouBanApiUtil.LoadRepoData(getContext(), new Action1<ListDTO<SubjectsInfo>>() {
            @Override
            public void call(ListDTO<SubjectsInfo> subjectsInfoListDTO) {
                shadowListAdapter.addAll(subjectsInfoListDTO.getList());
                shadowListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void initListener() {

    }
}
