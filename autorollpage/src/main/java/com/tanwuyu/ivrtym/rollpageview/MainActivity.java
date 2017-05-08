package com.tanwuyu.ivrtym.rollpageview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<RollPageView.Page> pageList;
    RollPageView rollPageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollPageView = (RollPageView) findViewById(R.id.roll_page_view);
        RollPageView.Page page;
        ImageView imageView;
        pageList = new ArrayList<RollPageView.Page>();
        for (int i = 0; i < 11; i++) {
            page = new RollPageView.Page();
            imageView = new ImageView(this);

            if (i % 3 == 0)
                imageView.setBackgroundColor(Color.YELLOW);
            if (i % 5 == 0)
                imageView.setBackgroundColor(Color.GRAY);
            if (i % 7 == 0)
                imageView.setBackgroundColor(Color.RED);
            if (i % 2 == 0)
                imageView.setBackgroundColor(Color.GREEN);

            page.setPage(imageView);
            page.setPageDesc("这是第"+String.valueOf(i)+"个简单粗暴的轮播页面");
            pageList.add(page);
        }

        rollPageView.initRollPageView(pageList);
    }
}
