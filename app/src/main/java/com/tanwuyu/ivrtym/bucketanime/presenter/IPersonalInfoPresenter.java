package com.tanwuyu.ivrtym.bucketanime.presenter;

/**
 * Created by ivrty on 2017/4/28.
 */

public interface IPersonalInfoPresenter {

    void changeUserImgUrl(String userImgUrl);
    void changeUserNickName(String userNickName);
    void chageUserSex(String userSex);
    void changeUserBirthDay(String userBirthDay);
    void changeUserSign(String userSign);

    void exitCurrentCount();
}
