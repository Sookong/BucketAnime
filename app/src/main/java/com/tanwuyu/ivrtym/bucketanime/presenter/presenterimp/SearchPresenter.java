package com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp;

import android.util.Log;

import com.tanwuyu.ivrtym.bucketanime.api.BaseMultiDatasResponse;
import com.tanwuyu.ivrtym.bucketanime.base.BasePresenter;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.HotSearch;
import com.tanwuyu.ivrtym.bucketanime.model.modelimp.AnimeImp;
import com.tanwuyu.ivrtym.bucketanime.model.modelimp.HotSearchImp;
import com.tanwuyu.ivrtym.bucketanime.presenter.ISearchPresenter;
import com.tanwuyu.ivrtym.bucketanime.utils.SharedPreferencesUtil;
import com.tanwuyu.ivrtym.bucketanime.view.ISearchView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DefaultSubscriber;
import io.reactivex.subscribers.DisposableSubscriber;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by ivrty on 2017/3/19.
 */

public class SearchPresenter extends BasePresenter<ISearchView> implements ISearchPresenter {
    AnimeImp animeImp;


    public SearchPresenter(){
        compositeDisposable = new CompositeDisposable();
        animeImp = new AnimeImp();
    }


    @Override
    public void doSearch() {
        String searchText = mView.getSearchText();
        if (!"".equals(searchText)) {
            //执行搜索



            //先保存到搜索记录
            Flowable.just(SharedPreferencesUtil.setSearchRecords(searchText))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(@NonNull Boolean aBoolean) throws Exception {
                            if (aBoolean){
                                //保存成功 更新界面数据
                                loadSearchRecords();
                            }
                        }
                    });

            //Log.e("doSearch", "here");
            //
        }
    }

    @Override
    public void clearSearchRecords() {
        Flowable.just(SharedPreferencesUtil.clearSearchRecords())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean){
                            loadSearchRecords();
                        }
                    }
                });

    }

    @Override
    public void loadSearchRecords() {
        Flowable.just(SharedPreferencesUtil.getSearchRecords())
                .map(new Function<LinkedHashSet<String>,List<String>>() {
                    @Override
                    public List<String> apply(@NonNull LinkedHashSet<String> strings) throws Exception {
                        List<String> s = new ArrayList<String>(strings);
                        Collections.reverse(s);
                        return s;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(List<String> strings) {
                        mView.loadSearchRecordToRcv(strings);
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
    public void loadSearchResult(String searchText, int skip) {


    }

    @Override
    public void loadHotSearchs(int skip) {
        HotSearchImp hotSearchImp = new HotSearchImp();
        hotSearchImp.getHotSearchs(10, skip)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BaseMultiDatasResponse<HotSearch>>() {
                    @Override
                    public void onNext(BaseMultiDatasResponse<HotSearch> hotSearchBaseMultiDatasResponse) {
                        List<HotSearch> hotSearches=hotSearchBaseMultiDatasResponse.getResults();

                        mView.loadHotSearchToFollowLayout(hotSearches);
                        mView.loadHotSearchToRcv(hotSearches);
                    }

                    @Override
                    public void onError(Throwable t) {
                        dispose();
                    }

                    @Override
                    public void onComplete() {
                        dispose();
                    }
                });
    }

    @Override
    public void loadSearchInputConnect() {
        String searchText = mView.getSearchText();
        if (!searchText.isEmpty()){
            animeImp.getAnimesByName(5,0,"-followCount",searchText)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<BaseMultiDatasResponse<Anime>>() {
                        @Override
                        public void onSubscribe(Subscription s) {
                            Log.e("Presenter_InputConnect","start");
                            s.request(Long.MAX_VALUE);
                        }

                        @Override
                        public void onNext(BaseMultiDatasResponse<Anime> animeBaseMultiDatasResponse) {
                            List<Anime> animes = animeBaseMultiDatasResponse.getResults();
                            if (!animes.isEmpty()){
                                Log.e("Presenter_Suc",String.valueOf(animes.size()));
                                //成功取得输入名称相关动漫,加载数据,显示popupWindow
                                mView.loadSearchInputConnectToRcv(animes);
                                mView.showInputConnectPopWindow();
                            }else {
                                mView.dismissInputConnectPopWindow();
                            }
                        }

                        @Override
                        public void onError(Throwable t) {
                            Log.e("Presenter_ERR",t.toString());
                            mView.dismissInputConnectPopWindow();
                        }

                        @Override
                        public void onComplete() {
                            Log.e("Presenter_Comp","end");
                        }
                    });
        }
    }
}
