package com.tanwuyu.ivrtym.bucketanime.model.modelimp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tanwuyu.ivrtym.bucketanime.api.BaseMultiDatasResponse;
import com.tanwuyu.ivrtym.bucketanime.api.IReplyService;
import com.tanwuyu.ivrtym.bucketanime.api.RetrofitManager;
import com.tanwuyu.ivrtym.bucketanime.model.AnimeReply;
import com.tanwuyu.ivrtym.bucketanime.model.VideoReply;

import io.reactivex.Flowable;

/**
 * Created by ivrty on 2017/5/4.
 */

public class AnimeReplyImp {
    /**
     * 获取指定Anime 下 所有评论
     * where={"post":{"__type":"Pointer","className":"Post","objectId":"1dafb9ed9b"}}
     * @param limit
     * @param skip
     * @param order
     * @param animeId
     * @return
     */
    public Flowable<BaseMultiDatasResponse<AnimeReply>> getRepliesOfAnime(int limit, int skip,@NonNull String order,@NonNull String animeId){
        String requirement = "{\"superAnime\":{\"__type\":\"Pointer\",\"className\":\"Anime\",\"objectId\":\""+animeId+"\"}}";
        return RetrofitManager.getInstance()
                .crateApiService(IReplyService.class)
                .getAnimeRepliesByRequirement(limit,skip,order,requirement);
    }

    /**
     * 获取指定Anime评论下的所有子回复
     * @return
     */
    public Flowable<BaseMultiDatasResponse<AnimeReply>> getRepliesOfAnimeReply(int limit,int skip, @NonNull String order, @NonNull String replyId){
        String requirement="{\"targetReply\":{\"__type\":\"Pointer\",\"className\":\"AnimeReply\",\"objectId\":\"" + replyId + "\"}}";
        return RetrofitManager.getInstance()
                .crateApiService(IReplyService.class)
                .getAnimeRepliesByRequirement(limit,skip,"-createdAt",requirement);
    }

    /**
     * 发送一条动漫评论
     */
    public Flowable<AnimeReply> sendSingleReply(@NonNull String uerId,@NonNull String animeId,@Nullable String targetReplyId){
        AnimeReply animeReply = new AnimeReply();
        AnimeReply.ReplyAuthorBean replyAuthorBean = new AnimeReply.ReplyAuthorBean();
        AnimeReply.SuperAnimeBean superAnimeBean = new AnimeReply.SuperAnimeBean();
        AnimeReply.TargetReplyBean targetReplyBean = new AnimeReply.TargetReplyBean();

        replyAuthorBean.set__type("Pointer");
        replyAuthorBean.setClassName("_User");
        replyAuthorBean.setObjectId(uerId);

        superAnimeBean.set__type("Pointer");
        superAnimeBean.setClassName("Anime");
        superAnimeBean.setObjectId(animeId);

        targetReplyBean.set__type("Pointer");
        targetReplyBean.setClassName("AnimeReply");
        targetReplyBean.setObjectId(targetReplyId);

        animeReply.setReplyAuthor(replyAuthorBean);
        animeReply.setSuperAnime(superAnimeBean);
        if (targetReplyId!=null){
            animeReply.setTargetReply(targetReplyBean);
        }

        return RetrofitManager.getInstance()
                .crateApiService(IReplyService.class)
                .addSingleAnimeReply(animeReply);
    }
    /**
     *
     */
}
