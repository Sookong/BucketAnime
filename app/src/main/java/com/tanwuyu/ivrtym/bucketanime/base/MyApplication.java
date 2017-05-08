package com.tanwuyu.ivrtym.bucketanime.base;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.mob.commons.SHARESDK;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.User;

import java.util.HashMap;

import cn.bmob.push.BmobPush;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by ivrtym on 2017/2/23.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化fresco
        Fresco.initialize(this);
        //初始化ShareSdk
        ShareSDK.initSDK(this,"1d16c26130480");
        // 初始化BmobSDK
        //Bmob.initialize(this, "你的AppKey");
        // 使用推送服务时的初始化操作
        //BmobInstallation.getCurrentInstallation().save();
        // 启动推送服务
        BmobPush.startWork(this);
    }
    public static MyApplication getInstance(){
        return instance;
    }
}
