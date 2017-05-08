package com.tanwuyu.ivrtym.bucketanime.presenter.presenterimp;

import com.tanwuyu.ivrtym.bucketanime.base.BasePresenter;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.Fenlei;
import com.tanwuyu.ivrtym.bucketanime.presenter.IFenLeiPresenter;
import com.tanwuyu.ivrtym.bucketanime.view.IFenLeiView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivrtym on 2017/3/8.
 */

public class FenLeiPresenter extends BasePresenter<IFenLeiView> implements IFenLeiPresenter {
    List<Fenlei> fenleis;

    @Override
    public void loadData() {
        fenleis = new ArrayList<Fenlei>();

        Fenlei fenlei;
        for (int i = 0; i < 20; i++) {
            fenlei = new Fenlei();
            fenlei.setFenleiName(String.valueOf(i) + "hahahaha");
            fenlei.setImgUrl("http://www.dilidili.wang/uploads/allimg/170301/1_1129051531.jpg");
            fenleis.add(fenlei);
        }
        mView.loadDataToRecycleView(fenleis);
    }
}
