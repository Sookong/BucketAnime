package com.tanwuyu.ivrtym.bucketanime.api;

import com.tanwuyu.ivrtym.bucketanime.model.AnimeReply;
import com.tanwuyu.ivrtym.bucketanime.model.VideoReply;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ivrty on 2017/5/4.
 */

public interface IReplyService {
    //根据约束条件查询动漫回复
    @GET("classes/AnimeReply")
    Flowable<BaseMultiDatasResponse<AnimeReply>> getAnimeRepliesByRequirement(@Query("limit")int limit,@Query("skip")int skip,@Query("order")String order,@Query("where")String requirement);
    //添加一条动漫回复
    @POST("classes/AnimeReply")
    Flowable<AnimeReply> addSingleAnimeReply(@Body AnimeReply animeReply);


    //根据约束条件查询视频回复
    @GET("classes/VideoReply")
    Flowable<BaseMultiDatasResponse<VideoReply>> getVideoRepliesByRequirement(@Query("limit")int limit, @Query("skip")int skip, @Query("order")String order, @Query("where")String requirement);
    //添加一条视频回复
    @POST("classes/VideoReply")
    Flowable<VideoReply> addSingleVideoReply(@Body VideoReply videoReply);
}
