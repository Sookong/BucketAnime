package com.tanwuyu.ivrtym.bucketanime.model.modelimp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tanwuyu.ivrtym.bucketanime.api.BaseMultiDatasResponse;
import com.tanwuyu.ivrtym.bucketanime.api.IVideoService;
import com.tanwuyu.ivrtym.bucketanime.api.RetrofitManager;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Video;

import io.reactivex.Flowable;

/**
 * Created by ivrty on 2017/3/27.
 */

public class VideoImp {
    /**
     * 获得指定id动漫下的所有剧集 pointer查询
     * where={"post":{"__type":"Pointer","className":"Post","objectId":"1dafb9ed9b"}}
     *
     * @param superAnimeId
     * @return
     */
    public Flowable<BaseMultiDatasResponse<Video>> getVideosBySuperAnimeId(String superAnimeId) {
        String requirement = "{\"superAnime\":{\"__type\":\"Pointer\",\"className\":\"Anime\",\"objectId\":\"" + superAnimeId + "\"}}";
        return RetrofitManager.getInstance()
                .crateApiService(IVideoService.class)
                .getVideosByRequirement(Integer.MAX_VALUE, 0, 1, "-number", requirement);
    }

    public Flowable<BaseMultiDatasResponse<Video>> getVideosCountBySuperAnimeId(String superAnimeId) {
        String requirement = "{\"superAnime\":{\"__type\":\"Pointer\",\"className\":\"Anime\",\"objectId\":\"" + superAnimeId + "\"}}";
        return RetrofitManager.getInstance()
                .crateApiService(IVideoService.class)
                .getVideosByRequirement(0, 0, 1, "-number", requirement);
    }


    /**
     * 获得指定id用户收藏的剧集
     *
     * @param userId
     * @return
     */
    public Flowable<BaseMultiDatasResponse<Video>> getVideosByUerId(@NonNull int limit, @NonNull int skip, @NonNull String userId, @Nullable String videoId) {
        String requirement;
        String requirement1 = "{\"$relatedTo\":{\"object\":{\"__type\":\"Pointer\",\"className\":\"_Uer\",\"objectId\":\"" + userId + "\"},\"key\":\"collectUsers\"}}";
        String requirement2 = "{\"objectId\":\"" + videoId + "\"}";
        if (videoId == null) {
            requirement = requirement1;
        } else {
            requirement = "{\"$and\":[" + requirement1 + "," + requirement2 + "]}";
        }
        return RetrofitManager.getInstance()
                .crateApiService(IVideoService.class)
                .getVideosByRequirement(limit, skip, 1, "-updatedAt", requirement);
    }
}
