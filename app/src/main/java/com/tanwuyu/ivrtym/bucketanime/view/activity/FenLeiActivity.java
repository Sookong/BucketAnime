package com.tanwuyu.ivrtym.bucketanime.view.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.adapter.AnimeGrid_RcvAdapter;
import com.tanwuyu.ivrtym.bucketanime.base.BaseMvpActivity;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Anime;
import com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp.FenLeiActivityPresenter;
import com.tanwuyu.ivrtym.bucketanime.view.IFenLeiActivityView;
import com.tanwuyu.ivrtym.bucketanime.widget.AutoLoadMoreWithLayoutManagerSwitchRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ivrty on 2017/4/19.
 */

public class FenLeiActivity extends BaseMvpActivity<IFenLeiActivityView, FenLeiActivityPresenter> implements IFenLeiActivityView {


    View orderPopupWindowView;
    RadioGroup rdgrpOrder;
    PopupWindow orderPopupWindow;

    List<Anime> animes;
    AnimeGrid_RcvAdapter mAdapter;

    String inFeilei;
    @BindView(R.id.rdbtn_fen_lei_all)
    RadioButton rdbtnFenLeiAll;
    @BindView(R.id.rdbtn_fen_lei_rexue)
    RadioButton rdbtnFenLeiRexue;
    @BindView(R.id.rdbtn_fen_lei_paomian)
    RadioButton rdbtnFenLeiPaomian;
    @BindView(R.id.rdbtn_fen_lei_gaoxiao)
    RadioButton rdbtnFenLeiGaoxiao;
    @BindView(R.id.rdbtn_fen_lei_qingchun)
    RadioButton rdbtnFenLeiQingchun;
    @BindView(R.id.rdbtn_fen_lei_jizhan)
    RadioButton rdbtnFenLeiJizhan;
    @BindView(R.id.rdbtn_fen_lei_youxi)
    RadioButton rdbtnFenLeiYouxi;
    @BindView(R.id.rdbtn_fen_lei_zhiyu)
    RadioButton rdbtnFenLeiZhiyu;
    @BindView(R.id.rdbtn_fen_lei_luoli)
    RadioButton rdbtnFenLeiLuoli;
    @BindView(R.id.rdbtn_fen_lei_lizhi)
    RadioButton rdbtnFenLeiLizhi;
    @BindView(R.id.rdbtn_fen_lei_lianai)
    RadioButton rdbtnFenLeiLianai;
    @BindView(R.id.rdbtn_fen_lei_yinv)
    RadioButton rdbtnFenLeiYinv;
    @BindView(R.id.rdbtn_fen_lei_hougong)
    RadioButton rdbtnFenLeiHougong;
    @BindView(R.id.rdbtn_fen_lei_maoxian)
    RadioButton rdbtnFenLeiMaoxian;
    @BindView(R.id.rdbtn_fen_lei_qinggai)
    RadioButton rdbtnFenLeiQinggai;
    @BindView(R.id.rdbtn_fen_lei_roufan)
    RadioButton rdbtnFenLeiRoufan;
    @BindView(R.id.rdgrp_fen_lei)
    RadioGroup rdgrpFenLei;
    @BindView(R.id.rdbtn_time_all)
    RadioButton rdbtnTimeAll;
    @BindView(R.id.rdbtn_time_2017)
    RadioButton rdbtnTime2017;
    @BindView(R.id.rdbtn_time_2016)
    RadioButton rdbtnTime2016;
    @BindView(R.id.rdbtn_time_2015)
    RadioButton rdbtnTime2015;
    @BindView(R.id.rdbtn_time_2014)
    RadioButton rdbtnTime2014;
    @BindView(R.id.rdbtn_time_2013)
    RadioButton rdbtnTime2013;
    @BindView(R.id.rdbtn_time_2012)
    RadioButton rdbtnTime2012;
    @BindView(R.id.rdbtn_time_2011)
    RadioButton rdbtnTime2011;
    @BindView(R.id.rdbtn_time_2010)
    RadioButton rdbtnTime2010;
    @BindView(R.id.rdbtn_time_early)
    RadioButton rdbtnTimeEarly;
    @BindView(R.id.rdgrp_time)
    RadioGroup rdgrpTime;
    @BindView(R.id.rdbtn_area_all)
    RadioButton rdbtnAreaAll;
    @BindView(R.id.rdbtn_area_japan)
    RadioButton rdbtnAreaJapan;
    @BindView(R.id.rdbtn_area_west)
    RadioButton rdbtnAreaWest;
    @BindView(R.id.rdbtn_area_china)
    RadioButton rdbtnAreaChina;
    @BindView(R.id.rdgrp_area)
    RadioGroup rdgrpArea;
    @BindView(R.id.rdbtn_state_all)
    RadioButton rdbtnStateAll;
    @BindView(R.id.rdbtn_state_end)
    RadioButton rdbtnStateEnd;
    @BindView(R.id.rdbtn_state_showing)
    RadioButton rdbtnStateShowing;
    @BindView(R.id.rdbtn_state_future)
    RadioButton rdbtnStateFuture;
    @BindView(R.id.rdgrp_state)
    RadioGroup rdgrpState;
    @BindView(R.id.part_fen_lei_choose)
    LinearLayout partFenLeiChoose;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.tv_pai_xu)
    TextView tvPaiXu;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.rcv_results)
    AutoLoadMoreWithLayoutManagerSwitchRecyclerView rcvResults;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fenlei);
        ButterKnife.bind(this);

        initVars();

        initView();

        loadData();
    }


    @Override
    public FenLeiActivityPresenter creatPresenter() {
        return new FenLeiActivityPresenter();
    }

    @Override
    public void initView() {
        //初始化popwindow内容
        orderPopupWindowView = LayoutInflater.from(this).inflate(R.layout.popupwindow_order, null);
        rdgrpOrder = (RadioGroup) orderPopupWindowView.findViewById(R.id.rdgrp_order);

        //设置默认选中
        rdgrpTime.check(R.id.rdbtn_time_all);
        rdgrpArea.check(R.id.rdbtn_area_all);
        rdgrpState.check(R.id.rdbtn_state_all);
        rdgrpOrder.check(R.id.rdbtn_order_moren);

        if (inFeilei == null || "".equals(inFeilei)) {
            rdgrpFenLei.check(R.id.rdbtn_fen_lei_all);
        } else {
            int fenleiChildCount = rdgrpFenLei.getChildCount();
            for (int i = 0; i < fenleiChildCount; i++) {
                RadioButton fenleiBtn = (RadioButton) rdgrpFenLei.getChildAt(i);
                //Log.e("---------",fenleiBtn.getText().toString());
                if (inFeilei.equals(fenleiBtn.getText())) {
                    fenleiBtn.setChecked(true);
                }
            }
        }


        //选择监听
        rdgrpArea.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                //mPresenter.getAnimesOfFenlei(getFenLeiChoose(),getTimeChoose(),getAreaChoose());
            }
        });
        rdgrpFenLei.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

            }
        });
        rdgrpTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

            }
        });
        rdgrpOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

            }
        });


        mAdapter = new AnimeGrid_RcvAdapter(animes,AnimeGrid_RcvAdapter.RCV_MODE_GRID,this);
        mAdapter.setOnItemClickListener(new AnimeGrid_RcvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view) {
                Anime anime = (Anime) view.getTag();
                String animeId = anime.getObjectId();
                //goto AnimeActivity
            }
        });
        rcvResults.setOnSwitchLayoutManagerListener(new AutoLoadMoreWithLayoutManagerSwitchRecyclerView.OnSwitchLayoutManagerListener() {
            @Override
            public void onSwitchLayout(RecyclerView.LayoutManager layoutManager) {

            }
        });



        tvPaiXu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrderPopWindow(tvPaiXu);
            }
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initVars() {
        //inFeilei = getIntent().getStringExtra("feileiName");
        inFeilei = "乙女";

        animes = new ArrayList<>();

    }


    @Override
    public void loadResultsToRcv(List<Anime> animes) {

    }

    @Override
    public String getAreaChoose() {
        int areaChooseId = rdgrpArea.getCheckedRadioButtonId();
        String areaChoose;
        switch (areaChooseId) {
            case R.id.rdbtn_area_japan:
                areaChoose = "日本";
                break;
            case R.id.rdbtn_area_west:
                areaChoose = "中国";
                break;
            case R.id.rdbtn_area_china:
                areaChoose = "欧美";
                break;
            default:
                areaChoose = "";
        }
        return areaChoose;
    }

    @Override
    public String getFenLeiChoose() {
        int fenleiChooseId = rdgrpFenLei.getCheckedRadioButtonId();
        String fenleiChoose;
        switch (fenleiChooseId) {
            case R.id.rdbtn_fen_lei_gaoxiao:
                fenleiChoose = "搞笑";
                break;
            case R.id.rdbtn_fen_lei_hougong:
                fenleiChoose = "后宫";
                break;
            case R.id.rdbtn_fen_lei_jizhan:
                fenleiChoose = "机战";
                break;
            case R.id.rdbtn_fen_lei_lianai:
                fenleiChoose = "恋爱";
                break;
            case R.id.rdbtn_fen_lei_lizhi:
                fenleiChoose = "励志";
                break;
            case R.id.rdbtn_fen_lei_luoli:
                fenleiChoose = "萝莉";
                break;
            case R.id.rdbtn_fen_lei_maoxian:
                fenleiChoose = "冒险";
                break;
            case R.id.rdbtn_fen_lei_paomian:
                fenleiChoose = "泡面";
                break;
            case R.id.rdbtn_fen_lei_qingchun:
                fenleiChoose = "青春";
                break;
            case R.id.rdbtn_fen_lei_qinggai:
                fenleiChoose = "轻改";
                break;
            case R.id.rdbtn_fen_lei_rexue:
                fenleiChoose = "热血";
                break;
            case R.id.rdbtn_fen_lei_roufan:
                fenleiChoose = "肉番";
                break;
            case R.id.rdbtn_fen_lei_yinv:
                fenleiChoose = "乙女";
                break;
            case R.id.rdbtn_fen_lei_youxi:
                fenleiChoose = "游戏";
                break;
            case R.id.rdbtn_fen_lei_zhiyu:
                fenleiChoose = "治愈";
                break;
            default:
                fenleiChoose = "";

        }
        return fenleiChoose;
    }

    @Override
    public int getTimeChoose() {
        int timeChooseId = rdgrpTime.getCheckedRadioButtonId();
        int timeChoose;
        switch (timeChooseId) {
            case R.id.rdbtn_time_early:
                timeChoose = -1;
                break;
            case R.id.rdbtn_time_2010:
                timeChoose = 201204;
                break;
            case R.id.rdbtn_time_2011:
                timeChoose = 201207;
                break;
            case R.id.rdbtn_time_2012:
                timeChoose = 201210;
                break;
            case R.id.rdbtn_time_2013:
                timeChoose = 201301;
                break;
            case R.id.rdbtn_time_2014:
                timeChoose = 201304;
                break;
            case R.id.rdbtn_time_2015:
                timeChoose = 201307;
                break;
            case R.id.rdbtn_time_2016:
                timeChoose = 201210;
                break;
            case R.id.rdbtn_time_2017:
                timeChoose = 201401;
                break;
            default:
                timeChoose = 0;
        }
        return timeChoose;
    }

    @Override
    public String getOrder() {
        int orderChooseId = rdgrpOrder.getCheckedRadioButtonId();
        String orderChoose;
        switch (orderChooseId) {
            case R.id.rdbtn_order_time:
                orderChoose = "showTime";
                break;
            case R.id.rdbtn_order_zhuifan:
                orderChoose = "followCount";
                break;
            case R.id.rdbtn_order_name:
                orderChoose = "animeName";
                break;
            default:
                orderChoose = "";
        }
        return orderChoose;
    }

    @Override
    public int getStateChoose() {
        int stateChooseId = rdgrpState.getCheckedRadioButtonId();
        int stateChoose;
        switch (stateChooseId) {
            case R.id.rdbtn_state_end:
                stateChoose = 0;
                break;
            case R.id.rdbtn_state_showing:
                stateChoose = 1;
                break;
            case R.id.rdbtn_state_future:
                stateChoose = 2;
                break;
            default:
                stateChoose = -1;
        }
        return stateChoose;
    }

    @Override
    public void notifyAutoLoadMoreComplete(boolean isError, String errorMsg) {

    }

    @Override
    public void showOrderPopWindow(View view) {
        orderPopupWindow = new PopupWindow(orderPopupWindowView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        ColorDrawable dw = new ColorDrawable();
        orderPopupWindow.setBackgroundDrawable(dw);
        orderPopupWindow.showAsDropDown(tvPaiXu);
    }


}
