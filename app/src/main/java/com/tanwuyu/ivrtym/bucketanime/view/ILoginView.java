package com.tanwuyu.ivrtym.bucketanime.view;

import java.util.List;

/**
 * Created by ivrty on 2017/3/18.
 */

public interface ILoginView {
    String getAccountInput();

    void setAccountInput(String account);

    String getPassWordInput();

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    String getSmsCode();

    void setSmsCode(String smsCode);

    void showAccountInputClearButton();

    void hideAccountInputClearButton();

    void showPassWordVisiableButton();

    void hidePassWordVisiableButton();

    void showPhoneNumberInputClearButton();

    void hidePhoneNumberInputClearButton();

    void showSocialLoginPart();

    void hideSocialLoginPart();

    void showInputPopupWindow();

    void hideInputPopupWindow();

    void loadAccountRecordsToRcv(List<String> accountRecords);

    void enableLoginButton();

    void disableLoginButton();

    void enableGetSmsButton();

    void disableGetSmsButton();

    void setGetSmsButtonText(String msg);

    void showAccoutError(String errorMsg);

    void hideAccoutError();

    void showPassWordError(String errorMsg);

    void hidePassWordError();

    void showPhoneNumberError(String errorMsg);

    void hidePhoneNumberError();

    void startRequestSmsTimeDonw();

}
