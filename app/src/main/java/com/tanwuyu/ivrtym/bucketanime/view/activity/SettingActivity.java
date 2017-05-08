package com.tanwuyu.ivrtym.bucketanime.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.base.BaseMvpActivity;
import com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp.SettingPresenter;
import com.tanwuyu.ivrtym.bucketanime.view.ISettingView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ivrty on 2017/4/27.
 */

public class SettingActivity extends BaseMvpActivity<ISettingView, SettingPresenter> implements ISettingView {
    @BindView(R.id.iv_key_back)
    ImageView ivKeyBack;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv_decode_setting)
    TextView tvDecodeSetting;
    @BindView(R.id.tv_current_decode)
    TextView tvCurrentDecode;
    @BindView(R.id.tv_allow_mobile_net_play)
    TextView tvAllowMobileNetPlay;
    @BindView(R.id.switch_allow_mobile_net_play)
    Switch switchAllowMobileNetPlay;
    @BindView(R.id.tv_allow_push_msg)
    TextView tvAllowPushMsg;
    @BindView(R.id.switch_allow_push_msg)
    Switch switchAllowPushMsg;
    @BindView(R.id.tv_clear_play_record)
    TextView tvClearPlayRecord;
    @BindView(R.id.tv_clear_input_record)
    TextView tvClearInputRecord;
    @BindView(R.id.tv_clear_img_cache)
    TextView tvClearImgCache;
    @BindView(R.id.tv_apply_default_setting)
    TextView tvApplyDefaultSetting;
    @BindView(R.id.tv_check_update)
    TextView tvCheckUpdate;
    @BindView(R.id.tv_current_version)
    TextView tvCurrentVersion;
    @BindView(R.id.tv_about_us)
    TextView tvAboutUs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @Override
    public SettingPresenter creatPresenter() {
        return new SettingPresenter();
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
}
