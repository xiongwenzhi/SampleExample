package com.leo.example.ui.adapter.page;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.leo.example.ui.Fragment.ShadowsFragment;

/**
 * Created by leo on 16/5/14.
 * 阴影效果
 */
public class ShadowPageAdapter extends FragmentPagerAdapter {
    private static String title[] = {"shape阴影效果", "cardView阴影效果"};
    private SparseArray<ShadowsFragment> fragmentSparseArray = new SparseArray<>();

    public ShadowPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    private ShadowsFragment getFragment(int position) {
        ShadowsFragment fragment = fragmentSparseArray.get(position);
        if (fragment == null) {
            fragment = ShadowsFragment.getInstance(position);
            fragmentSparseArray.put(position, fragment);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
