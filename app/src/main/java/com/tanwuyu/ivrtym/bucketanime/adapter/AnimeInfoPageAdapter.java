package com.tanwuyu.ivrtym.bucketanime.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ivrty on 2017/4/9.
 */

public class AnimeInfoPageAdapter extends FragmentPagerAdapter {
    Context context;
    List<Fragment> fragments;
    private String titles[] = new String[]{"选集,相关推荐"};

    public AnimeInfoPageAdapter(FragmentManager fm, Context context, List<Fragment> fragments) {
        super(fm);
        this.context = context;
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
        return titles[position];
    }
}
