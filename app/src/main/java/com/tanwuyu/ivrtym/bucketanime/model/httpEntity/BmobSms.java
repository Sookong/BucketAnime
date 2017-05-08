package com.tanwuyu.ivrtym.bucketanime.model.httpEntity;

/**
 * Created by ivrty on 2017/4/26.
 */

public class BmobSms {
    private String mobilePhoneNumber;
    private String template;
    private String smsId;
    private String msg;
    private String sms_state;
    private Boolean verify_state;

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSms_state() {
        return sms_state;
    }

    public void setSms_state(String sms_state) {
        this.sms_state = sms_state;
    }

    public Boolean getVerify_state() {
        return verify_state;
    }

    public void setVerify_state(Boolean verify_state) {
        this.verify_state = verify_state;
    }
}
