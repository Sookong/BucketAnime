package com.tanwuyu.ivrtym.seibertronrecyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ivrty on 2017/4/29.
 */

public class SeibertronAdapter<T> extends RecyclerView.Adapter<SeibertronViewHolder> {
    List<T> mDatas;
    Context mContext;

    static final int TYPE_HEADER = 77;
    static final int TYPE_FOOTER = 79;
    static final int TYPE_SINGLE_TYPE_ITEM = 73;

    /*static final int TYPE_SECTION_TITLE = 61;
    static final int TYPE_SECTION_BOTTOM = 67;*/

    AutoLoadSupport mAutoLoadSupport;
    HeaderSupport mHeaderSupport;
    SingleTypeSupport mSingleTypeSupport;
    MultiTypeSupport mMultiTypeSupport;
    SectionSupport mSectionSupport;


    HashMap<Integer, Integer> sectionTitlePositionsWithTag;
    HashMap<Integer, Integer> sectionBottomPositionsWithTag;
    List<SectionSupport.SectionInfo> sectionInfos;


    public SeibertronAdapter(List<T> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    public void addAutoLoadSupport(AutoLoadSupport autoLoadSupport) {
        mAutoLoadSupport = autoLoadSupport;
    }

    public void addHeaderSupport(HeaderSupport headerSupport) {
        mHeaderSupport = headerSupport;
    }

    public void addMultiTypeSupport(MultiTypeSupport multiTypeSupport) throws SeibertronSupportException {
        if (mSingleTypeSupport != null) {
            throw new SeibertronSupportException("已经存在SingleTypeSupport :不能同时设置两种TypeSupport \n SingleTypeSupport already exist : you cant add two TypeSupport to one Adapter");
        }
        mMultiTypeSupport = multiTypeSupport;
    }

    public void addSingleTypeSupport(SingleTypeSupport singleTypeSupport) throws SeibertronSupportException {
        if (mSingleTypeSupport != null) {
            throw new SeibertronSupportException("已经存在MultiTypeSupport : 不能同时设置两种TypeSupport\n MultiTypeSupport already exist : you cant add two TypeSupport to one Adapter");
        }
        mSingleTypeSupport = singleTypeSupport;
    }

    public void addSectionSupport(SectionSupport sectionSupport) {
        mSectionSupport = sectionSupport;
    }


    @Override
    public int getItemCount() {
        int itemCount = -1;

        //是否添加了分区支持
        if (mSectionSupport != null) {

            //遍历分区列表,计算分区title+bottom 总数
            int sectionItemCount = 0;
            List<SectionSupport.SectionInfo> sectionInfos = mSectionSupport.calculatSections(mDatas);
            for (SectionSupport.SectionInfo sectionInfo : sectionInfos) {
                if (sectionInfo.isShowTitle()) {
                    sectionItemCount += 1;
                }
                if (sectionInfo.isShowBottom()) {
                    sectionItemCount += 1;
                }
            }
            //item总数等于数据长度+分区title+bottom总数
            itemCount = mDatas.size() + sectionItemCount;

        } else {
            itemCount = mDatas.size();
        }

        //判断头尾是否存在,相应加上上面计算出的总数
        if (mHeaderSupport != null && mAutoLoadSupport != null) {           //同不为空
            itemCount += 2;
        } else if (!(mHeaderSupport == null && mAutoLoadSupport == null)) {   //其一为空
            itemCount += 1;
        }
        return itemCount;
    }


    @Override
    public int getItemViewType(int position) {

        //头部存在,且position==0,返回头部类型
        if (mHeaderSupport != null && position == 0) {
            return TYPE_HEADER;
        }

        //底部存在,且position==尾部,返回底部类型
        if (mAutoLoadSupport != null && position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }

        //是否添加了分区支持,根据位置返回分区类型
        if (mSectionSupport != null) {
            //计算出所有分区的titleposition与bottomposition集
            sectionInfos = mSectionSupport.calculatSections(mDatas);
            HashMap<String, HashMap<Integer, Integer>> positionsMapCarelessOut = mSectionSupport.getPositionsMapCarelessOut(mDatas, sectionInfos);

            sectionTitlePositionsWithTag = positionsMapCarelessOut.get(SectionSupport.TITLE);
            sectionBottomPositionsWithTag = positionsMapCarelessOut.get(SectionSupport.BOTTOM);

            if (mHeaderSupport != null) {
                HashMap<Integer, Integer> sectionTitlePositionsWithTag1 = new HashMap<>();
                HashMap<Integer, Integer> sectionBottomPositionsWithTag1 = new HashMap<>();
                //如果头部存在,所有分区position后移一位
                for (Map.Entry<Integer, Integer> entry : sectionTitlePositionsWithTag.entrySet()) {
                    sectionTitlePositionsWithTag1.put(entry.getKey() + 1, entry.getValue());
                }
                for (Map.Entry<Integer, Integer> entry : sectionBottomPositionsWithTag.entrySet()) {
                    sectionBottomPositionsWithTag1.put(entry.getKey() + 1, entry.getValue());
                }

                sectionTitlePositionsWithTag = sectionTitlePositionsWithTag1;
                sectionBottomPositionsWithTag = sectionBottomPositionsWithTag1;
            }

            //判断position是否存在于上面计算出的SectionTitle\Bottom Position集中
            if (sectionTitlePositionsWithTag.containsKey(position)) {
                return SectionSupport.TYPE_SECTION_TITEL;
            }
            if (sectionBottomPositionsWithTag.containsKey(position)) {
                return SectionSupport.TYPE_SECTION_TITEL;
            }
        }

        //多布局支持,返回多布局对应类型
        if (mMultiTypeSupport != null) {
            int dataIndex = getDataIndexByPosition(position);
            return mMultiTypeSupport.getItemType(position, dataIndex, mDatas.get(dataIndex));
        }

        //默认单布局支持,返回单布局类型
        return TYPE_SINGLE_TYPE_ITEM;

    }

    @Override
    public SeibertronViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //头部类型
        if (viewType == TYPE_HEADER) {
            return SeibertronViewHolder.creatViewHolder(mHeaderSupport.getHeaderViewLayoutResourceId(), parent, mContext);
        }
        //底部类型
        if (viewType == TYPE_FOOTER) {
            return SeibertronViewHolder.creatViewHolder(mAutoLoadSupport.getBottomViewLayoutResourceId(), parent, mContext);
        }

        //单分区Title类型
        if (viewType == SectionSupport.TYPE_SECTION_TITEL) {
            return SeibertronViewHolder.creatViewHolder(mSectionSupport.getTitleHolderLayoutResourceId(), parent, mContext);
        }
        //单分区Bottom类型
        if (viewType == SectionSupport.TYPE_SECTION_BOTTOM) {
            return SeibertronViewHolder.creatViewHolder(mSectionSupport.getBottomHolderLayoutResourceId(), parent, mContext);
        }

        //单布局item类型
        if (viewType == TYPE_SINGLE_TYPE_ITEM) {
            return SeibertronViewHolder.creatViewHolder(mSingleTypeSupport.getHolderLayoutResourceId(), parent, mContext);
        }
        //多布局item类型
        return SeibertronViewHolder.creatViewHolder(mMultiTypeSupport.getHolderLayoutResourceIdByItemType(viewType), parent, mContext);

    }

    @Override
    public void onBindViewHolder(SeibertronViewHolder holder, int position) {
        int viewHolderLayoutRecourceId = holder.getLayoutRecourceId();

        if (mHeaderSupport != null && viewHolderLayoutRecourceId == mHeaderSupport.getHeaderViewLayoutResourceId()) {
            //绑定头布局
            mHeaderSupport.bindHeaderHolder(holder);
        }
        else if (mAutoLoadSupport != null && viewHolderLayoutRecourceId == mAutoLoadSupport.getBottomViewLayoutResourceId()) {
            //绑定底部布局
            mAutoLoadSupport.bindBottomHolder(holder);
        }
        else if (mSingleTypeSupport != null && viewHolderLayoutRecourceId == mSingleTypeSupport.getHolderLayoutResourceId()) {
            //绑定单itemType布局
            mSingleTypeSupport.bindDataToHolder(holder, mDatas.get(getDataIndexByPosition(position)));
        }
        else if (mSectionSupport != null) {
            //绑定单Section布局
            if (viewHolderLayoutRecourceId == mSectionSupport.getTitleHolderLayoutResourceId()) {
                mSectionSupport.bindTitleHolder(viewHolderLayoutRecourceId, holder, sectionTitlePositionsWithTag.get(position));
            }else if (viewHolderLayoutRecourceId == mSectionSupport.getBottomHolderLayoutResourceId()) {
                mSectionSupport.bindBottomHolder(viewHolderLayoutRecourceId, holder, sectionBottomPositionsWithTag.get(position));
            }
        } else {
            //绑定多itemType布局
            mMultiTypeSupport.bindDataToHolder(holder.getLayoutRecourceId(), holder, null);
        }

    }


    public int getDataIndexByPosition(int position) {


        if (mSectionSupport != null) {

        }


        if (mHeaderSupport != null) {
            return position - 1;
        }
        return position;
    }


    /**
     * AutoLoadPart
     */

    public void loadMore() {
        if (mAutoLoadSupport != null) {
            mAutoLoadSupport.onLoadMore(mDatas.size());
        }
    }

    public void notifyLoadAllComplete() {
        if (mAutoLoadSupport != null) {
            mAutoLoadSupport.onLoadAllComplete();
        }
    }

    public void notifyLoadError() {
        if (mAutoLoadSupport != null) {
            mAutoLoadSupport.onLoadError();
        }
    }

    public void notifyLoadSuccess() {
        if (mAutoLoadSupport != null) {
            mAutoLoadSupport.onLoadSuccess();
        }
    }


}
