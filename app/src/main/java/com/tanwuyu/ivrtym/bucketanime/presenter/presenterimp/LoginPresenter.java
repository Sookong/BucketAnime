package com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.tanwuyu.ivrtym.bucketanime.api.BmobHttpError;
import com.tanwuyu.ivrtym.bucketanime.base.BasePresenter;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.BmobSms;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.User;
import com.tanwuyu.ivrtym.bucketanime.model.modelimp.BmobSmsImp;
import com.tanwuyu.ivrtym.bucketanime.model.modelimp.UserImp;
import com.tanwuyu.ivrtym.bucketanime.presenter.ILoginPresenter;
import com.tanwuyu.ivrtym.bucketanime.utils.StringPatternUtil;
import com.tanwuyu.ivrtym.bucketanime.view.ILoginView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by ivrty on 2017/3/18.
 */

public class LoginPresenter extends BasePresenter<ILoginView> implements ILoginPresenter{
    UserImp userImp;
    BmobSmsImp bmobSmsImp;

    public LoginPresenter() {
        userImp = new UserImp();
        bmobSmsImp = new BmobSmsImp();
    }

    @Override
    public void doLogin() {
        String account = mView.getAccountInput();
        String password = mView.getPassWordInput();

        userImp.signInByAccount(account,password)
                .map(new Function<User, User>() {
                    @Override
                    public User apply(@NonNull User user) throws Exception {
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(User user) {
                        Log.e("LOGIN_SUCCESS","------------");
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (t instanceof HttpException){
                            String errorResponseBody = null;
                            try {
                                errorResponseBody = ((HttpException) t).response().errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //BmobHttpError httpError = new GsonBuilder().create().fromJson(errorResponseBody,BmobHttpError.class);
                            Log.e("LOGIN_ERROR",errorResponseBody);
                            mView.showPassWordError(errorResponseBody);
                            //mView.showPassWordError(httpError.getError());
                        }
                        Log.e("LOGIN_ERROR","---------");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("LOGIN_Complete","------------");
                    }
                });

    }

    @Override
    public void doOneKeyLogin() {
        String phoneNumber = mView.getPhoneNumber();
        String smsCode = mView.getSmsCode();
        userImp.signInOrUpBySmsCode(phoneNumber,smsCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(User user) {
                        Log.e("LOGIN_SUCCESS","------------");
                    }

                    @Override
                    public void onError(Throwable t) {
                        if (t instanceof HttpException){
                            String errorResponseBody = null;
                            try {
                                errorResponseBody = ((HttpException) t).response().errorBody().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //BmobHttpError httpError = new GsonBuilder().create().fromJson(errorResponseBody,BmobHttpError.class);
                            Log.e("LOGIN_ERROR",errorResponseBody);
                            mView.showPassWordError(errorResponseBody);
                            //mView.showPassWordError(httpError.getError());
                        }
                        Log.e("LOGIN_ERROR","---------");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("LOGIN_Complete","------------");
                    }
                });
    }

    @Override
    public void doSocialLogin() {

    }

    @Override
    public void requestSmsCode() {
        String phoneNumber = mView.getPhoneNumber();
        bmobSmsImp.requestSmsCode(phoneNumber,"signUpSms")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BmobSms>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(BmobSms bmobSms) {
                        mView.startRequestSmsTimeDonw();
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
