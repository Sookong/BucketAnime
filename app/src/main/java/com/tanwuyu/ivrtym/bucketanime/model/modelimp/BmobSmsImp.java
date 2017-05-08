package com.tanwuyu.ivrtym.bucketanime.model.modelimp;

import com.tanwuyu.ivrtym.bucketanime.api.ISmsService;
import com.tanwuyu.ivrtym.bucketanime.api.RetrofitManager;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.BmobSms;

import io.reactivex.Flowable;

/**
 * Created by ivrty on 2017/4/26.
 */

public class BmobSmsImp {
    public Flowable<BmobSms> requestSmsCode(String mobilePhoneNumber, String template) {
        BmobSms bmobSms = new BmobSms();
        bmobSms.setMobilePhoneNumber(mobilePhoneNumber);
        if (template != null && !template.isEmpty()) {
            bmobSms.setTemplate(template);
        }
        return RetrofitManager.getInstance()
                .crateApiService(ISmsService.class)
                .requestSmsCode(bmobSms);
    }
    public Flowable<BmobSms> checkSmsCode(String mobilePhoneNumber,String smsCode){
        BmobSms bmobSms = new BmobSms();
        bmobSms.setMobilePhoneNumber(mobilePhoneNumber);

        return RetrofitManager.getInstance()
                .crateApiService(ISmsService.class)
                .checkSmsCode(smsCode,bmobSms);
    }

    public Flowable<BmobSms> getSmsCodeState(String smsId){
        return RetrofitManager.getInstance()
                .crateApiService(ISmsService.class)
                .getSmsCodeState(smsId);
    }
}
