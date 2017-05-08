package com.tanwuyu.ivrtym.bucketanime.model.modelimp;


import com.tanwuyu.ivrtym.bucketanime.api.BaseMultiDatasResponse;
import com.tanwuyu.ivrtym.bucketanime.api.IHotSearchService;
import com.tanwuyu.ivrtym.bucketanime.api.RetrofitManager;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.HotSearch;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import io.reactivex.Flowable;
import okhttp3.RequestBody;


/**
 * Created by ivrty on 2017/3/27.
 */

public class HotSearchImp {
    /**
     * {"searchCount":{"__op":"Increment","amount":1}}
     * 搜索数原子增加1
     *
     * @param id
     * @return
     */
    public Flowable<HotSearch> countAutoIncreaseOne(String id) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONObject targetJson = new JSONObject();
        String bodyString;
        RequestBody requestBody;

        jsonObject.put("__op", "Increment");
        jsonObject.put("amount", 1);
        targetJson.put("searchCount", jsonObject);
        bodyString = targetJson.toString();
        requestBody = RequestBody.create(null, bodyString);

        return RetrofitManager.getInstance()
                .crateApiService(IHotSearchService.class)
                .updateHotSearch(id, requestBody);
    }

    /**
     * get热搜关键词
     *{"createdAt":{"$lte":{"__type": "Date", "iso": "2014-07-15 23:59:59"}}}]}
     * @param limit
     * @param skip
     * @return
     */
    public Flowable<BaseMultiDatasResponse<HotSearch>> getHotSearchs(int limit, int skip) {

        return RetrofitManager.getInstance()
                .crateApiService(IHotSearchService.class)
                .getHotSearchs(limit,skip,"-searchCount");

    }

    /**
     * 添加一个热搜关键词
     *
     * @param hotSearchText
     * @return
     * @throws JSONException
     */
    public Flowable<HotSearch> addOneHotSearch(String hotSearchText) throws JSONException {
        HotSearch hotSearch = new HotSearch();
        hotSearch.setSearchText(hotSearchText);
        return RetrofitManager.getInstance()
                .crateApiService(IHotSearchService.class)
                .addHotSearch(hotSearch);
    }

    /**
     * 查找 关键词
     *
     * @param searchText
     * @return
     * @throws JSONException
     */
    public Flowable<HotSearch> getOneHotSearchBySearchText(String searchText) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("searchText", searchText);
        return RetrofitManager.getInstance()
                .crateApiService(IHotSearchService.class)
                .getHotSearchByRequirement(jsonObject.toString());
    }

}
