package com.tanwuyu.ivrtym.bucketanime.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import java.util.Collection;

/**
 * Created by ivrty on 2017/4/19.
 */

public class AutoLoadMoreRecyclerView extends RecyclerView {
    boolean isLoadingMore = false;

    OnAutoLoadMoreListner mOnAntoLoadMoreListener;

    public interface OnAutoLoadMoreListner {
        void onLoadMore();
    }

    public AutoLoadMoreRecyclerView(Context context) {
        super(context);
    }

    public AutoLoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoLoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //设置自动加载监听
    public void setOnAutoLoadMoreListener(OnAutoLoadMoreListner autoLoadMoreListener) {
        mOnAntoLoadMoreListener = autoLoadMoreListener;
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if (!canScrollVertically(-1) && !isLoadingMore) {
            startLoadMore();
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

    }

    public void startLoadMore() {
        if (mOnAntoLoadMoreListener != null) {
            isLoadingMore = true;
            mOnAntoLoadMoreListener.onLoadMore();
        }

    }


    public void notifyLoadComplete() {
        isLoadingMore = false;
    }

}
