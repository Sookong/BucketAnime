package com.tanwuyu.ivrtym.bucketanime.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.adapter.VideoGridAdapter;
import com.tanwuyu.ivrtym.bucketanime.base.BaseMvpFragment;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Video;
import com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp.VideoGridPresenter;
import com.tanwuyu.ivrtym.bucketanime.view.IVideoGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ivrty on 2017/4/6.
 */

public class VideoGridFragment extends BaseMvpFragment<IVideoGridView, VideoGridPresenter> implements IVideoGridView {


    @BindView(R.id.rcv_video_grid)
    RecyclerView rcvVideoGrid;

    List<Video> videos;

    Unbinder unbinder;
    @BindView(R.id.tv_retry)
    TextView tvRetry;
    @BindView(R.id.error_view)
    LinearLayout errorView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_video_grid, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        initView();
        loadDatas();

        return rootView;
    }

    @Override
    public VideoGridPresenter createPresenter() {
        return new VideoGridPresenter();
    }

    @Override
    public void initView() {
        videos = new ArrayList<>();
        VideoGridAdapter videoGridAdapter = new VideoGridAdapter(getActivity(), videos);
        rcvVideoGrid.setAdapter(videoGridAdapter);

        tvRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatas();
            }
        });

        errorView.setVisibility(View.GONE);

    }

    @Override
    public void loadDatas() {
        Bundle bundle = getArguments();
        mPresenter.getVideos(bundle.getString("animeId"));
    }

    @Override
    public void initVars() {

    }

    @Override
    public void setDatasToRcv(List<Video> videos) {
        errorView.setVisibility(View.GONE);

        this.videos.clear();
        this.videos.addAll(videos);
        rcvVideoGrid.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showError() {
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
