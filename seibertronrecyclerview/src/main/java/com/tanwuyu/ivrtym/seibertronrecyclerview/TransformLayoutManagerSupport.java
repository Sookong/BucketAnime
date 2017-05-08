package com.tanwuyu.ivrtym.seibertronrecyclerview;

import android.support.v7.widget.RecyclerView;

/**
 * Created by ivrty on 2017/4/29.
 */

public abstract class TransformLayoutManagerSupport {
    abstract void transformToLinear();

    abstract void transformToGrid(int spanCount);

    abstract void transformToStaggeredGrid(int spanCount);

    abstract <T extends RecyclerView.LayoutManager> void ransformToCustomer(T t);
}
