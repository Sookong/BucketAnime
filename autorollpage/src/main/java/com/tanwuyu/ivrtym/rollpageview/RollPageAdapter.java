package com.tanwuyu.ivrtym.rollpageview;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by ivrtym on 2017/3/2.
 */

public class RollPageAdapter extends PagerAdapter {
    List<View> views;

    public RollPageAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        //return Integer.MAX_VALUE; 个别机型异常,数量太大
        return 100000;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position%views.size());
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent!=null){
            parent.removeAllViews();
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position % views.size()));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
