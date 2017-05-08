package com.tanwuyu.ivrtym.bucketanime.model.httpEntity;

/**
 * Created by ivrty on 2017/3/28.
 */

public class User {
    /**
     * createdAt : 2017-03-28 15:54:36
     * email : ivrtym@hotmail.com
     * emailVerified : false
     * imgUrl : http://www.youk.com/asdfasdf
     * mobilePhoneNumber : 18602756106
     * mobilePhoneNumberVerified : false
     * objectId : cIEdDDDH
     * updatedAt : 2017-03-28 16:28:23
     * username : ivrtym
     */

    private String createdAt;
    private String email;
    private boolean emailVerified;
    private String imgUrl;
    private String mobilePhoneNumber;
    private boolean mobilePhoneNumberVerified;
    private String objectId;
    private String updatedAt;
    private String username;
    private String smsCode;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public boolean isMobilePhoneNumberVerified() {
        return mobilePhoneNumberVerified;
    }

    public void setMobilePhoneNumberVerified(boolean mobilePhoneNumberVerified) {
        this.mobilePhoneNumberVerified = mobilePhoneNumberVerified;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
