package com.tanwuyu.ivrtym.bucketanime;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tanwuyu.ivrtym.bucketanime.view.fragment.FenLeiFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        FragmentManager fm = getSupportFragmentManager();
        //fm.beginTransaction().replace(R.id.container_main,new FenLeiFragment()).commit();

    }
}
