package com.tanwuyu.ivrtym.bucketanime.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.adapter.HomePageAdapter;
import com.tanwuyu.ivrtym.bucketanime.base.BaseMvpActivity;
import com.tanwuyu.ivrtym.bucketanime.base.BasePresenter;
import com.tanwuyu.ivrtym.bucketanime.base.MyApplication;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.User;
import com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp.HomePresenter;
import com.tanwuyu.ivrtym.bucketanime.view.IHomeView;
import com.tanwuyu.ivrtym.bucketanime.view.fragment.FaXianFragment;
import com.tanwuyu.ivrtym.bucketanime.view.fragment.FenLeiFragment;
import com.tanwuyu.ivrtym.bucketanime.view.fragment.TuiJianFragment;
import com.tanwuyu.ivrtym.bucketanime.view.fragment.XinFanFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ivrtym on 2017/3/17.
 */

public class HomeActivity extends BaseMvpActivity implements IHomeView, View.OnClickListener {

    User currentUser;

    @BindView(R.id.draweeview_user_img)
    SimpleDraweeView draweeviewUserImg;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_history)
    ImageView ivHistory;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.draweeview_nav_user_img)
    SimpleDraweeView draweeviewNavUserImg;
    @BindView(R.id.tv_nav_username)
    TextView tvNavUsername;
    @BindView(R.id.tv_message)
    TextView tvMessage;
    @BindView(R.id.tv_history)
    TextView tvHistory;
    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.tv_feedback)
    TextView tvFeedback;
    @BindView(R.id.navigationview)
    LinearLayout navigationview;
    @BindView(R.id.drawerLayout_home)
    DrawerLayout drawerLayoutHome;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        currentUser = MyApplication.getInstance().getCurrentUser();

        initToolbar();
        initDrawerLayout();
        initViewPager();
    }

    @Override
    public BasePresenter creatPresenter() {
        return new HomePresenter();
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void initVars() {

    }


    //初始化ViewPager
    private void initViewPager() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new TuiJianFragment());
        fragments.add(new XinFanFragment());
        fragments.add(new FenLeiFragment());
        fragments.add(new FaXianFragment());

        viewpager.setAdapter(new HomePageAdapter(fragments, getSupportFragmentManager(), this));
        viewpager.setOffscreenPageLimit(4);
        tabLayout.setupWithViewPager(viewpager);

        viewpager.setCurrentItem(0);
    }

    //初始化侧划抽屉
    private void initDrawerLayout() {
        //if ()

    }

    //初始化toolbar
    private void initToolbar() {
        User currentUser = MyApplication.getInstance().getCurrentUser();
        //文本加粗
        TextPaint paint = tvTitle.getPaint();
        paint.setFakeBoldText(true);
        if (currentUser == null) {
            //draweeviewUserImg.ur;
            tvTitle.setText(currentUser.getUsername());
            draweeviewUserImg.setImageURI(currentUser.getImgUrl());
        } else {
            tvTitle.setText("8KAnime");
            //draweeviewUserImg.setImageResource();
        }

        tvTitle.setOnClickListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title:
            case R.id.draweeview_user_img:
            case R.id.iv_toggle:
                drawerLayoutHome.openDrawer(navigationview);
                break;
            case R.id.tv_message:
                if (currentUser == null)
                    notifyLogin();
                else
                    goMessageActivity();
                break;
            case R.id.tv_history:
            case R.id.iv_history:
                goHistoryActivity();
                break;
            case R.id.tv_collect:
                goCollectActivity();
                break;
            case R.id.tv_follow:
                goFollowActivity();
                break;
            case R.id.tv_setting:
                goSettingActivity();
                break;
            case R.id.tv_feedback:
                goFeedBackActivity();
                break;
            case R.id.iv_search:
                goPreSearchActivity();
                break;

        }
    }

    void goHistoryActivity() {

    }

    void goCollectActivity() {

    }

    void goSettingActivity() {

    }

    void goPreSearchActivity() {

    }

    void goFollowActivity() {

    }

    void goMessageActivity() {

    }

    void goFeedBackActivity() {

    }

    void notifyLogin() {

    }
}
