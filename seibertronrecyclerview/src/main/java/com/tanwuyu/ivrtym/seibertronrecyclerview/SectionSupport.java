package com.tanwuyu.ivrtym.seibertronrecyclerview;

import android.support.annotation.Nullable;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ivrty on 2017/5/2.
 */

public abstract class SectionSupport<T> {
    public static final int TYPE_SECTION_TITEL = 1;
    public static final int TYPE_SECTION_BOTTOM = 2;

    public static final String TITLE = "title";
    public static final String BOTTOM = "bottom";


    public abstract int getTitleHolderLayoutResourceId();

    public abstract int getBottomHolderLayoutResourceId();

    public abstract void bindTitleHolder(int titleHolderLayoutResourceId, SeibertronViewHolder viewHolder, int sectionTag);

    public abstract void bindBottomHolder(int bottomHolderLayoutResourceId, SeibertronViewHolder viewHolder, int sectionTag);


    /**
     * 根据数据源计算section,返回得出section结果集
     *
     * @param datas your datas for recyclerview
     * @return sectionInfos List calculated by your datas
     */
    public abstract List<SectionInfo> calculatSections(List<T> datas);


    /**
     * 计算出 分区标题位置集合sectionTitlePositions  底部位置集合sectionBottomPositions
     */
    public HashMap<String, HashMap<Integer, Integer>> getPositionsMapCarelessOut(List<T> datas, List<SectionInfo> sectionInfos) {

        HashMap<String, HashMap<Integer, Integer>> positionsMapCarelessOut = new HashMap<>();
        HashMap<Integer, Integer> sectionTitlePositions = new HashMap<>();
        HashMap<Integer, Integer> sectionBottomPositions = new HashMap<>();
        int sectionCursor = 0;                     // 游标

        for (int i = 0; i < sectionInfos.size(); i++) {
            int sectionStartPosition = 0;
            int sectionEndPosition = 0;
            SectionSupport.SectionInfo beforeSectionInfo;

            SectionSupport.SectionInfo sectionInfo = sectionInfos.get(i);

            //分区数据长度 = 数据结束位置 - 数据开始位置 + 1
            int sectionDataLength = sectionInfo.getDataEndIndex() - sectionInfo.getDataStartIndex() + 1;

            //如果是第一分区 : section起始位置 = 数据起始序号
            if (i == 0) {
                sectionStartPosition = sectionInfo.getDataStartIndex();
                sectionEndPosition = sectionInfo.isShowTitle()
                        ? (sectionInfo.isShowBottom() ? sectionStartPosition + sectionDataLength + 2 : sectionStartPosition + 1)
                        : (sectionInfo.isShowBottom() ? sectionStartPosition + sectionDataLength + 1 : sectionStartPosition);
                //判断标题存在,添加标题位置进标题集合
                if (sectionInfo.isShowTitle()) {
                    sectionTitlePositions.put(sectionStartPosition, sectionInfo.getSectionTag());
                }
                //判断底部存在,添加底部进底部集合
                if (sectionInfo.isShowBottom()) {
                    sectionBottomPositions.put(sectionEndPosition, sectionInfo.getSectionTag());
                }

                //更新游标位置
                sectionCursor = sectionEndPosition;

            }
            //非第一分区 : section起始位置 = 游标位置+断层长度 ;   section结束位置 = 起始位置 + 数据长度 + 是否有bottom+是否有title
            else {
                //计算当前分区与前一分区之间断层长度
                beforeSectionInfo = sectionInfos.get(i - 1);
                int faultageLength = sectionInfo.getDataStartIndex() - beforeSectionInfo.getDataEndIndex();
                //更新游标位置
                sectionCursor += faultageLength;

                sectionStartPosition = sectionCursor;
                sectionEndPosition = sectionInfo.isShowTitle()
                        ? (sectionInfo.isShowBottom() ? sectionStartPosition + sectionDataLength + 2 : sectionStartPosition + 1)
                        : (sectionInfo.isShowBottom() ? sectionStartPosition + sectionDataLength + 1 : sectionStartPosition);

                //判断标题存在,添加标题位置进标题集合
                if (sectionInfo.isShowTitle()) {
                    sectionTitlePositions.put(sectionStartPosition, sectionInfo.getSectionTag());
                }
                //判断底部存在,添加底部进底部集合
                if (sectionInfo.isShowBottom()) {
                    sectionBottomPositions.put(sectionEndPosition, sectionInfo.getSectionTag());
                }

                //更新游标位置
                sectionCursor = sectionEndPosition;
            }
        }

        positionsMapCarelessOut.put(TITLE, sectionTitlePositions);
        positionsMapCarelessOut.put(BOTTOM, sectionBottomPositions);

        return positionsMapCarelessOut;
    }

/*    public int getSectionTagByPositionCarelessOut(int positioinCarelessOut){

    }*/


    public static class SectionInfo {
        public SectionInfo(int sectionTag, int dataStartIndex, int dataEndIndex, boolean showTitle, boolean showBottom) {
            this.sectionTag = sectionTag;
            this.dataStartIndex = dataStartIndex;
            this.dataEndIndex = dataEndIndex;
            this.showTitle = showTitle;
            this.showBottom = showBottom;
        }

        int sectionTag;
        int dataStartIndex;
        int dataEndIndex;

        boolean showTitle = false;
        boolean showBottom = false;

        public boolean isShowTitle() {
            return showTitle;
        }

        public void setShowTitle(boolean showTitle) {
            this.showTitle = showTitle;
        }

        public boolean isShowBottom() {
            return showBottom;
        }

        public void setShowBottom(boolean showBottom) {
            this.showBottom = showBottom;
        }

        public int getDataStartIndex() {
            return dataStartIndex;
        }

        public void setDataStartIndex(int dataStartIndex) {
            this.dataStartIndex = dataStartIndex;
        }

        public int getDataEndIndex() {
            return dataEndIndex;
        }

        public void setDataEndIndex(int dataEndIndex) {
            this.dataEndIndex = dataEndIndex;
        }

        public int getSectionTag() {
            return sectionTag;
        }

        public void setSectionTag(int sectionTag) {
            this.sectionTag = sectionTag;
        }
    }
}
