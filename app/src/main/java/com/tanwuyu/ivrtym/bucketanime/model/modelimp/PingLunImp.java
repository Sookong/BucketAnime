package com.tanwuyu.ivrtym.bucketanime.model.modelimp;

import com.tanwuyu.ivrtym.bucketanime.api.BaseMultiDatasResponse;
import com.tanwuyu.ivrtym.bucketanime.api.IPinglunService;
import com.tanwuyu.ivrtym.bucketanime.api.RetrofitManager;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.PingLun;

import io.reactivex.Flowable;

/**
 * Created by ivrty on 2017/3/28.
 */

public class PingLunImp {
    /**
     * 获取指定id video下的所有评论
     * include:superVideo,pinglunUser,toUser,toPingLun  where:{"toPinglun":{"__type":"Pointer","className":"PingLun","objectId":"rENa555p"}}
     * @param limit
     * @param skip
     * @param order
     * @param id
     * @return
     */
    public Flowable<BaseMultiDatasResponse<PingLun>> getPingLunByVideoId(int limit, int skip, String order, String videoId){
        String requirement = "{\"superVideo\":{\"__type\":\"Pointer\",\"className\":\"Video\",\""+videoId+"\"}}";
        return RetrofitManager.getInstance()
                .crateApiService(IPinglunService.class)
                .getPinglunsByRequirement(limit,skip,order,"superVideo,pinglunUser,toUser,toPinglun",requirement);
    }
}
