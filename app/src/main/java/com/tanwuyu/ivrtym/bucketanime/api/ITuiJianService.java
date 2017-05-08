package com.tanwuyu.ivrtym.bucketanime.api;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.TuiJianData;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.TuiJianHeadData;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ivrty on 2017/4/1.
 */

public interface ITuiJianService {
    @GET("classes/TuiJianData")
    Flowable<BaseMultiDatasResponse<TuiJianData>> getTuiJianDatas(@Query("limit")int limit,@Query("skip")int skip,@Query("order")String order,@Query("include")String include);
    @GET("classes/TuiJianHeadData")
    Flowable<BaseMultiDatasResponse<TuiJianHeadData>> getTuiJianHeadDatas(@Query("limit")int limit,@Query("skip")int skip,@Query("order")String order,@Query("include")String include);
}
