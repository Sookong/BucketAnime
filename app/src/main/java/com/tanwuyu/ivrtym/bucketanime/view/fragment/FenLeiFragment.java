package com.tanwuyu.ivrtym.bucketanime.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.adapter.FenLeiRcvAdapter;
import com.tanwuyu.ivrtym.bucketanime.base.BaseMvpFragment;

import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Fenlei;
import com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp.FenLeiPresenter;
import com.tanwuyu.ivrtym.bucketanime.view.IFenLeiView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ivrtym on 2017/3/8.
 */

public class FenLeiFragment extends BaseMvpFragment<IFenLeiView, FenLeiPresenter> implements IFenLeiView {
    FenLeiRcvAdapter fenLeiAdapter;
    List<Fenlei> fenleiList;

    @BindView(R.id.rcv_fenlei)
    RecyclerView rcvFenlei;

    @Override
    public FenLeiPresenter createPresenter() {
        return new FenLeiPresenter();
    }

    @Override
    public void initView() {

    }

    @Override
    public void loadDatas() {

    }

    @Override
    public void initVars() {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    //@Override
    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fenlei, container, false);
        ButterKnife.bind(this, rootView);

        Fenlei fenlei;
        fenleiList = new ArrayList<Fenlei>();


        for (int i = 0; i < 20; i++) {
            fenlei = new Fenlei();
            fenlei.setFenleiName(String.valueOf(i) + "个");
            fenlei.setImgUrl("http://www.dilidili.wang/uploads/allimg/170301/1_1129051531.jpg");
            fenleiList.add(fenlei);
        }


        rcvFenlei.setLayoutManager(new GridLayoutManager(mContext, 3));
        fenLeiAdapter = new FenLeiRcvAdapter(fenleiList, mContext);
        fenLeiAdapter.setOnItemClickListener(new FenLeiRcvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, fenleiList.get(position).getFenleiName(), Toast.LENGTH_SHORT).show();
            }
        });
        rcvFenlei.setAdapter(fenLeiAdapter);
        Log.e("State",rcvFenlei.getAdapter().getClass().getName());
        Log.e("State","initView");
        Log.e("State",String.valueOf(fenleiList.size()));

        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e("State","setUserVisibleHint = "+isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) mPresenter.loadData();

    }


    @Override
    public void loadDataToRecycleView(List<Fenlei> fenleis) {
        //fenleiList.removeAll();
        //Log.e("State cleared",String.valueOf(fenleiList.size()));

        fenleiList.addAll(fenleis);
        //fenleiList = fenleis;
        //fenLeiAdapter.notifyDataSetChanged();
        rcvFenlei.getAdapter().notifyDataSetChanged();
        Log.e("State","loadDataToRecycleView");
        Log.e("State",String.valueOf(fenleis.size()));
    }
}
