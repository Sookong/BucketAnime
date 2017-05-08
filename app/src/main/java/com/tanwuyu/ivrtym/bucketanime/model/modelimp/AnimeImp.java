package com.tanwuyu.ivrtym.bucketanime.model.modelimp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tanwuyu.ivrtym.bucketanime.api.BaseMultiDatasResponse;

import com.tanwuyu.ivrtym.bucketanime.api.IAnimeService;
import com.tanwuyu.ivrtym.bucketanime.api.RetrofitManager;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;

import io.reactivex.Flowable;

/**
 * Created by ivrty on 2017/3/27.
 */

public class AnimeImp {

    public Flowable<BaseMultiDatasResponse<Anime>> getAnimesByRequirement(int limit, int skip, String order, @Nullable String fenleiId, @Nullable String showTime, @Nullable int state, @Nullable String area) {
        //where={"$and":[{"createdAt":{"$gte":{"__type": "Date", "iso": "2014-07-15 00:00:00"}}},{"createdAt":{"$lte":{"__type": "Date", "iso": "2014-07-15 23:59:59"}}}]} 时间段内
        String showtimeRequirement = "";
        //where={"animeArea":"日本"};  地区
        String areaRequirement = "{\"animeArea\":\"" + area + "\"}";
        //where={"animenState":1}  完结状态
        String stateRequirement = "{\"animenState\":" + state + "}";
        //where={"$relatedTo":{"object":{"__type":"Pointer","className":"FenLei","objectId":"fenleiId"},"key":"childAnimes"}} 分类
        String fenleiRequirement = "{\"$relatedTo\":{\"object\":{\"__type\":\"Pointer\",\"className\":\"FenLei\",\"objectId\":\"" + fenleiId + "\"},\"key\":\"childAnimes\"}}";

        String requirementStart = "{\"$and\":[";
        String requirementEnd = "]}";
        String requirement = new String("");


        return RetrofitManager.getInstance()
                .crateApiService(IAnimeService.class)
                .getAnimesByRequirement(limit, skip, order, requirement);

    }

    /**
     * 根据动漫名获得动漫,模糊查询
     *
     * @param limit
     * @param skip
     * @param order
     * @param name
     * @return
     */
    public Flowable<BaseMultiDatasResponse<Anime>> getAnimesByName(int limit, int skip, String order, String name) {
        //where={"animeName":{"$regex":"name.*"}}
        String requirement = "{\"animeName\":{\"$regex\":\"" + name + ".*\"}}";
        return RetrofitManager.getInstance()
                .crateApiService(IAnimeService.class)
                .getAnimesByRequirement(limit, skip, order, requirement);
    }

    public Flowable<Anime> getAnimeById(String animeId) {
        return RetrofitManager.getInstance()
                .crateApiService(IAnimeService.class)
                .getAnimeById(animeId);
    }

    //获取用户收藏的所有番剧 (当animeId不为空且返回空时,说明用户没有追该番)
    public Flowable<BaseMultiDatasResponse<Anime>> getUserFollowAnime(@NonNull int limit, @NonNull int skip, @NonNull String userId, @Nullable String animeId) {
        String requirement;
        String requirementUserId = "{\"$relatedTo\":{\"object\":{\"__type\":\"Pointer\",\"className\":\"_User\",\"objectId\":\"" + userId + "\"},\"key\":\"followAnimes\"}}";
        String requirementAnimeId = "{\"objectId\":\"" + animeId + "\"}";

        if (animeId == null) {
            requirement = requirementUserId;
        } else {
            requirement = "{\"$and\":[" + requirementUserId + "," + requirementAnimeId + "]}";
        }

        return RetrofitManager.getInstance()
                .crateApiService(IAnimeService.class)
                .getAnimesByRequirement(limit, skip, "-updatedAt", requirement);
    }

}
