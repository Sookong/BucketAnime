package com.tanwuyu.ivrtym.bucketanime.view;

import com.tanwuyu.ivrtym.bucketanime.model.AnimeReply;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Video;

import java.util.List;

/**
 * Created by ivrty on 2017/3/31.
 */

public interface IAnimeView {

    String getTotalAnimeId();
    Anime getTotalAnime();

    void setBaseAnieInfo(Anime anime);
    void setAnimeReplyCount(int replyCount);

    void seFollowButton();
    void enableFollowButton(Boolean hasFollowed);



    void setAnimeVideosToRcv(List<Video> videos);
    void setAnimeVideoCount(int animeVideoCount);
    void dismissAnimeVideosRcv();

    void setAnimeReplysToRcv(List<AnimeReply> animeReplies);
    void addAnimeReplysToRcv(List<AnimeReply> animeReplies);

    void setTuijianToRcv(List<Anime> tuijians);

    String getUserReplyInput();
    AnimeReply getUserReplyTarget();

    void setUserReplyTarget(AnimeReply replyTarget);

    void showInputPopupWindow();
    void dismissInputPopupWindow();

    void showLoginDialog();
    void showShareWindow();

    void goAnimeInfoActivity();
    void goLoginActivity();

    void showToast(String msg);

}
