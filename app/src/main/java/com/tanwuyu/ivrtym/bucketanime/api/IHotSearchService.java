package com.tanwuyu.ivrtym.bucketanime.api;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.HotSearch;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ivrty on 2017/3/27.
 */

public interface IHotSearchService {
    /**
     * limit:5
     skip:5
     order:searchCount
     where:{"createdAt":{"$gte":{"__type": "Date", "iso": "2014-07-15 00:00:00"}}}
     * @param limit             单页数
     * @param skip              起跳页
     * @param order             排序方式
     * @param requirement       查询条件
     * @return
     */
    @GET("classes/HotSearch")
    Flowable<BaseMultiDatasResponse<HotSearch>> getHotSearchsByRequirement(@Query("limit") int limit,@Query("skip") int skip,@Query("order") String order,@Query("where") String requirement);

    /**
     * 查询单条
     * @param requirement
     * @return
     */
    @GET("classes/HotSearch")
    Flowable<HotSearch> getHotSearchByRequirement(@Query("where")String requirement);
    @GET("classes/HotSearch")
    Flowable<BaseMultiDatasResponse<HotSearch>> getHotSearchs(@Query("limit") int limit,@Query("skip") int skip,@Query("order") String order);

    /**
     * 添加数据
     * @param hotSearch
     * @return
     */
    @POST("classes/HotSearch")
    Flowable<HotSearch> addHotSearch(@Body HotSearch hotSearch);

    /**
     * 修改数据
     * @param id
     * @return
     */
    @PUT("classes/HotSearch/{id}")
    Flowable<HotSearch> updateHotSearch(@Path("id")String id, @Body RequestBody requestBody);
}
