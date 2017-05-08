package com.tanwuyu.ivrtym.bucketanime.api;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ivrtym on 2017/2/23.
 */

public interface IAnimeService {
    /**
     * 条件查询多条
     * @param limit
     * @param skip
     * @param order
     * @param requirement
     * @return
     */
    @GET("classes/Anime")
    Flowable<BaseMultiDatasResponse<Anime>> getAnimesByRequirement(@Query("limit")int limit,@Query("skip")int skip,@Query("order")String order,@Query("where")String requirement);

    /**
     * 直接查询单条
     * @param id
     * @return
     */
    @GET("classes/Anime/{id}")
    Flowable<Anime> getAnimeById(@Path("id")String id);

    /**
     * 条件查询单条
     * @param requirement
     * @return
     */
    @GET("classes/Anime")
    Flowable<Anime> getAnimeByRequirement(@Query("where")String requirement);

    @PUT("classes/Anime")
    Flowable<Anime> checkAnimeIsFollowedByUser(@Body RequestBody requestBody);

}
