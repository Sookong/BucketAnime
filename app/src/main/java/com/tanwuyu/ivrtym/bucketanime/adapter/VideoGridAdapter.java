package com.tanwuyu.ivrtym.bucketanime.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Video;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ivrty on 2017/4/7.
 */

public class VideoGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Video> mDatas;

    int historyIndex;

    static final int TYPE_ITEM = 3;
    static final int TYPE_ERROR = 5;

    public VideoGridAdapter(Context context, List<Video> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video_in_grid, parent);
        return new NormalHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Video video = mDatas.get(position);
        ((NormalHolder) holder).setTvVideoNum(video.getNumber())
                .setTvVideoName(video.getVideoName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(layoutManager);
    }

    class NormalHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_video_num)
        TextView tvVideoNum;
        @BindView(R.id.tv_video_name)
        TextView tvVideoName;
        @BindView(R.id.item_video)
        LinearLayout itemVideo;

        public NormalHolder(View itemView) {
            super(itemView);
        }

        public NormalHolder setTvVideoNum(int videoNum) {
            tvVideoNum.setText("第" + String.valueOf(videoNum) + "话");
            return this;
        }

        public NormalHolder setTvVideoName(String videoName) {
            tvVideoName.setText(videoName);
            return this;
        }

        public NormalHolder setBackGroundColor(int color) {
            itemVideo.setBackgroundColor(color);
            return this;
        }
    }

}
