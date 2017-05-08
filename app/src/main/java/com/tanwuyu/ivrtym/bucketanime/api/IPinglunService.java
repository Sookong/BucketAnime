package com.tanwuyu.ivrtym.bucketanime.api;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.PingLun;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ivrtym on 2017/2/23.
 */

public interface IPinglunService {
    //条件查询多条
    @GET("classes/PingLun")
    Flowable<BaseMultiDatasResponse<PingLun>> getPinglunsByRequirement(@Query("limit")int limit, @Query("skip")int skip, @Query("order")String order, @Query("include")String include, @Query("where")String requirement);
    //直接查询单条
    @GET("classes/PingLun/{id}")
    Flowable<PingLun> getPinglunById(@Path("id")String id,@Query("include")String include);
    //条件查询单条
    @GET("classes/PingLun")
    Flowable<PingLun> getPinglunByRequirement(@Query("include")String include,@Query("where")String requirement);

}
