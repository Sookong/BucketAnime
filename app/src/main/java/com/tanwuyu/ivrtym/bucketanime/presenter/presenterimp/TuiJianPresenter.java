package com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp;

import com.tanwuyu.ivrtym.bucketanime.api.BaseMultiDatasResponse;
import com.tanwuyu.ivrtym.bucketanime.base.BasePresenter;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.TuiJianData;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.TuiJianHeadData;
import com.tanwuyu.ivrtym.bucketanime.model.modelimp.AnimeImp;
import com.tanwuyu.ivrtym.bucketanime.model.modelimp.TuiJianImp;
import com.tanwuyu.ivrtym.bucketanime.presenter.ITuiJianPresenter;
import com.tanwuyu.ivrtym.bucketanime.view.ITuijianView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ivrtym on 2017/3/16.
 */

public class TuiJianPresenter extends BasePresenter<ITuijianView> implements ITuiJianPresenter {
    TuiJianImp tuiJianImp;

    public TuiJianPresenter() {
        tuiJianImp = new TuiJianImp();
    }

    @Override
    public void loadTuiJianDatas() {
        tuiJianImp.getTuiJianDatas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseMultiDatasResponse<TuiJianData>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(BaseMultiDatasResponse<TuiJianData> tuiJianDataBaseMultiDatasResponse) {
                        List<TuiJianData> tuiJianDatas = tuiJianDataBaseMultiDatasResponse.getResults();
                        if (tuiJianDatas!=null&&tuiJianDatas.size()>0)
                            mView.upDateTuiJianDatas(tuiJianDatas);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadHeaderDatas() {
        tuiJianImp.getTuiJianHeadDatas()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseMultiDatasResponse<TuiJianHeadData>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(BaseMultiDatasResponse<TuiJianHeadData> tuiJianHeadDataBaseMultiDatasResponse) {
                        List<TuiJianHeadData> tuiJianHeadDatas= tuiJianHeadDataBaseMultiDatasResponse.getResults();
                        if (tuiJianHeadDatas!=null&&tuiJianHeadDatas.size()>0)
                            mView.upDateHeaderDatas(tuiJianHeadDatas);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
