package com.tanwuyu.ivrtym.bucketanime.model.modelimp;

import com.tanwuyu.ivrtym.bucketanime.api.BaseMultiDatasResponse;
import com.tanwuyu.ivrtym.bucketanime.api.ITuiJianService;
import com.tanwuyu.ivrtym.bucketanime.api.RetrofitManager;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.TuiJianData;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.TuiJianHeadData;

import io.reactivex.Flowable;

/**
 * Created by ivrty on 2017/4/1.
 */

public class TuiJianImp {
    public Flowable<BaseMultiDatasResponse<TuiJianData>> getTuiJianDatas() {
        return RetrofitManager.getInstance()
                .crateApiService(ITuiJianService.class)
                .getTuiJianDatas(100, 0, "contentType", "contentAnime,contentVideo");
    }

    public Flowable<BaseMultiDatasResponse<TuiJianHeadData>> getTuiJianHeadDatas() {
        return RetrofitManager.getInstance()
                .crateApiService(ITuiJianService.class)
                .getTuiJianHeadDatas(100, 0, "contentType", "contentAnime,contentVideo");
    }
}
