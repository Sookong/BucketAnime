package com.tanwuyu.ivrtym.seibertronrecyclerview;

/**
 * Created by ivrty on 2017/5/2.
 */

public interface SingleTypeSupport<T> {
    void bindDataToHolder(SeibertronViewHolder viewHolder,T data);
    int getHolderLayoutResourceId();
}
