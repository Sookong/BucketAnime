package com.tanwuyu.ivrtym.bucketanime.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Fenlei;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ivrtym on 2017/3/8.
 */

public class FenLeiRcvAdapter extends RecyclerView.Adapter<FenLeiRcvAdapter.FenLeiViewHolder> {
    Context context;
    List<Fenlei> fenleiList;
    public OnItemClickListener onItemClickListener;

    public FenLeiRcvAdapter(List<Fenlei> fenleiList, Context context) {
        this.fenleiList = fenleiList;
        this.context = context;
        Log.e("Adapter","creating");
    }

    @Override
    public FenLeiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fenlei, parent, false);
        FenLeiViewHolder viewHolder = new FenLeiViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final FenLeiViewHolder holder, int position) {
        Fenlei fenlei = fenleiList.get(position);
        String name = fenlei.getFenleiName();
        String url = fenlei.getImgUrl();
        if (!"".equals(name))
            holder.tvItemFenlei.setText(fenlei.getFenleiName());
        Log.e("Text",fenlei.getFenleiName());
        if (!"".equals(url))
            //Picasso.with(context).load(fenlei.getImgUrl()).error(R.mipmap.ic_launcher).into(holder.ivItemFenlei);

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return fenleiList.size();
    }

    class FenLeiViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_fenlei)
        ImageView ivItemFenlei;
        @BindView(R.id.tv_item_fenlei)
        TextView tvItemFenlei;


        public FenLeiViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
