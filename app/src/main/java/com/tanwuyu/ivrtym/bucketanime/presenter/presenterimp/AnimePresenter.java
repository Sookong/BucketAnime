package com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp;

import com.google.gson.GsonBuilder;
import com.tanwuyu.ivrtym.bucketanime.api.BaseMultiDatasResponse;
import com.tanwuyu.ivrtym.bucketanime.api.BmobHttpError;
import com.tanwuyu.ivrtym.bucketanime.base.BasePresenter;
import com.tanwuyu.ivrtym.bucketanime.base.MyApplication;
import com.tanwuyu.ivrtym.bucketanime.model.AnimeReply;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.User;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Video;
import com.tanwuyu.ivrtym.bucketanime.model.modelimp.AnimeImp;
import com.tanwuyu.ivrtym.bucketanime.model.modelimp.UserImp;
import com.tanwuyu.ivrtym.bucketanime.model.modelimp.VideoImp;
import com.tanwuyu.ivrtym.bucketanime.presenter.IAnimePresenter;
import com.tanwuyu.ivrtym.bucketanime.view.IAnimeView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * Created by ivrty on 2017/3/31.
 */

public class AnimePresenter extends BasePresenter<IAnimeView> implements IAnimePresenter {
    AnimeImp animeImp;
    VideoImp videoImp;
    UserImp userImp;

    public AnimePresenter() {
        animeImp = new AnimeImp();
        videoImp = new VideoImp();
    }

    @Override
    public void doFollow() {

    }

    @Override
    public void doReply() {
        String targetAnimeId = mView.getTotalAnimeId();
        AnimeReply targetReply = mView.getUserReplyTarget();
    }

    @Override
    public void loadAnime() {
        User totalUser = MyApplication.getInstance().getCurrentUser();
        Anime totalAnime = mView.getTotalAnime();
        String totalAnimeId = mView.getTotalAnimeId();
        //加载基本信息
        if (totalAnime != null) {
            mView.setBaseAnieInfo(totalAnime);
        } else {
            compositeDisposable.add(animeImp.getAnimeById(totalAnimeId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new ResourceSubscriber<Anime>() {
                        @Override
                        public void onNext(Anime anime) {
                            mView.setBaseAnieInfo(anime);
                        }

                        @Override
                        public void onError(Throwable t) {
                            if (t instanceof HttpException) {
                                try {
                                    String errorMsgJson = ((HttpException) t).response().errorBody().string();
                                    BmobHttpError httpError = new GsonBuilder().create().fromJson(errorMsgJson, BmobHttpError.class);
                                    mView.showToast(httpError.getError());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                mView.showToast("网络好像出了点问题~~T_T");
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    }));

        }
        //判断用户是否收藏了该动漫
        compositeDisposable.add(animeImp.getUserFollowAnime(9999, 0, totalUser.getObjectId(), totalAnimeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseMultiDatasResponse<Anime>>() {
                    @Override
                    public void accept(@NonNull BaseMultiDatasResponse<Anime> animeBaseMultiDatasResponse) throws Exception {
                        List<Anime> userFollowAnimes = animeBaseMultiDatasResponse.getResults();
                        if (userFollowAnimes.size() > 0) {
                            mView.enableFollowButton(true);
                        } else {
                            mView.enableFollowButton(false);
                        }
                    }

                }));

    }

    @Override
    public void loadAnimeVideos() {
        String totalAnimeId = mView.getTotalAnimeId();
        compositeDisposable.add(videoImp.getVideosBySuperAnimeId(totalAnimeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<BaseMultiDatasResponse<Video>>() {
                    @Override
                    public void onNext(BaseMultiDatasResponse<Video> videoBaseMultiDatasResponse) {
                        List<Video> videos = videoBaseMultiDatasResponse.getResults();
                        mView.setAnimeVideosToRcv(videos);
                        mView.setAnimeVideoCount(videos.size());
                    }

                    @Override
                    public void onError(Throwable t) {
                        mView.dismissAnimeVideosRcv();
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void loadAnimeReplies() {

    }

    @Override
    public void loadMoreAnimeReplies(int skip) {

    }

    @Override
    public void loadTuijianList() {

    }
}
