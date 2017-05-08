package com.tanwuyu.ivrtym.bucketanime.view.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.tanwuyu.ivrtym.bucketanime.R;
import com.tanwuyu.ivrtym.bucketanime.widget.BucketMediaController;
import com.tanwuyu.ivrtym.bucketanime.widget.MediaController;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ivrty on 2017/3/23.
 */

public class PlayActivity extends AppCompatActivity {
    public static final String TEST_URL = "http://pl-ali.youku.com/playlist/m3u8?vid=482011413&type=hd2&ts=1490437224&keyframe=0&sid=149043722400021c2ac90&token=9501&oip=1894636263&ctype=21&ev=1&did=0c260042e77487bb3c7be0e30f35a7454c677b53&ep=b%2FisLWJck2TmUCeWPJ3zS49iIRDFjQ0tDCPnlC%2FFimhP7T71N9e7R4J7T2dXnvTN";

    @BindView(R.id.plv_video_texture_view)
    PLVideoTextureView plvVideoTextureView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_layout)
    CollapsingToolbarLayout collapsingLayout;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.appbar_layout)
    AppBarLayout appbarLayout;
    @BindView(R.id.bt_test)
    Button button;
    @BindView(R.id.bkcontrol)
    BucketMediaController bucketMediaController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        initView();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    void initView(){
        initPlayer();
    }
    void initPlayer(){
        //BucketMediaController mediaController= (BucketMediaController) findViewById(R.id.simple_media_controller);
        //plvVideoTextureView.setVolume();
        //MediaController mediaController12 = new MediaController(this);
        bucketMediaController.setFullscreenState(true);

        plvVideoTextureView.setVideoURI(Uri.parse(TEST_URL));
        plvVideoTextureView.setMediaController(bucketMediaController);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plvVideoTextureView.start();
                Log.e("Clicked","--");
            }
        });

    }

    @Override
    public int getRequestedOrientation() {
        return super.getRequestedOrientation();
    }

}
