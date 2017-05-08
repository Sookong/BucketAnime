package com.tanwuyu.ivrtym.rollpageview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivrtym on 2017/3/2.
 */

public class RollPageView extends FrameLayout implements ViewPager.OnPageChangeListener, View.OnTouchListener {
    Context context;
    ViewPager viewPager;
    LinearLayout rollIndicatorContainer;
    View indicatorView;
    List<Page> pages;
    TextView tvPageDesc;
    int lastPosition = 0;
    boolean isTouching = false;
    boolean isVisible = false;
    Handler mHander;

    public RollPageView(Context context) {
        super(context);
        this.context = context;
    }

    public RollPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public RollPageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void initRollPageView(List<Page> pages) {
        this.pages = pages;

        List<View> views = new ArrayList<>();
        for (Page page : pages) {
            //init viewpager date
            views.add(page.getPage());
            //init indicator
            indicatorView = new View(context);
            indicatorView.setBackgroundResource(R.drawable.roll_indicator_background);
            indicatorView.setEnabled(false);
            LinearLayout.LayoutParams indicatorParam = new LinearLayout.LayoutParams(20, 20);
            indicatorParam.leftMargin = 10;
            rollIndicatorContainer.addView(indicatorView, indicatorParam);
        }


        tvPageDesc.setText(pages.get(0).getPageDesc());

        //init viewpager
        viewPager.setAdapter(new RollPageAdapter(views));
        viewPager.setCurrentItem(this.pages.size() * 10000);

        rollIndicatorContainer.getChildAt(0).setEnabled(true);

        isTouching = false;

        mHander = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (!isTouching)
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        };

        startAtuoRound();

    }

    public void startAtuoRound() {
        isVisible = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isVisible) {
                    try {
                        Thread.sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mHander.sendEmptyMessage(0);
                }
            }
        }).start();

    }

    public void stopAtuoRound() {
        isVisible = false;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(context).inflate(R.layout.roll_photo_view, null);
        viewPager = (ViewPager) view.findViewById(R.id.roll_page);
        rollIndicatorContainer = (LinearLayout) view.findViewById(R.id.roll_indicator);
        tvPageDesc = (TextView) view.findViewById(R.id.tv_page_desc);
        viewPager.addOnPageChangeListener(this);
        viewPager.setOnTouchListener(this);
        this.addView(view);
    }


    //页面切换监听
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tvPageDesc.setText(pages.get(position % pages.size()).getPageDesc());
        rollIndicatorContainer.getChildAt(position % pages.size()).setEnabled(true);
        rollIndicatorContainer.getChildAt(lastPosition).setEnabled(false);
        lastPosition = position % pages.size();

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //触摸时不自动轮播
        switch (event.getAction()) {

            case MotionEvent.ACTION_UP:
                isTouching = false;
                Log.e("Touch","false");
                break;
            default:
                isTouching = true;
                Log.e("Touch","true");
                break;
        }
        //不消化,否则onpagechangelistener失效
        return false;
    }


    public static class Page {
        View page;
        String pageDesc;

        public View getPage() {
            return page;
        }

        public void setPage(View page) {
            this.page = page;
        }

        public String getPageDesc() {
            return pageDesc;
        }

        public void setPageDesc(String pageDesc) {
            this.pageDesc = pageDesc;
        }
    }


}
