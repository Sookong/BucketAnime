package com.tanwuyu.ivrtym.bucketanime.view;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Video;

import java.util.List;

/**
 * Created by ivrty on 2017/4/6.
 */

public interface IXiangGuanTuiJianView {
   void loadDatasToRcv(List<Anime> animes);
}
