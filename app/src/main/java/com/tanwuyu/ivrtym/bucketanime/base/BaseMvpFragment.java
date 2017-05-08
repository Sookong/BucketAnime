package com.tanwuyu.ivrtym.bucketanime.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ivrtym on 2017/2/22.
 */

public abstract class BaseMvpFragment<V, T extends BasePresenter<V>> extends Fragment {
    protected T mPresenter;
    protected Context mContext;
    protected CompositeDisposable mCompositeDisposable;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = MyApplication.getInstance();
        if (mPresenter == null)
            mPresenter = createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        mPresenter.attachView((V) this);
        mCompositeDisposable = new CompositeDisposable();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null)
            mPresenter.detachView();
        mCompositeDisposable.clear();
        super.onDestroyView();
    }

    public abstract T createPresenter();

    public abstract void initView();

    public abstract void loadDatas();

    public abstract void initVars();


}
