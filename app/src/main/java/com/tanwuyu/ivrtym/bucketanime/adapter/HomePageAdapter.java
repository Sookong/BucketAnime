package com.tanwuyu.ivrtym.bucketanime.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by ivrtym on 2017/3/17.
 */

public class HomePageAdapter extends FragmentPagerAdapter {
    Context mContext;
    private List<Fragment> fragments;
    private String tabTitles[] = new String[]{"推荐","新番","分类","专题","发现"};
    public HomePageAdapter(List<Fragment> fragments, FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
