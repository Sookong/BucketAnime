package com.tanwuyu.ivrtym.seibertronrecyclerview;

/**
 * Created by ivrty on 2017/4/29.
 */

public interface MultiTypeSupport<T> {
    //通过item类型返回对应holder的布局资源id
    int getHolderLayoutResourceIdByItemType(int itemType);
    //根据position或者itemData返回对应的item类型
    int getItemType(int position,int dataIndex,T data);
    //绑定数据到ViewHolder
    void bindDataToHolder(int holderResourceId,SeibertronViewHolder viewHolder,T data);
}
