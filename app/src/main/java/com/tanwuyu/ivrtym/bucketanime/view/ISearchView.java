package com.tanwuyu.ivrtym.bucketanime.view;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.HotSearch;

import java.util.List;

/**
 * Created by ivrty on 2017/3/19.
 */

public interface ISearchView {
    String getSearchText();
    void  setSearchText(String searchText);
    //加载热门搜索数据
    void loadHotSearchToFollowLayout(List<HotSearch> hotSearches);
    //加载搜索记录数据
    void loadSearchRecordToRcv(List<String> searchRecords);
    //加载输入相关数据
    void loadSearchInputConnectToRcv(List<Anime> animes);
    //加载成功数据
    void loadSearchResultsToRcv(List<Anime> animes);
    //加载失败数据
    void loadHotSearchToRcv(List<HotSearch> hotSearches);
    void loadErrorMsgToTv(String errorMsg);

    //显示对应状态页面
    void showErrorPage();
    void showPreSearchPage();
    void showSucessPage();

    void showInputConnectPopWindow();
    void dismissInputConnectPopWindow();



}
