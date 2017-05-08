package com.tanwuyu.ivrtym.bucketanime.api;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.BmobSms;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ivrty on 2017/4/26.
 */

public interface ISmsService {
    //请求验证码 {"mobilePhoneNumber": phoneNum,"template": templateName(选填，需先在管理后台创建)}
    @POST("requestSmsCode")
    Flowable<BmobSms> requestSmsCode(@Body BmobSms bmobSms);

    //校验 验证码   {"mobilePhoneNumber": "18602756106"} 校验即失效
    @POST("verifySmsCode/{smsCode}")
    Flowable<BmobSms> checkSmsCode(@Path("smsCode")String smsCode,@Body BmobSms bmobSms);

    //检查 短信发送状态
    @GET("querySms/:{smsId}")
    Flowable<BmobSms> getSmsCodeState(@Path("smsId")String smsId);
}
