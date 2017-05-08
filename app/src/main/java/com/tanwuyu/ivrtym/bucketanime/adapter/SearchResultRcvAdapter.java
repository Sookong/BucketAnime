package com.tanwuyu.ivrtym.bucketanime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ivrty on 2017/3/19.
 */

public class SearchResultRcvAdapter extends RecyclerView.Adapter<SearchResultRcvAdapter.Holder> {
    List<Anime> results;
    Context mContext;

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public SearchResultRcvAdapter(List<Anime> results, Context mContext) {
        this.results = results;
        this.mContext = mContext;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anime_in_list, parent, false);
        return new Holder(holderView);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });

        //holder.tvTitleItemSearch.setText(results.get(position).);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class Holder extends RecyclerView.ViewHolder {

/*        @BindView(R.id.iv_item_search)
        ImageView ivItemSearch;
        @BindView(R.id.tv_title_item_search)
        TextView tvTitleItemSearch;
        @BindView(R.id.tv_episode_item_search)
        TextView tvEpisodeItemSearch;
        @BindView(R.id.tv_category_item_search)
        TextView tvCategoryItemSearch;
        @BindView(R.id.tv_desc_item_search)
        TextView tvDescItemSearch;*/

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
