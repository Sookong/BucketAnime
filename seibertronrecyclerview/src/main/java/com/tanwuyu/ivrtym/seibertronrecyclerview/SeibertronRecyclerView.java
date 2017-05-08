package com.tanwuyu.ivrtym.seibertronrecyclerview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by ivrty on 2017/4/29.
 */

public class SeibertronRecyclerView extends RecyclerView {
    private boolean isLoading = false;


    static class MHandle extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    public SeibertronRecyclerView(Context context) {
        super(context);
    }

    public SeibertronRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SeibertronRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        //way 1
        if (!canScrollVertically(-1) && !isLoading) {
            loadMore();
            isLoading = true;
        }
        //way 2

    }


    public void loadMore() {
        Adapter adapter = getAdapter();
        if (adapter instanceof SeibertronAdapter) {
            ((SeibertronAdapter) adapter).loadMore();
        }
    }

    public void notifyLoadAllComplete() {
        isLoading=false;
        Adapter adapter = getAdapter();
        if (adapter instanceof SeibertronAdapter) {
            ((SeibertronAdapter) adapter).notifyLoadAllComplete();
        }
    }

    private void notifyLoadSuccess() {
        isLoading=false;
        Adapter adapter = getAdapter();
        if (adapter instanceof SeibertronAdapter) {
            ((SeibertronAdapter) adapter).notifyLoadSuccess();
        }
    }

    public void notifyLoadError() {
        isLoading=false;
        Adapter adapter = getAdapter();
        if (adapter instanceof SeibertronAdapter) {
            ((SeibertronAdapter) adapter).notifyLoadError();
        }
    }
}
