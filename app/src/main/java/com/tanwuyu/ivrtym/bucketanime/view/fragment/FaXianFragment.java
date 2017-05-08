package com.tanwuyu.ivrtym.bucketanime.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tanwuyu.ivrtym.bucketanime.R;
/**
 * Created by ivrtym on 2017/3/17.
 */

public class FaXianFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_faxian,container,false);
        return view;
    }
}
