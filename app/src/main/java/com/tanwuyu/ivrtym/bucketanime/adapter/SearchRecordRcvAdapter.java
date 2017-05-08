package com.tanwuyu.ivrtym.bucketanime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tanwuyu.ivrtym.bucketanime.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ivrty on 2017/4/20.
 */

public class SearchRecordRcvAdapter extends RecyclerView.Adapter<SearchRecordRcvAdapter.ViewHolder> {
    List<String> searchRecords;
    Context mContext;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view);
    }

    public SearchRecordRcvAdapter(List<String> searchRecords, Context mContext) {
        this.searchRecords = searchRecords;
        this.mContext = mContext;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public SearchRecordRcvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View holderView = LayoutInflater.from(mContext).inflate(R.layout.item_search_record,parent,false);
        return new ViewHolder(holderView);
    }

    @Override
    public void onBindViewHolder(SearchRecordRcvAdapter.ViewHolder holder, int position) {
        final String searchRecord = searchRecords.get(position);
        holder.tvSearchRecord.setText(searchRecord);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setTag(searchRecord);
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchRecords.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_search_record)
        TextView tvSearchRecord;
        @BindView(R.id.item_search_record)
        LinearLayout itemSearchRecord;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
