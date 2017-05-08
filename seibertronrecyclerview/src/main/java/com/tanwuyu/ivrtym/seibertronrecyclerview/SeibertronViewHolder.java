package com.tanwuyu.ivrtym.seibertronrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ivrty on 2017/4/29.
 */

public class SeibertronViewHolder extends RecyclerView.ViewHolder {
    Context mContext;
    SparseArray<View> childViews;
    View mItemView;
    int mViewHolderResourceId;

    public SeibertronViewHolder(View itemView, int viewHolderResourceId, Context context) {
        super(itemView);
        mContext = context;
        mItemView = itemView;
        mViewHolderResourceId = viewHolderResourceId;
        childViews = new SparseArray<>();

    }

    public static SeibertronViewHolder creatViewHolder(View itemView, int viewHolderResourceId, Context context) {
        return new SeibertronViewHolder(itemView, viewHolderResourceId, context);
    }

    public static SeibertronViewHolder creatViewHolder(int layoutResourceId, ViewGroup parent, Context context) {
        View itemView = LayoutInflater.from(context).inflate(layoutResourceId, parent, false);
        return creatViewHolder(itemView, layoutResourceId, context);
    }

    public int getLayoutRecourceId(){
        return mViewHolderResourceId;
    }


    //根据ViewHolder中保存的子view引用
    public <T extends View> T getChildView(int childId) {
        View childView = childViews.get(childId);
        //第一次调用childview还未添加至引用map,添加
        if (childView == null) {
            childView = itemView.findViewById(childId);
            childViews.put(childId, childView);
        }
        return (T) childView;

    }
}
