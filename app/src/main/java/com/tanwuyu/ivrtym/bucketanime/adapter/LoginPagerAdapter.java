package com.tanwuyu.ivrtym.bucketanime.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ivrty on 2017/4/26.
 */

public class LoginPagerAdapter extends PagerAdapter {
    List<View> views;
    Context mContext;

    public LoginPagerAdapter(List<View> views, Context mContext) {
        this.views = views;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent!=null){
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }
}
