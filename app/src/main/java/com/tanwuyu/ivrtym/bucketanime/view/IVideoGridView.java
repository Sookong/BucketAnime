package com.tanwuyu.ivrtym.bucketanime.view;

import android.content.Intent;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Video;

import java.util.List;

/**
 * Created by ivrty on 2017/4/6.
 */

public interface IVideoGridView {
    void setDatasToRcv(List<Video> videos);
    void showError();
}
