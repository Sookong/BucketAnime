package com.tanwuyu.ivrtym.bucketanime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.HotSearch;

import java.util.List;

/**
 * Created by ivrty on 2017/4/21.
 */

public class SearchHotSearchRcvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<HotSearch> hotSearches;
    Context mContext;

    public SearchHotSearchRcvAdapter(List<HotSearch> hotSearches, Context mContext) {
        this.hotSearches = hotSearches;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
