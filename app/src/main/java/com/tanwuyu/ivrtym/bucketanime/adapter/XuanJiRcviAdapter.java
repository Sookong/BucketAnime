package com.tanwuyu.ivrtym.bucketanime.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Video;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ivrty on 2017/3/30.
 */

public class XuanJiRcviAdapter extends RecyclerView.Adapter<XuanJiRcviAdapter.ViewHolder> {
    Context mContext;
    List<Video> videos;

    interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public XuanJiRcviAdapter(Context mContext, List<Video> videos) {
        this.mContext = mContext;
        this.videos = videos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_xuan_ji_num)
        TextView tvXuanJiNum;
        @BindView(R.id.tv_xuan_ji_name)
        TextView tvXuanJiName;
        @BindView(R.id.container_xuan_ji)
        LinearLayout containerXuanJi;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
