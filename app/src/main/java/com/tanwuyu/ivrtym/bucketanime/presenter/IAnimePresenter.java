package com.tanwuyu.ivrtym.bucketanime.presenter;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.User;

/**
 * Created by ivrty on 2017/3/31.
 */

public interface IAnimePresenter {
    void doFollow();
    void doReply();

    void loadAnime();

    void loadAnimeVideos();
    void loadAnimeReplies();
    void loadMoreAnimeReplies(int skip);
    void loadTuijianList();
}
