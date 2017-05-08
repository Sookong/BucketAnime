package com.tanwuyu.ivrtym.bucketanime.model.modelimp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.tanwuyu.ivrtym.bucketanime.api.BaseMultiDatasResponse;
import com.tanwuyu.ivrtym.bucketanime.api.IReplyService;
import com.tanwuyu.ivrtym.bucketanime.api.RetrofitManager;
import com.tanwuyu.ivrtym.bucketanime.model.VideoReply;

import io.reactivex.Flowable;

/**
 * Created by ivrty on 2017/5/4.
 */

public class VideoReplyImp {
    /**
     * 获取指定Video 下 所有评论
     * where={"post":{"__type":"Pointer","className":"Post","objectId":"1dafb9ed9b"}}
     * @param limit
     * @param skip
     * @param order
     * @param videoId
     * @return
     */
    public Flowable<BaseMultiDatasResponse<VideoReply>> getRepliesOfVideo(int limit,int skip,String order,String videoId){
        String requirement = "{\"superVideo\":{\"__type\":\"Pointer\",\"className\":\"Video\",\"objectId\":\""+videoId+"\"}}";
        return RetrofitManager.getInstance()
                .crateApiService(IReplyService.class)
                .getVideoRepliesByRequirement(limit,skip,order,requirement);
    }

    /**
     * 获取指定评论下的所有子回复
     * @return
     */
    public Flowable<BaseMultiDatasResponse<VideoReply>> getRepliesOfVideoReply(@NonNull int limit,@NonNull int skip,@NonNull String order,@NonNull String replyId){
        String requirement="{\"targetReply\":{\"__type\":\"Pointer\",\"className\":\"VideoReply\",\"objectId\":\"" + replyId + "\"}}";
        return RetrofitManager.getInstance()
                .crateApiService(IReplyService.class)
                .getVideoRepliesByRequirement(limit,skip,"-createdAt",requirement);
    }

    /**
     * 发送一条视频评论
     */
    public Flowable<VideoReply> sendSingleReply(@NonNull String uerId,@NonNull String videoId,@Nullable String targetReplyId){
        VideoReply videoReply = new VideoReply();
        VideoReply.ReplyAuthorBean replyAuthorBean = new VideoReply.ReplyAuthorBean();
        VideoReply.SuperVideoBean superVideoBean = new VideoReply.SuperVideoBean();
        VideoReply.TargetReplyBean targetReplyBean = new VideoReply.TargetReplyBean();

        replyAuthorBean.set__type("Pointer");
        replyAuthorBean.setClassName("_User");
        replyAuthorBean.setObjectId(uerId);

        superVideoBean.set__type("Pointer");
        superVideoBean.setClassName("Video");
        superVideoBean.setObjectId(videoId);

        targetReplyBean.set__type("Pointer");
        targetReplyBean.setClassName("VideoReply");
        targetReplyBean.setObjectId(targetReplyId);

        videoReply.setReplyAuthor(replyAuthorBean);
        videoReply.setSuperVideo(superVideoBean);
        if (targetReplyId!=null){
            videoReply.setTargetReply(targetReplyBean);
        }

        return RetrofitManager.getInstance()
                .crateApiService(IReplyService.class)
                .addSingleVideoReply(videoReply);
    }
}
