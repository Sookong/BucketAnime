package com.tanwuyu.ivrtym.bucketanime.model.modelimp;

import com.tanwuyu.ivrtym.bucketanime.api.IUserService;
import com.tanwuyu.ivrtym.bucketanime.api.RetrofitManager;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.User;
import com.tanwuyu.ivrtym.bucketanime.utils.StringPatternUtil;

import io.reactivex.Flowable;

/**
 * Created by ivrty on 2017/3/27.
 */

public class UserImp {
    public Flowable<User> signInByAccount(String account,String passwrod){
        return RetrofitManager.getInstance()
                .crateApiService(IUserService.class)
                .signInByAccount(account,passwrod);
    }

    public Flowable<User> signInOrUpBySmsCode(String phoneNumber,String smsCode){
        User user = new User();
        user.setMobilePhoneNumber(phoneNumber);
        user.setSmsCode(smsCode);
        return RetrofitManager.getInstance()
                .crateApiService(IUserService.class)
                .signInOrUpBySmsCode(user);
    }


}
