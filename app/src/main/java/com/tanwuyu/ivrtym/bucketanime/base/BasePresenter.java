package com.tanwuyu.ivrtym.bucketanime.base;


import org.reactivestreams.Subscription;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.internal.subscriptions.ArrayCompositeSubscription;

/**
 * Created by ivrtym on 2017/2/22.
 */

public class BasePresenter<T> {
    protected T mView;
    protected CompositeDisposable compositeDisposable;   //保存订阅关系

    public BasePresenter() {
        compositeDisposable = new CompositeDisposable();
    }

    public void attachView(T mView) {
        this.mView = mView;
    }

    public void detachView() {
        if (this.mView != null){
            this.mView = null;
        }
        if (compositeDisposable != null){
            compositeDisposable.clear();
        }
    }
}
