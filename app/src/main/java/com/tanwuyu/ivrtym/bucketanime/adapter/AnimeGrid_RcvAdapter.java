package com.tanwuyu.ivrtym.bucketanime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;

import java.util.List;

/**
 * Created by ivrty on 2017/4/19.
 */

public class AnimeGrid_RcvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Anime> animes;
    Context mContext;
    int rcvMode;

    OnItemClickListener mOnItemClickListener;

    public final static int RCV_MODE_LIST = 1;
    public final static int RCV_MODE_GRID = 2;

    public AnimeGrid_RcvAdapter(List<Anime> animes, int rcvMode,Context mContext) {
        this.animes = animes;
        this.mContext = mContext;
        this.rcvMode = rcvMode;
    }

    public interface OnItemClickListener{
        void onItemClick(View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return rcvMode;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view;
        if (viewType==RCV_MODE_LIST){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_anime_in_list,null);
            holder = new ListViewHolder(view);
        }else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_anime_in_grid,null);
            holder = new GridViewHolder(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Anime anime = animes.get(position);
        if (holder instanceof ListViewHolder){

        }else {

        }
    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    public void switchRcvMode(int rcvMode){
        this.rcvMode = rcvMode;
        if (rcvMode==RCV_MODE_LIST){

        }
        notifyDataSetChanged();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        public ListViewHolder(View itemView) {
            super(itemView);
        }
    }
    class GridViewHolder extends RecyclerView.ViewHolder{

        public GridViewHolder(View itemView) {
            super(itemView);
        }
    }

}
