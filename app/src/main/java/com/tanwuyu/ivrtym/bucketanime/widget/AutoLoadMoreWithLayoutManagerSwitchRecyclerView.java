package com.tanwuyu.ivrtym.bucketanime.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * Created by ivrty on 2017/4/19.
 */

public class AutoLoadMoreWithLayoutManagerSwitchRecyclerView extends AutoLoadMoreRecyclerView {
    int rcvMode;
    OnSwitchLayoutManagerListener mOnSwitchLayoutManagerListener;


    public interface OnSwitchLayoutManagerListener {
        void onSwitchLayout(LayoutManager layoutManager);
    }


    public AutoLoadMoreWithLayoutManagerSwitchRecyclerView(Context context) {
        super(context);
    }

    public AutoLoadMoreWithLayoutManagerSwitchRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoLoadMoreWithLayoutManagerSwitchRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //设置布局管理器切换监听
    public void setOnSwitchLayoutManagerListener(OnSwitchLayoutManagerListener onSwitchLayoutManagerListener) {
        mOnSwitchLayoutManagerListener = onSwitchLayoutManagerListener;
    }


    //切换布局管理器方法
    public void switchLayoutManager(LayoutManager newLayoutManager) {
        LayoutManager oldLayoutManager = getLayoutManager();
        int firstVisiablePosition;
        firstVisiablePosition = getFirstVisiableItemPosition();

        //如果需要根据LayoutManager切换item布局,设置回调,由adapter处理
        if (mOnSwitchLayoutManagerListener != null)
            mOnSwitchLayoutManagerListener.onSwitchLayout(newLayoutManager);

        setLayoutManager(newLayoutManager);


        scrollToPosition(firstVisiablePosition);

    }


    public int getFirstVisiableItemPosition() {
        int firstVisiableItemPosition;
        LayoutManager layoutManager = getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            firstVisiableItemPosition = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof LinearLayoutManager) {
            firstVisiableItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        /*else if (layoutManager instanceof StaggeredGridLayoutManager){
            //获得固定行or列数
            int spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
            int[] into = new int[spanCount];
            firstVisiableItemPosition = ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(into);
        }*/
        else {
            firstVisiableItemPosition = 0;
        }

        return firstVisiableItemPosition;
    }


}
