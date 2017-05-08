package com.tanwuyu.ivrtym.bucketanime.presenter;

/**
 * Created by ivrty on 2017/3/19.
 */

public interface ISearchPresenter {
    void doSearch();

    void clearSearchRecords();

    void loadSearchRecords();

    void loadSearchResult(String searchText,int skip);

    void loadHotSearchs(int skip);

    void loadSearchInputConnect();


}
