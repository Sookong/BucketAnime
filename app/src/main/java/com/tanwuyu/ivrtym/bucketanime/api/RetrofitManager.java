package com.tanwuyu.ivrtym.bucketanime.api;


import android.text.TextUtils;

import com.tanwuyu.ivrtym.bucketanime.base.MyApplication;
import com.tanwuyu.ivrtym.bucketanime.utils.NetWorkUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by ivrtym on 2017/2/23.
 */

public class RetrofitManager {
    private static RetrofitManager rtrofitManager;
    private Retrofit retrofit;

    private RetrofitManager() {
        retrofit = new Retrofit.Builder()
                .client(getOKHttpClient())
                .baseUrl("https://api.bmob.cn/1/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized RetrofitManager getInstance() {
        if (rtrofitManager == null) {
            rtrofitManager = new RetrofitManager();
        }
        return rtrofitManager;
    }
    //创建apiService
    public <T>T crateApiService(Class<T> apiService){
        return retrofit.create(apiService);
    }

    private OkHttpClient getOKHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new MyInterceptor())
                .addNetworkInterceptor(new NetWorkInterceptor())
                .connectTimeout(8, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .cache(getCache())
                .build();

        return client;

    }

    private Cache getCache() {
        File cacheDir = MyApplication.getInstance().getCacheDir();
        //获取缓存 大小 1MB * 20
        return new Cache(cacheDir, 1024 * 1024 * 20);
    }

    class MyInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            //统一添加Bmob_Header
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type","application/json")
                    .addHeader("X-Bmob-Application-Id","2860f9d9b02b08b5a6a502da8cf1e646")
                    .addHeader("X-Bmob-REST-API-Key","60053039186d27df8cbeffae4d4acd04")
                    .build();

            //没网走缓存
            if (!NetWorkUtil.isNetWorkAvailable(MyApplication.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            return chain.proceed(request);
        }
    }

    class NetWorkInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response preResponse = chain.proceed(request);
            String serverCacheControl = preResponse.header("Cache-Control");
            //若服务器未设置cacheControl,手动为Response添加cacheControl
            if (TextUtils.isEmpty(serverCacheControl) /*serverCacheControl == null && serverCacheControl.isEmpty()*/) {

                //缓存最大保存时间 1周
                int maxAge = 60 * 60 * 24 * 7;
                String cusCacheControl = "public,only-if-cached,max-stale=" + maxAge;
                Response response = preResponse.newBuilder()
                        .removeHeader("Pragma")
                        .addHeader("Cache-Control", cusCacheControl)
                        .build();
                return response;
            } else {
                return preResponse;
            }

        }
    }
}
