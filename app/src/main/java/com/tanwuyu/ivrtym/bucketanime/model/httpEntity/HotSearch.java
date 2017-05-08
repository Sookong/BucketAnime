package com.tanwuyu.ivrtym.bucketanime.model.httpEntity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ivrty on 2017/3/27.
 */

public class HotSearch implements Parcelable {
    /**
     * createdAt : 2017-03-20 17:19:30
     * objectId : k4tqDDDS
     * searchCount : 32
     * searchText : 人渣的本愿真人版
     * updatedAt : 2017-03-27 16:23:19
     */

    private String createdAt;
    private String objectId;
    private int searchCount;
    private String searchText;
    private String updatedAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.createdAt);
        dest.writeString(this.objectId);
        dest.writeInt(this.searchCount);
        dest.writeString(this.searchText);
        dest.writeString(this.updatedAt);
    }

    public HotSearch() {
    }

    protected HotSearch(Parcel in) {
        this.createdAt = in.readString();
        this.objectId = in.readString();
        this.searchCount = in.readInt();
        this.searchText = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<HotSearch> CREATOR = new Parcelable.Creator<HotSearch>() {
        @Override
        public HotSearch createFromParcel(Parcel source) {
            return new HotSearch(source);
        }

        @Override
        public HotSearch[] newArray(int size) {
            return new HotSearch[size];
        }
    };
}
