package com.tanwuyu.ivrtym.bucketanime.view;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;

import java.util.List;

/**
 * Created by ivrty on 2017/4/11.
 */

public interface IWeekUpdate {
     void loadDatasToRcv(List<Anime> animes);
     void showError();
}
