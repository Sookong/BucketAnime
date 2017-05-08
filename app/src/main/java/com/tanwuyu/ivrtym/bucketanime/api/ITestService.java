package com.tanwuyu.ivrtym.bucketanime.api;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by ivrty on 2017/3/20.
 */

public interface ITestService {
    @GET("classes/HotSearch")
    Observable<ResponseBody> get();
}
