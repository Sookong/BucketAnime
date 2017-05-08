package com.tanwuyu.ivrtym.bucketanime.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.TuiJianData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ivrtym on 2017/3/16.
 */

public class TuiJianRcvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<TuiJianData> tuiJianDatas;
    Context context;
    private View headerView;

    private static final int ITEM_TYPE_HEAD = 11;
    private static final int ITEM_TYPE_NORMAL = 13;
    private static final int ITEM_TYPE_TITLE = 17;

    private static final int ITEM_TYPE_NORMAL_HOT = 1;
    private static final int ITEM_TYPE_NORMAL_UPDATE = 2;
    private static final int ITEM_TYPE_NORMAL_NEW = 3;
    private static final int ITEM_TYPE_NORMAL_OLD = 4;
    private static final int ITEM_TYPE_NORMAL_CHINA = 5;
    private static final int ITEM_TYPE_NORMAL_REAL = 6;


    public TuiJianRcvAdapter(List<TuiJianData> tuiJianDatas, Context context) {
        this.tuiJianDatas = tuiJianDatas;
        this.context = context;
    }

    public void setHeaderView(View view) {
        headerView = view;
        notifyItemInserted(0);
        //通知位置变化,防止错位
        notifyItemRangeChanged(0, getItemCount());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case ITEM_TYPE_HEAD:
                viewHolder = new HeaderHolder(headerView);
                break;
            case ITEM_TYPE_TITLE:
                View titleView = LayoutInflater.from(context).inflate(R.layout.item_tuijian_title, parent, false);
                viewHolder = new TitleHolder(titleView);
                break;
            default:
                View normalView = LayoutInflater.from(context).inflate(R.layout.item_tuijian, parent, false);
                viewHolder = new NormalHolder(normalView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //绑定HeadView
        if (getItemViewType(position) == ITEM_TYPE_HEAD)
            return;
        //绑定标题
        if (holder instanceof TitleHolder) {
            if (position == getTitlePositionOfType(ITEM_TYPE_NORMAL_HOT)) {
                ((TitleHolder) holder).setIvTitleLeft(R.drawable.ic_whatshot_red_500_24dp)
                        .setIvTitleRight(R.drawable.ic_autorenew_red_500_24dp)
                        .setTvTitleLeft("热门推荐")
                        .setTvTitleRight("换一波推荐");


            }
            if (position == getTitlePositionOfType(ITEM_TYPE_NORMAL_UPDATE)) {
                ((TitleHolder) holder).setIvTitleLeft(R.drawable.ic_whatshot_red_500_24dp)
                        .setIvTitleRight(R.drawable.ic_autorenew_red_500_24dp)
                        .setTvTitleLeft("最近更新")
                        .setTvTitleRight("换一波推荐");


            }
            if (position == getTitlePositionOfType(ITEM_TYPE_NORMAL_NEW)) {
                ((TitleHolder) holder).setIvTitleLeft(R.drawable.ic_whatshot_red_500_24dp)
                        .setIvTitleRight(R.drawable.ic_autorenew_red_500_24dp)
                        .setTvTitleLeft("新番推荐")
                        .setTvTitleRight("换一波推荐");


            }
            if (position == getTitlePositionOfType(ITEM_TYPE_NORMAL_OLD)) {
                ((TitleHolder) holder).setIvTitleLeft(R.drawable.ic_whatshot_red_500_24dp)
                        .setIvTitleRight(R.drawable.ic_autorenew_red_500_24dp)
                        .setTvTitleLeft("补番推荐")
                        .setTvTitleRight("换一波推荐");


            }
            if (position == getTitlePositionOfType(ITEM_TYPE_NORMAL_CHINA)) {
                ((TitleHolder) holder).setIvTitleLeft(R.drawable.ic_whatshot_red_500_24dp)
                        .setIvTitleRight(R.drawable.ic_autorenew_red_500_24dp)
                        .setTvTitleLeft("优秀国漫")
                        .setTvTitleRight("换一波推荐");


            }
            if (position == getTitlePositionOfType(ITEM_TYPE_NORMAL_REAL)) {
                ((TitleHolder) holder).setIvTitleLeft(R.drawable.ic_whatshot_red_500_24dp)
                        .setIvTitleRight(R.drawable.ic_autorenew_red_500_24dp)
                        .setTvTitleLeft("真人动漫")
                        .setTvTitleRight("换一波推荐");


            }

        }
        //绑定item
        if (holder instanceof NormalHolder) {
            int index = getRealIndexInList(position);
            TuiJianData data = tuiJianDatas.get(index);
            TuiJianData.ContentAnimeBean contentAnimeBean = data.getContentAnime();

            switch (data.getContentType()) {
                case 2:
                    TuiJianData.ContentVideoBean contentVideoBean = data.getContentVideo();
                    ((NormalHolder) holder).setIvTuijianContent("".equals(contentAnimeBean.getImgUrl()) ? contentVideoBean.getImgUrl() : contentAnimeBean.getImgUrl())
                            .setTvTuijianTitle(contentAnimeBean.getAnimeName())
                            .setTvTuijianDesc("第" + String.valueOf(contentVideoBean.getNumber()) + "话" + " " + contentVideoBean.getVideoName());
                    break;
                default:
                    ((NormalHolder) holder).setIvTuijianContent(contentAnimeBean.getImgUrl())
                            .setTvTuijianTitle(contentAnimeBean.getAnimeName())
                            .setTvTuijianDesc(contentAnimeBean.getLookPoint());
            }
        }
    }

    @Override
    public int getItemCount() {
        //item总数 = 数据列表长度 + 标题个数 + 是否有头布局
        return headerView == null ? tuiJianDatas.size() + 6 : tuiJianDatas.size() + 6 + 1;
    }

    @Override
    public int getItemViewType(int position) {
        //头布局存在且position==0
        if (headerView != null && position == 0) {
            return ITEM_TYPE_HEAD;
        } else if (position == getTitlePositionOfType(ITEM_TYPE_NORMAL_HOT)
                || position == getTitlePositionOfType(ITEM_TYPE_NORMAL_UPDATE)
                || position == getTitlePositionOfType(ITEM_TYPE_NORMAL_NEW)
                || position == getTitlePositionOfType(ITEM_TYPE_NORMAL_OLD)
                || position == getTitlePositionOfType(ITEM_TYPE_NORMAL_CHINA)
                || position == getTitlePositionOfType(ITEM_TYPE_NORMAL_REAL)) {
            return ITEM_TYPE_TITLE;
        }
        return ITEM_TYPE_NORMAL;
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(context, 6);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int spanSize = 0;
                switch (getItemViewType(position)) {
                    case ITEM_TYPE_HEAD:
                    case ITEM_TYPE_TITLE:
                        spanSize = 6;
                        break;
                    default:
                        spanSize = 3;
                        break;
                }
                return spanSize;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
    }

    //获取类别区间下总数
    int getCountInEachType(int type) {
        int count = 0;
        for (TuiJianData data : tuiJianDatas) {
            if (data.getContentType() == type)
                count += 1;
        }
        return count;
    }

    //确定各类别标题位置
    int getTitlePositionOfType(int type) {
        int titlePosition = 0;
        switch (type) {
            case ITEM_TYPE_NORMAL_HOT:
                titlePosition = headerView == null ? 0 : 1;
                break;
            case ITEM_TYPE_NORMAL_UPDATE:
                //标题位置 = 前一个标题位置 + 前一个类别总数 + 1
                titlePosition = getTitlePositionOfType(ITEM_TYPE_NORMAL_HOT) + getCountInEachType(ITEM_TYPE_NORMAL_HOT) + 1;
                break;
            case ITEM_TYPE_NORMAL_NEW:
                titlePosition = getTitlePositionOfType(ITEM_TYPE_NORMAL_UPDATE) + getCountInEachType(ITEM_TYPE_NORMAL_UPDATE) + 1;
                break;
            case ITEM_TYPE_NORMAL_OLD:
                titlePosition = getTitlePositionOfType(ITEM_TYPE_NORMAL_NEW) + getCountInEachType(ITEM_TYPE_NORMAL_NEW) + 1;
                break;
            case ITEM_TYPE_NORMAL_CHINA:
                titlePosition = getTitlePositionOfType(ITEM_TYPE_NORMAL_OLD) + getCountInEachType(ITEM_TYPE_NORMAL_OLD) + 1;
                break;
            case ITEM_TYPE_NORMAL_REAL:
                titlePosition = getTitlePositionOfType(ITEM_TYPE_NORMAL_CHINA) + getCountInEachType(ITEM_TYPE_NORMAL_CHINA) + 1;
                break;
        }

        //头布局存在则位置后移1
        return titlePosition;
    }

    /**
     * 根据position获取数据在list中真实序号
     * LayoutPosition____0  1  2  3  4  5  6  7  8  9  10 11  12  13 14 15 16 17
     * Layout____________H  A a1 a2 a3 a4  B  b1 b2 b3 b4 b5  b6  C  c1 c2 c3 c4
     * indexOfList_____________0  1  2  3     4  5  6  7  8   9      10 11 12 13
     *
     * @param position
     * @return
     */
    int getRealIndexInList(int position) {
        int realIndex = 0;
        if (position > getTitlePositionOfType(ITEM_TYPE_NORMAL_HOT) && position < getTitlePositionOfType(ITEM_TYPE_NORMAL_UPDATE)) {
            realIndex = headerView == null ? position - 1 : position - 2;
        }
        if (position > getTitlePositionOfType(ITEM_TYPE_NORMAL_UPDATE) && position < getTitlePositionOfType(ITEM_TYPE_NORMAL_NEW)) {
            realIndex = headerView == null ? position - 2 : position - 3;
        }
        if (position > getTitlePositionOfType(ITEM_TYPE_NORMAL_NEW) && position < getTitlePositionOfType(ITEM_TYPE_NORMAL_OLD)) {
            realIndex = headerView == null ? position - 3 : position - 4;
        }
        if (position > getTitlePositionOfType(ITEM_TYPE_NORMAL_OLD) && position < getTitlePositionOfType(ITEM_TYPE_NORMAL_CHINA)) {
            realIndex = headerView == null ? position - 4 : position - 5;
        }
        if (position > getTitlePositionOfType(ITEM_TYPE_NORMAL_CHINA) && position < getTitlePositionOfType(ITEM_TYPE_NORMAL_REAL)) {
            realIndex = headerView == null ? position - 5 : position - 6;
        }
        if (position > getTitlePositionOfType(ITEM_TYPE_NORMAL_REAL)) {
            realIndex = headerView == null ? position - 6 : position - 7;
        }

        return realIndex;
    }

    class TitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_title_left)
        ImageView ivTitleLeft;
        @BindView(R.id.tv_title_left)
        TextView tvTitleLeft;
        @BindView(R.id.tv_title_right)
        TextView tvTitleRight;
        @BindView(R.id.iv_title_right)
        ImageView ivTitleRight;
        @BindView(R.id.item_tuijian_title)
        LinearLayout itemTuijianTitle;

        public TitleHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public TitleHolder setIvTitleLeft(int resId) {
            ivTitleLeft.setImageResource(resId);
            return this;
        }

        public TitleHolder setTvTitleLeft(String text) {
            tvTitleLeft.setText(text);
            return this;
        }

        public TitleHolder setTvTitleRight(String text) {
            tvTitleRight.setText(text);
            return this;

        }

        public TitleHolder setIvTitleRight(int resId) {
            ivTitleRight.setImageResource(resId);
            return this;
        }
    }

    class NormalHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_tuijian_content)
        SimpleDraweeView ivTuijianContent;
        @BindView(R.id.tv_tuijian_title)
        TextView tvTuijianTitle;
        @BindView(R.id.tv_tuijian_desc)
        TextView tvTuijianDesc;
        @BindView(R.id.ll_item_tuijian)
        LinearLayout llItemTuijian;
        @BindView(R.id.card_item_tuijian)
        CardView cardItemTuijian;

        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public NormalHolder setIvTuijianContent(String imgUrl) {
            ivTuijianContent.setImageURI(imgUrl);
            return this;
        }

        public NormalHolder setTvTuijianTitle(String text) {
            tvTuijianTitle.setText(text);
            return this;
        }

        public NormalHolder setTvTuijianDesc(String text) {
            tvTuijianDesc.setText(text);
            return this;
        }
    }

    class HeaderHolder extends RecyclerView.ViewHolder {
        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }

}
