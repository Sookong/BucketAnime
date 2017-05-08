package com.tanwuyu.ivrtym.bucketanime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.tanwuyu.ivrtym.bucketanime.model.AnimeReply;

import java.util.List;

/**
 * Created by ivrty on 2017/4/27.
 */

public class AnimeReplyRcvAdapter extends RecyclerView.Adapter {
    List<AnimeReply> animeReplies;
    Context mContext;

    public AnimeReplyRcvAdapter(List<AnimeReply> animeReplies, Context mContext) {
        this.animeReplies = animeReplies;
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
