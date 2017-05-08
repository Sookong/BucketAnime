package com.tanwuyu.ivrtym.bucketanime.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanwuyu.ivrtym.bucketanime.base.BaseMvpFragment;
import com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp.XinFanPresenter;
import com.tanwuyu.ivrtym.bucketanime.view.IXinFanView;

/**
 * Created by ivrtym on 2017/3/8.
 */

public class XinFanFragment extends BaseMvpFragment<IXinFanView,XinFanPresenter> implements IXinFanView {




    @Override
    public XinFanPresenter createPresenter() {
        return new XinFanPresenter();
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

    // @Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }


}
