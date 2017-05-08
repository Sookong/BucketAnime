package com.tanwuyu.ivrtym.bucketanime.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tanwuyu.ivrtym.bucketanime.base.BaseMvpActivity;
import com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp.DownLoadPresenter;
import com.tanwuyu.ivrtym.bucketanime.view.IDownLoadView;

/**
 * Created by ivrtym on 2017/3/15.
 */

public class DownLoadActivity extends BaseMvpActivity<IDownLoadView,DownLoadPresenter> implements IDownLoadView {
    @Override
    public DownLoadPresenter creatPresenter() {
        return new DownLoadPresenter();
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadDownLoadList() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView();
        initView();
    }


    @Override
    public void loadData() {

    }

    @Override
    public void initVars() {

    }
}
