package com.tanwuyu.ivrtym.bucketanime.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.adapter.TuiJianRcvAdapter;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.TuiJianData;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.TuiJianHeadData;
import com.tanwuyu.ivrtym.bucketanime.view.activity.AnimeActivity;
import com.tanwuyu.ivrtym.bucketanime.view.activity.VideoActivity;
import com.tanwuyu.ivrtym.bucketanime.view.activity.WebViewActivity;
import com.tanwuyu.ivrtym.rollpageview.RollPageView;
import com.tanwuyu.ivrtym.bucketanime.base.BaseMvpFragment;

import com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp.TuiJianPresenter;
import com.tanwuyu.ivrtym.bucketanime.view.ITuijianView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ivrtym on 2017/3/16.
 */

public class TuiJianFragment extends BaseMvpFragment<ITuijianView, TuiJianPresenter> implements ITuijianView {
    @BindView(R.id.rcv_tuijian)
    RecyclerView rcvTuijian;
    @BindView(R.id.swip_refresh_layout_tuijian)
    SwipeRefreshLayout swipRefreshLayoutTuijian;


    List<TuiJianData> mDatas;
    List<RollPageView.Page> rollPageDatas;
    TuiJianRcvAdapter mAdapter;

    View headerView;
    RollPageView rollPageView;

    boolean isFragmentHasShown;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_tuijian, container, false);
        ButterKnife.bind(this, rootView);

        initView();
        loadDatas();

        return rootView;
    }

    @Override
    public void initView() {
        isFragmentHasShown = false;

        //初始化header
        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.item_tuijian_header, null);
        //初始化轮播图
        rollPageDatas = new ArrayList<>();
        rollPageView = (RollPageView) headerView.findViewById(R.id.rollpageview_item_tuijian_header);
        //rollPageView.initRollPageView(rollPageDatas);
        //高占屏幕1/3
        int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, (int) (height / 3 + 0.5));
        rollPageView.setLayoutParams(layoutParams);

        //初始化recyclerview
        mDatas = new ArrayList<>();
        mAdapter = new TuiJianRcvAdapter(mDatas, mContext);
        rcvTuijian.setAdapter(mAdapter);


        //初始化下拉刷新控件
        swipRefreshLayoutTuijian.setProgressBackgroundColorSchemeResource(R.color.white);
        swipRefreshLayoutTuijian.setColorSchemeResources(R.color.lightblue, R.color.lightpink, R.color.lightyellow, R.color.lightgreen);
        swipRefreshLayoutTuijian.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadTuiJianDatas();
            }
        });


    }

    @Override
    public void loadDatas() {
        mPresenter.loadHeaderDatas();
        mPresenter.loadTuiJianDatas();
    }

    @Override
    public void initVars() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        rollPageView.stopAtuoRound();
    }

    @Override
    public TuiJianPresenter createPresenter() {
        return new TuiJianPresenter();
    }

    @Override
    public void upDateTuiJianDatas(List<TuiJianData> datas) {
        if (mDatas == null) mDatas = new ArrayList<>();
        mDatas.clear();
        mDatas.addAll(0,datas);
        mAdapter.notifyDataSetChanged();
        swipRefreshLayoutTuijian.setRefreshing(false);
    }

    @Override
    public void upDateHeaderDatas(List<TuiJianHeadData> headDatas) {

        if (rollPageDatas == null) rollPageDatas = new ArrayList<>();
        rollPageDatas.clear();

        if (headDatas != null) {
            RollPageView.Page page;

            for (final TuiJianHeadData headData : headDatas) {
                page = new RollPageView.Page();
                SimpleDraweeView draweeView = new SimpleDraweeView(getActivity());
                int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
                draweeView.setAspectRatio(1.5f);
                draweeView.setMaxHeight((int) (height / 3 + 0.5));
                draweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP);

                switch (headData.getContentType()) {
                    case 0:
                        draweeView.setImageURI(headData.getContentImgUrl());
                        draweeView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                toAdActivity(headData.getContentUrl());
                            }
                        });
                        page.setPage(draweeView);
                        page.setPageDesc(headData.getTitle());
                        break;
                    case 1:
                        draweeView.setImageURI(headData.getContentAnime().getImgUrl());
                        draweeView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                toAnimeActivity(headData.getContentAnime().getObjectId());
                            }
                        });
                        page.setPage(draweeView);
                        page.setPageDesc(headData.getContentAnime().getAnimeName());
                        break;
                    case 2:
                        draweeView.setImageURI(headData.getContentVideo().getImgUrl());
                        draweeView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                toVideoActivity(headData.getContentVideo().getObjectId());
                            }
                        });
                        page.setPage(draweeView);
                        page.setPageDesc(headData.getContentAnime().getAnimeName() + " : " + "第" + headData.getContentVideo().getNumber() + "话" + " " + headData.getContentVideo().getVideoName());
                        break;
                }

                rollPageDatas.add(page);
            }
            rollPageView.initRollPageView(rollPageDatas);
        }
       mAdapter.setHeaderView(headerView);
        //mAdapter.notifyDataSetChanged();
    }


    @Override
    public void toAnimeActivity(String animeId) {
        Intent intent = new Intent(getActivity(), AnimeActivity.class);
        intent.putExtra("animeId", animeId);
        startActivity(intent);
    }

    @Override
    public void toVideoActivity(String videoId) {
        Intent intent = new Intent(getActivity(), VideoActivity.class);
        intent.putExtra("videoId", videoId);
        startActivity(intent);
    }

    @Override
    public void toAdActivity(String url) {
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }


}
