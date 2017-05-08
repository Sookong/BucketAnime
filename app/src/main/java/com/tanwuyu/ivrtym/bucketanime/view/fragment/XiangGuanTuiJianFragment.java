package com.tanwuyu.ivrtym.bucketanime.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.base.BaseMvpFragment;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;
import com.tanwuyu.ivrtym.bucketanime.presenter.IXiangGuanTuiJianPresenter;
import com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp.VideoGridPresenter;
import com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp.XiangGuanTuiJianPresenter;
import com.tanwuyu.ivrtym.bucketanime.view.IVideoGridView;
import com.tanwuyu.ivrtym.bucketanime.view.IXiangGuanTuiJianView;

import java.util.List;

/**
 * Created by ivrty on 2017/4/6.
 */

public class XiangGuanTuiJianFragment extends BaseMvpFragment<IXiangGuanTuiJianView,XiangGuanTuiJianPresenter> implements IXiangGuanTuiJianView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_xiang_guan_tui_jian,container,false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public XiangGuanTuiJianPresenter createPresenter() {
        return new XiangGuanTuiJianPresenter();
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadDatas() {

    }

    @Override
    public void initVars() {

    }

    @Override
    public void loadDatasToRcv(List<Anime> animes) {

    }
}
