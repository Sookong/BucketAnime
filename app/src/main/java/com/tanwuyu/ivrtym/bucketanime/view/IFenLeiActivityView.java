package com.tanwuyu.ivrtym.bucketanime.view;

import android.view.View;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;

import java.util.List;

/**
 * Created by ivrty on 2017/4/19.
 */

public interface IFenLeiActivityView {
    void loadResultsToRcv(List<Anime> animes);
    String getAreaChoose();
    String getFenLeiChoose();
    int getTimeChoose();
    String getOrder();
    void showOrderPopWindow(View view);
    int getStateChoose();
    void notifyAutoLoadMoreComplete(boolean isError,String errorMsg);
}
