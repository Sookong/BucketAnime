package com.tanwuyu.ivrtym.bucketanime.view;

import android.content.Intent;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.TuiJianData;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.TuiJianHeadData;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Video;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.viewentity.BeanTuijian;

import java.util.List;

/**
 * Created by ivrtym on 2017/3/1.
 */

public interface ITuijianView {
    void toAnimeActivity(String animeId);
    void toVideoActivity(String videoId);
    void toAdActivity(String url);
    void upDateTuiJianDatas(List<TuiJianData> datas);
    void upDateHeaderDatas(List<TuiJianHeadData> headDatas);
}
