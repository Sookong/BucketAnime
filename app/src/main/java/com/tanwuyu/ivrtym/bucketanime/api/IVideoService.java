package com.tanwuyu.ivrtym.bucketanime.api;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Video;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ivrty on 2017/3/27.
 */

public interface IVideoService {

    @GET("classes/Video")
    Flowable<BaseMultiDatasResponse<Video>> getVideosByRequirement(@Query("limit")int limit,@Query("skip")int skip,@Query("count")int count,@Query("order")String order,@Query("where")String requirement);



    @GET("classes/Video/{id}")
    Flowable<Video> getVideoById(@Path("id")String id,@Query("include")String include);

    @GET("classes/Video")
    Flowable<Video> getVideoByRequirement(@Query("include")String include,@Query("where")String requirement);


}
