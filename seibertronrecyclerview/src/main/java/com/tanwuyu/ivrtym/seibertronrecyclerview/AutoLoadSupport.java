package com.tanwuyu.ivrtym.seibertronrecyclerview;

import android.support.annotation.Nullable;

/**
 * Created by ivrty on 2017/4/29.
 */

public abstract class AutoLoadSupport{
    public AutoLoadSupport() {
    }

    public abstract int getBottomViewLayoutResourceId();
    public abstract void bindBottomHolder(SeibertronViewHolder bottomHolder);
    public abstract void onLoadMore(int totalDatasSize);
    public abstract void onLoadAllComplete();
    public abstract void onLoadError();
    public abstract void onLoadSuccess();
}
