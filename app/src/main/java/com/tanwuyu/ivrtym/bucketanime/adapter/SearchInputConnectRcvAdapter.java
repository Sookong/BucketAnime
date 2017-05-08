package com.tanwuyu.ivrtym.bucketanime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ivrty on 2017/4/20.
 */

public class SearchInputConnectRcvAdapter extends RecyclerView.Adapter<SearchInputConnectRcvAdapter.ViewHolder> {
    List<Anime> animes;
    Context mContext;
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public SearchInputConnectRcvAdapter(List<Anime> animes, Context mContext) {
        this.animes = animes;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(mContext).inflate(R.layout.item_search_input_connect, parent, false);
        return new ViewHolder(holderView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String animeName = animes.get(position).getAnimeName();
        holder.tvSearchInputConnect.setText(animeName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setTag(animeName);
                onItemClickListener.onItemCLick(v);
            }
        });
    }


    @Override
    public int getItemCount() {
        return animes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_search_input_connect)
        TextView tvSearchInputConnect;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
