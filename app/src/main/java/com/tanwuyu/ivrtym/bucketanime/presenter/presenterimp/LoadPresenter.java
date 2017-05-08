package com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp;

import com.tanwuyu.ivrtym.bucketanime.base.BaseMvpActivity;
import com.tanwuyu.ivrtym.bucketanime.base.BasePresenter;
import com.tanwuyu.ivrtym.bucketanime.presenter.ILoadPresenter;
import com.tanwuyu.ivrtym.bucketanime.utils.SharedPreferencesUtil;
import com.tanwuyu.ivrtym.bucketanime.view.ILoadView;
import com.tanwuyu.ivrtym.bucketanime.view.ILoginView;

/**
 * Created by ivrty on 2017/3/18.
 */

public class LoadPresenter extends BasePresenter<ILoadView> implements ILoadPresenter {
    @Override
    public void loadPrepare() {
        if (SharedPreferencesUtil.isFirstOpenApp()){
            mView.toLoginActivity();
        }else {
            mView.toHomeActivity();
        }

    }
}
