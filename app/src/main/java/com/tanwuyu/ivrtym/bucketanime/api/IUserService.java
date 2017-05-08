package com.tanwuyu.ivrtym.bucketanime.api;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.User;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by ivrtym on 2017/2/23.
 */

public interface IUserService {
    @POST("users")
    Flowable<User> signUp(@Body User user);

    @GET("login")
    Flowable<User> signInByAccount(@Query("username")String userName,@Query("password")String password);

    @POST("users")
    Flowable<User> signInOrUpBySmsCode(@Body User user);

    @GET("users")
    Flowable<User> getUser(@Query("objectId")String objectId);

    @PUT("users")
    Flowable<User> followAnime(@Body RequestBody requestBody);

}
