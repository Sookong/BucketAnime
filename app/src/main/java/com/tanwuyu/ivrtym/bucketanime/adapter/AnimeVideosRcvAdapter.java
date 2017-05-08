package com.tanwuyu.ivrtym.bucketanime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Video;

import java.util.List;

/**
 * Created by ivrty on 2017/5/4.
 */

public class AnimeVideosRcvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Video> videos;
    Context mContext;

    public AnimeVideosRcvAdapter(List<Video> videos, Context mContext) {
        this.videos = videos;
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
        return videos.size();
    }
}
