package com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp;

import com.tanwuyu.ivrtym.bucketanime.api.BaseMultiDatasResponse;
import com.tanwuyu.ivrtym.bucketanime.base.BasePresenter;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Video;
import com.tanwuyu.ivrtym.bucketanime.model.modelimp.VideoImp;
import com.tanwuyu.ivrtym.bucketanime.presenter.IVideoGridPresenter;
import com.tanwuyu.ivrtym.bucketanime.presenter.IXiangGuanTuiJianPresenter;
import com.tanwuyu.ivrtym.bucketanime.view.IVideoGridView;
import com.tanwuyu.ivrtym.bucketanime.view.fragment.VideoGridFragment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ivrty on 2017/4/6.
 */

public class VideoGridPresenter extends BasePresenter<IVideoGridView> implements IVideoGridPresenter{
    VideoImp videoImp;
    @Override
    public void getVideos(String animeId) {
        videoImp = new VideoImp();
        videoImp.getVideosBySuperAnimeId(animeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseMultiDatasResponse<Video>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(BaseMultiDatasResponse<Video> videoBaseMultiDatasResponse) {
                        List<Video> videos = videoBaseMultiDatasResponse.getResults();
                        mView.setDatasToRcv(videos);
                    }

                    @Override
                    public void onError(Throwable t) {
                        mView.showError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
