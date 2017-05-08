package com.tanwuyu.ivrtym.bucketanime.view.activity;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding2.view.RxView;
import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.base.BaseMvpActivity;
import com.tanwuyu.ivrtym.bucketanime.base.MyApplication;
import com.tanwuyu.ivrtym.bucketanime.model.AnimeReply;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Video;
import com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp.AnimePresenter;
import com.tanwuyu.ivrtym.bucketanime.utils.StringUtil;
import com.tanwuyu.ivrtym.bucketanime.view.IAnimeView;
import com.tanwuyu.ivrtym.seibertronrecyclerview.AutoLoadSupport;
import com.tanwuyu.ivrtym.seibertronrecyclerview.SectionSupport;
import com.tanwuyu.ivrtym.seibertronrecyclerview.SeibertronAdapter;
import com.tanwuyu.ivrtym.seibertronrecyclerview.SeibertronRecyclerView;
import com.tanwuyu.ivrtym.seibertronrecyclerview.SeibertronSupportException;
import com.tanwuyu.ivrtym.seibertronrecyclerview.SeibertronViewHolder;
import com.tanwuyu.ivrtym.seibertronrecyclerview.SingleTypeSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by ivrty on 2017/3/21.
 */

public class AnimeActivity extends BaseMvpActivity<IAnimeView, AnimePresenter> implements IAnimeView {

    @BindView(R.id.sdv_head_background)
    SimpleDraweeView sdvHeadBackground;
    @BindView(R.id.frame_head_cover)
    FrameLayout frameHeadCover;
    @BindView(R.id.sdv_anime_img)
    SimpleDraweeView sdvAnimeImg;
    @BindView(R.id.tv_update_state)
    TextView tvUpdateState;
    @BindView(R.id.tv_area)
    TextView tvArea;
    @BindView(R.id.tv_play_count)
    TextView tvPlayCount;
    @BindView(R.id.tv_follow_count)
    TextView tvFollowCount;
    @BindView(R.id.tv_reply_count)
    TextView tvReplyCount;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.container_head)
    LinearLayout containerHead;
    @BindView(R.id.tv_simple_introduce)
    TextView tvSimpleIntroduce;
    @BindView(R.id.container_introduce)
    LinearLayout containerIntroduce;
    @BindView(R.id.tv_play_record)
    TextView tvPlayRecord;
    @BindView(R.id.container_videos)
    LinearLayout containerVideos;
    @BindView(R.id.container_head_cover)
    LinearLayout containerHeadCover;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.sdv_user_head_img)
    SimpleDraweeView sdvUserHeadImg;
    @BindView(R.id.tv_quick_reply)
    TextView tvQuickReply;
    @BindView(R.id.container_quick_reply)
    LinearLayout containerQuickReply;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.tv_anime_name)
    TextView tvAnimeName;
    @BindView(R.id.container_anime_name)
    LinearLayout containerAnimeName;
    @BindView(R.id.frame_head_background)
    FrameLayout frameHeadBackground;
    @BindView(R.id.tv_anime_video_count)
    TextView tvAnimeVideoCount;
    @BindView(R.id.tv_more_introduce)
    TextView tvMoreIntroduce;
    @BindView(R.id.container_tui_jian)
    LinearLayout containerTuiJian;
    @BindView(R.id.iv_key_back)
    ImageView ivKeyBack;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    @BindView(R.id.rcv_tui_jian)
    SeibertronRecyclerView rcvTuiJian;
    @BindView(R.id.rcv_reply)
    SeibertronRecyclerView rcvReply;
    @BindView(R.id.rcv_videos)
    SeibertronRecyclerView rcvVideos;

    SeibertronAdapter<Video> videoRcvAdapter;
    SeibertronAdapter<Anime> tuijianRcvAdapter;
    SeibertronAdapter<AnimeReply> animeReplyRcvAdapter;

    PopupWindow inputPopupWindow;
    View popupWindowView;
    EditText etReplyInput;
    ImageView ivSendReply;

    Anime totalAnime;
    String totalAnimeId;
    AnimeReply replyTarget;

    List<Video> videos;
    List<AnimeReply> replies;
    List<Anime> tuijianList;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        ButterKnife.bind(this);
    }

    @Override
    public AnimePresenter creatPresenter() {
        return new AnimePresenter();
    }

    @Override
    public void initView() throws SeibertronSupportException {
        //初始化弹出回复框popupWindow
        popupWindowView = LayoutInflater.from(this).inflate(R.layout.popupwindow_reply, null);
        inputPopupWindow = new PopupWindow(popupWindowView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        ColorDrawable colorDrawable = new ColorDrawable();
        inputPopupWindow.setBackgroundDrawable(colorDrawable);

        //回复数点击监听
        RxView.clicks(tvReplyCount)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {

                    }
                });
        //追番点击监听
        RxView.clicks(tvFollow)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (MyApplication.getInstance().getCurrentUser() != null) {
                            mPresenter.doFollow();
                        } else {
                            showLoginDialog();
                        }
                    }
                });
        //分享点击监听
        RxView.clicks(tvShare)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        showShareWindow();
                    }
                });
        //更多介绍点击监听
        RxView.clicks(tvMoreIntroduce)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        goAnimeInfoActivity();
                    }
                });
        //快速回复点击监听
        RxView.clicks(tvQuickReply)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        etReplyInput.setHint("说点什么呢~");
                        showInputPopupWindow();
                    }
                });
        //发送回复点击监听
        RxView.clicks(ivSendReply)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        mPresenter.doReply();
                    }
                });




        //初始化选集列表
        videoRcvAdapter = new SeibertronAdapter<Video>(videos,this);
        rcvVideos.setAdapter(videoRcvAdapter);
        try {
            videoRcvAdapter.addSingleTypeSupport(new SingleTypeSupport<Video>() {
                @Override
                public void bindDataToHolder(SeibertronViewHolder viewHolder, Video data) {

                }

                @Override
                public int getHolderLayoutResourceId() {
                    return 0;
                }
            });
        } catch (SeibertronSupportException e) {
            e.printStackTrace();
        }
        //初始化推荐列表
        tuijianRcvAdapter = new SeibertronAdapter<Anime>(tuijianList,this);
        rcvTuiJian.setAdapter(tuijianRcvAdapter);
        try {
            tuijianRcvAdapter.addSingleTypeSupport(new SingleTypeSupport<Anime>() {
                @Override
                public void bindDataToHolder(SeibertronViewHolder viewHolder, Anime data) {

                }

                @Override
                public int getHolderLayoutResourceId() {
                    return 0;
                }
            });
        } catch (SeibertronSupportException e) {
            e.printStackTrace();
        }

        //初始化回复列表
        animeReplyRcvAdapter = new SeibertronAdapter<AnimeReply>(replies,this);
        rcvReply.setAdapter(animeReplyRcvAdapter);
        animeReplyRcvAdapter.addSingleTypeSupport(new SingleTypeSupport<AnimeReply>() {
            @Override
            public void bindDataToHolder(SeibertronViewHolder viewHolder, AnimeReply data) {

            }

            @Override
            public int getHolderLayoutResourceId() {
                return 0;
            }
        });
        animeReplyRcvAdapter.addSectionSupport(new SectionSupport<Anime>() {
            @Override
            public int getTitleHolderLayoutResourceId() {
                return 0;
            }

            @Override
            public int getBottomHolderLayoutResourceId() {
                return 0;
            }

            @Override
            public void bindTitleHolder(int titleHolderLayoutResourceId, SeibertronViewHolder viewHolder, int sectionTag) {

            }

            @Override
            public void bindBottomHolder(int bottomHolderLayoutResourceId, SeibertronViewHolder viewHolder, int sectionTag) {

            }

            @Override
            public List<SectionInfo> calculatSections(List<Anime> datas) {
                List<SectionInfo> sectionInfos = new ArrayList<SectionInfo>();

                return null;
            }
        });
        animeReplyRcvAdapter.addAutoLoadSupport(new AutoLoadSupport() {
            @Override
            public int getBottomViewLayoutResourceId() {
                return 0;
            }

            @Override
            public void bindBottomHolder(SeibertronViewHolder bottomHolder) {

            }

            @Override
            public void onLoadMore(int totalDatasSize) {

            }

            @Override
            public void onLoadAllComplete() {

            }

            @Override
            public void onLoadError() {

            }

            @Override
            public void onLoadSuccess() {

            }
        });
    }

    @Override
    public void loadData() {
        mPresenter.loadAnime();

        mPresenter.loadAnimeVideos();

        mPresenter.loadTuijianList();

        mPresenter.loadAnimeReplies();
    }

    @Override
    public void initVars() {
        totalAnime = (Anime) getIntent().getSerializableExtra("anime");
        totalAnimeId = getIntent().getStringExtra("animeId");
        if (totalAnime!=null){
            totalAnimeId = totalAnime.getObjectId();
        }


        videos = new ArrayList<>();
        replies = new ArrayList<>();
        tuijianList = new ArrayList<>();
    }


    /**
     * Override V_interface
     */

    @Override
    public void setBaseAnieInfo(Anime anime) {
        int state = anime.getAnimeState();
        int updateWeekDay = anime.getUpdateWeek();
        int playCount = anime.getPlayCount();
        int followCount = anime.getFollowCount();
        String animeName = anime.getAnimeName();
        String animeSimpleIntroduce = anime.getSimpleIntroduce();
        String animeArea = anime.getAnimeArea();


        switch (state) {
            case 0:
                tvUpdateState.setText("已完结");
                break;
            case 1:
                tvUpdateState.setText("连载中/" + "每周" + StringUtil.numToExactChineseMathCharacter(updateWeekDay) + "更新");
                break;
            default:
                tvUpdateState.setText("即将上映");
        }

        if (playCount < 10000) {
            tvPlayCount.setText("播放:" + playCount);
        } else {
            tvPlayCount.setText("播放:" + StringUtil.numToSimilarChineseMathUnitCharacter(playCount));
        }

        if (followCount < 10000) {
            tvFollowCount.setText("追番:" + followCount);
        } else {
            tvFollowCount.setText("追番:" + StringUtil.numToSimilarChineseMathUnitCharacter(followCount));
        }

        tvAnimeName.setText(animeName);
        tvSimpleIntroduce.setText(animeSimpleIntroduce);
        tvArea.setText("地区:" + animeArea);
    }

    @Override
    public void setAnimeReplyCount(int replyCount) {
        tvReplyCount.setText(String.valueOf(replyCount) + "评论");
    }

    @Override
    public void disableFollowButton() {
        tvFollow.setEnabled(false);
        tvFollow.setText("追番");
    }

    @Override
    public void enableFollowButton(Boolean hasFollowed) {
        tvFollow.setEnabled(true);
        if (hasFollowed) {
            tvFollow.setText("已追");
            tvFollow.setCompoundDrawables(null, null, null, null);
        } else {
            tvFollow.setText("追番");
            tvFollow.setCompoundDrawables(null, null, null, null);
        }
    }


    @Override
    public void setAnimeVideosToRcv(List<Video> videos) {
        this.videos.clear();
        this.videos.addAll(videos);
        videoRcvAdapter.notifyDataSetChanged();
    }

    @Override
    public void setAnimeVideoCount(int animeVideoCount) {
        tvAnimeVideoCount.setText("更新至第" + String.valueOf(animeVideoCount) + "集");
    }

    @Override
    public void setAnimeReplysToRcv(List<AnimeReply> animeReplies) {
        replies.clear();
        replies.addAll(animeReplies);
        animeReplyRcvAdapter.notifyDataSetChanged();
    }

    @Override
    public void addAnimeReplysToRcv(List<AnimeReply> animeReplies) {
        replies.addAll(animeReplies);
        animeReplyRcvAdapter.notifyDataSetChanged();
    }

    @Override
    public void setTuijianToRcv(List<Anime> tuijians) {
        tuijianList.clear();
        tuijianList.addAll(tuijians);
        tuijianRcvAdapter.notifyDataSetChanged();
    }

    @Override
    public String getUserReplyInput() {
        String replyInput = etReplyInput.getText().toString();
        return replyInput;
    }

    @Override
    public AnimeReply getUserReplyTarget() {
        return replyTarget;
    }

    @Override
    public void setUserReplyTarget(AnimeReply replyTarget) {
        this.replyTarget = replyTarget;
        if (replyTarget!=null){
            String targetUserName = replyTarget.getReplyAuthorName();
            etReplyInput.setHint("回复:" + targetUserName);
        }else {
            etReplyInput.setHint("我也来说一句~~!");
        }

    }

    @Override
    public void showInputPopupWindow() {
        inputPopupWindow.showAtLocation(toolBar, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void dismissInputPopupWindow() {
        if (inputPopupWindow.isShowing()) {
            inputPopupWindow.dismiss();
        }
    }

    @Override
    public void showLoginDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setIcon(R.drawable.ic_login_logo);
        dialogBuilder.setTitle("登录提示");
        dialogBuilder.setMessage("您需要先登录才能追番哦~~!");
        dialogBuilder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goLoginActivity();
            }
        });
        dialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogBuilder.setCancelable(true);
        dialogBuilder.show();
    }

    @Override
    public void showShareWindow() {

    }

    @Override
    public void goAnimeInfoActivity() {

    }

    @Override
    public void goLoginActivity() {

    }
}
