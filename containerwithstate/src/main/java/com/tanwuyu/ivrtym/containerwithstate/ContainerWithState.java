package com.tanwuyu.ivrtym.containerwithstate;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.transition.Visibility;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ivrty on 2017/4/14.
 */

public class ContainerWithState extends FrameLayout {
    View errorView;
    Context mContext;

    ImageView ivState;
    TextView tvState;
    Button btnReTty;

    OnRetryButtonClickListener mOnRetryButtonClickListener;

    interface OnRetryButtonClickListener {
        void onRetryButtonClick(View view);
    }

    public void setOnRetryButtonClickListener(OnRetryButtonClickListener listener){
        mOnRetryButtonClickListener = listener;
    }

    public ContainerWithState(@NonNull Context context) {
        super(context);
        mContext = context;
    }

    public ContainerWithState(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public ContainerWithState(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        errorView = LayoutInflater.from(mContext).inflate(R.layout.error_view,null);
        ivState = (ImageView) errorView.findViewById(R.id.iv_state);
        tvState = (TextView) errorView.findViewById(R.id.tv_state);
        btnReTty = (Button) errorView.findViewById(R.id.btn_retry);

        btnReTty.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRetryButtonClickListener != null)
                    mOnRetryButtonClickListener.onRetryButtonClick(v);
            }
        });


        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        errorView.setLayoutParams(layoutParams);
        errorView.setTag("errorview");

        errorView.setVisibility(GONE);
        addView(errorView);
    }


    void setError(String errorInfo) {
        errorView.setVisibility(VISIBLE);
        tvState.setText(errorInfo);

        btnReTty.setVisibility(VISIBLE);
        btnReTty.setText("重试");

        setChildViewVisiable(VISIBLE);
    }

    void setLoading() {
        errorView.setVisibility(VISIBLE);

        tvState.setText("努力加载中...");

        btnReTty.setVisibility(GONE);

        setChildViewVisiable(GONE);

    }

    void setEmpty() {

        setError("啥都没有哦~!");

        setChildViewVisiable(GONE);
    }

    void setSucessful() {
        errorView.setVisibility(GONE);
        setChildViewVisiable(VISIBLE);
    }

    void setChildViewVisiable(int visibility) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getTag() == null || !"errorview".equals(childView.getTag()))
                childView.setVisibility(visibility);
        }

    }

}
