package com.tanwuyu.ivrtym.bucketanime.model.httpEntity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ivrty on 2017/3/28.
 */

public class Fenlei implements Parcelable {

    /**
     * createdAt : 2017-03-27 01:46:08
     * fenleiName : 科幻
     * imgUrl : http://baidu.adfad.13234.jpg
     * objectId : D6xV222A
     * updatedAt : 2017-03-28 18:44:28
     */

    private String createdAt;
    private String fenleiName;
    private String imgUrl;
    private String objectId;
    private String updatedAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getFenleiName() {
        return fenleiName;
    }

    public void setFenleiName(String fenleiName) {
        this.fenleiName = fenleiName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
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
        dest.writeString(this.fenleiName);
        dest.writeString(this.imgUrl);
        dest.writeString(this.objectId);
        dest.writeString(this.updatedAt);
    }

    public Fenlei() {
    }

    protected Fenlei(Parcel in) {
        this.createdAt = in.readString();
        this.fenleiName = in.readString();
        this.imgUrl = in.readString();
        this.objectId = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<Fenlei> CREATOR = new Parcelable.Creator<Fenlei>() {
        @Override
        public Fenlei createFromParcel(Parcel source) {
            return new Fenlei(source);
        }

        @Override
        public Fenlei[] newArray(int size) {
            return new Fenlei[size];
        }
    };
}
