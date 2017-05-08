package com.tanwuyu.ivrtym.bucketanime.presenter;

/**
 * Created by ivrty on 2017/3/18.
 */

public interface ILoginPresenter {
    void doLogin();
    void doOneKeyLogin();
    void doSocialLogin();

    void requestSmsCode();
}
