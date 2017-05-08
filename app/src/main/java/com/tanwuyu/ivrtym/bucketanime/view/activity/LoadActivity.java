package com.tanwuyu.ivrtym.bucketanime.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.base.BaseMvpActivity;
import com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp.LoadPresenter;
import com.tanwuyu.ivrtym.bucketanime.view.ILoadView;


/**
 * Created by ivrty on 2017/3/18.
 */

public class LoadActivity extends BaseMvpActivity<ILoadView,LoadPresenter> implements ILoadView {
    @Override
    public LoadPresenter creatPresenter() {
        return new LoadPresenter();
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void initVars() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.loadPrepare();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
    }

    @Override
    public void toLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void toHomeActivity() {
        Intent intent = new Intent();
        intent.setClass(this,HomeActivity.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
