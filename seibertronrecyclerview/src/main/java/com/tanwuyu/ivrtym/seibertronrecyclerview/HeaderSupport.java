package com.tanwuyu.ivrtym.seibertronrecyclerview;

/**
 * Created by ivrty on 2017/4/29.
 */

public abstract class HeaderSupport{
    int headerViewLayoutResourceId;

    public HeaderSupport(int headerViewLayoutResourceId) {
        this.headerViewLayoutResourceId = headerViewLayoutResourceId;
    }

    abstract void bindHeaderHolder(SeibertronViewHolder viewHolder);
    abstract int getHeaderViewLayoutResourceId();
}
