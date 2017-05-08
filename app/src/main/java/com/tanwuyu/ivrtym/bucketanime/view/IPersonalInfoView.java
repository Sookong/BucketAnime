package com.tanwuyu.ivrtym.bucketanime.view;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.User;

/**
 * Created by ivrty on 2017/4/28.
 */

public interface IPersonalInfoView {
    void setBaseUerInfo(User uer);

    void setUserImgUrl(String userImgUrl);
    void setUserNickName(String userNickName);
    void setUserSex(String userSex);
    void setUserBirthDay(String userBirthDay);
    void setUserSign(String userSign);

    void setQqBindState(String qqBindState);
    void setWeiboBindState(String weiboBindState);
    void setWechatBindState(String wechatBindState);
    void setEmailBindState(String emailBindState);
}
