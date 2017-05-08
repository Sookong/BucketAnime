package com.tanwuyu.ivrtym.bucketanime.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by ivrty on 2017/3/18.
 */

public class InsideScrollView extends ScrollView {
    private boolean isScrollable;

    public boolean isScrollable() {
        return isScrollable;
    }

    public void setScrollable(boolean scrollable) {
        isScrollable = scrollable;
    }

    public InsideScrollView(Context context) {
        super(context);
    }

    public InsideScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InsideScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        requestDisallowInterceptTouchEvent(isScrollable);
        return super.dispatchTouchEvent(ev);
    }
}
