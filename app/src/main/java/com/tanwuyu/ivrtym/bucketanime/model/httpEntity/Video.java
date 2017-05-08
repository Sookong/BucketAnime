package com.tanwuyu.ivrtym.bucketanime.model.httpEntity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ivrty on 2017/3/27.
 */

public class Video implements Parcelable {

    /**
     * acUrl : http://youku.com
     * biliUrl : http://youku.com
     * createdAt : 2017-03-29 16:51:52
     * diliUrl : http://www.dilidili.wang/watch3/55757/
     * imgUrl : http://youku.com
     * leUrl : http://youku.com
     * number : 12
     * objectId : 6P8Bhhhr
     * playCount : 999
     * superAnime : {"__type":"Object","animeArea":"日本","animeIntroduce":"这个世界被划分为13个区，负责统一的唯一组织是ACCA。在那个组织中，前往各地区视察的\u201c监察课\u201d所属的课长，别名\u201c弄得到香烟的吉恩\u201d，是没有哪个组织\u201c不吃他那套的男人\u201d。然而视察后不久的他，却卷入了撼动世界的阴魔之中。","animeName":"ACCA13区监察课","animeState":1,"className":"Anime","createdAt":"2017-03-28 19:05:01","followCount":999,"imgUrl":"http://i0.hdslb.com/bfs/bangumi/0aad6593530a088c531272b9c9c0a3b81db30317.jpg_225x300.jpg","objectId":"XOKH888J","showTime":{"__type":"Date","iso":"2017-01-08 00:00:00"},"updateWeek":2,"updatedAt":"2017-03-28 19:14:18"}
     * updatedAt : 2017-03-30 19:09:20
     * videoName : 鸟的去向
     * youkuUrl : http://youku.com
     */

    private String acUrl;
    private String biliUrl;
    private String createdAt;
    private String diliUrl;
    private String imgUrl;
    private String leUrl;
    private int number;
    private String objectId;
    private int playCount;
    private SuperAnimeBean superAnime;
    private String updatedAt;
    private String videoName;
    private String youkuUrl;

    public String getAcUrl() {
        return acUrl;
    }

    public void setAcUrl(String acUrl) {
        this.acUrl = acUrl;
    }

    public String getBiliUrl() {
        return biliUrl;
    }

    public void setBiliUrl(String biliUrl) {
        this.biliUrl = biliUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDiliUrl() {
        return diliUrl;
    }

    public void setDiliUrl(String diliUrl) {
        this.diliUrl = diliUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLeUrl() {
        return leUrl;
    }

    public void setLeUrl(String leUrl) {
        this.leUrl = leUrl;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public SuperAnimeBean getSuperAnime() {
        return superAnime;
    }

    public void setSuperAnime(SuperAnimeBean superAnime) {
        this.superAnime = superAnime;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getYoukuUrl() {
        return youkuUrl;
    }

    public void setYoukuUrl(String youkuUrl) {
        this.youkuUrl = youkuUrl;
    }

    public static class SuperAnimeBean implements Parcelable {


        /**
         * __type : Object
         * animeArea : 日本
         * animeIntroduce : 这个世界被划分为13个区，负责统一的唯一组织是ACCA。在那个组织中，前往各地区视察的“监察课”所属的课长，别名“弄得到香烟的吉恩”，是没有哪个组织“不吃他那套的男人”。然而视察后不久的他，却卷入了撼动世界的阴魔之中。
         * animeName : ACCA13区监察课
         * animeState : 1
         * className : Anime
         * createdAt : 2017-03-28 19:05:01
         * followCount : 999
         * imgUrl : http://i0.hdslb.com/bfs/bangumi/0aad6593530a088c531272b9c9c0a3b81db30317.jpg_225x300.jpg
         * objectId : XOKH888J
         * showTime : {"__type":"Date","iso":"2017-01-08 00:00:00"}
         * updateWeek : 2
         * updatedAt : 2017-03-28 19:14:18
         */
        private String lookPoint;
        private String __type;
        private String animeArea;
        private String animeIntroduce;
        private String animeName;
        private int animeState;
        private String className;
        private String createdAt;
        private int followCount;
        private String imgUrl;
        private String objectId;
        private ShowTimeBean showTime;
        private int updateWeek;
        private String updatedAt;

        public String getLookPoint() {
            return lookPoint;
        }

        public void setLookPoint(String lookPoint) {
            this.lookPoint = lookPoint;
        }

        public String get__type() {
            return __type;
        }

        public void set__type(String __type) {
            this.__type = __type;
        }

        public String getAnimeArea() {
            return animeArea;
        }

        public void setAnimeArea(String animeArea) {
            this.animeArea = animeArea;
        }

        public String getAnimeIntroduce() {
            return animeIntroduce;
        }

        public void setAnimeIntroduce(String animeIntroduce) {
            this.animeIntroduce = animeIntroduce;
        }

        public String getAnimeName() {
            return animeName;
        }

        public void setAnimeName(String animeName) {
            this.animeName = animeName;
        }

        public int getAnimeState() {
            return animeState;
        }

        public void setAnimeState(int animeState) {
            this.animeState = animeState;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
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

        public ShowTimeBean getShowTime() {
            return showTime;
        }

        public void setShowTime(ShowTimeBean showTime) {
            this.showTime = showTime;
        }

        public int getUpdateWeek() {
            return updateWeek;
        }

        public void setUpdateWeek(int updateWeek) {
            this.updateWeek = updateWeek;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public static class ShowTimeBean implements Parcelable {
            /**
             * __type : Date
             * iso : 2017-01-08 00:00:00
             */

            private String __type;
            private String iso;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getIso() {
                return iso;
            }

            public void setIso(String iso) {
                this.iso = iso;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.__type);
                dest.writeString(this.iso);
            }

            public ShowTimeBean() {
            }

            protected ShowTimeBean(Parcel in) {
                this.__type = in.readString();
                this.iso = in.readString();
            }

            public static final Creator<ShowTimeBean> CREATOR = new Creator<ShowTimeBean>() {
                @Override
                public ShowTimeBean createFromParcel(Parcel source) {
                    return new ShowTimeBean(source);
                }

                @Override
                public ShowTimeBean[] newArray(int size) {
                    return new ShowTimeBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.lookPoint);
            dest.writeString(this.__type);
            dest.writeString(this.animeArea);
            dest.writeString(this.animeIntroduce);
            dest.writeString(this.animeName);
            dest.writeInt(this.animeState);
            dest.writeString(this.className);
            dest.writeString(this.createdAt);
            dest.writeInt(this.followCount);
            dest.writeString(this.imgUrl);
            dest.writeString(this.objectId);
            dest.writeParcelable(this.showTime, flags);
            dest.writeInt(this.updateWeek);
            dest.writeString(this.updatedAt);
        }

        public SuperAnimeBean() {
        }

        protected SuperAnimeBean(Parcel in) {
            this.lookPoint = in.readString();
            this.__type = in.readString();
            this.animeArea = in.readString();
            this.animeIntroduce = in.readString();
            this.animeName = in.readString();
            this.animeState = in.readInt();
            this.className = in.readString();
            this.createdAt = in.readString();
            this.followCount = in.readInt();
            this.imgUrl = in.readString();
            this.objectId = in.readString();
            this.showTime = in.readParcelable(ShowTimeBean.class.getClassLoader());
            this.updateWeek = in.readInt();
            this.updatedAt = in.readString();
        }

        public static final Creator<SuperAnimeBean> CREATOR = new Creator<SuperAnimeBean>() {
            @Override
            public SuperAnimeBean createFromParcel(Parcel source) {
                return new SuperAnimeBean(source);
            }

            @Override
            public SuperAnimeBean[] newArray(int size) {
                return new SuperAnimeBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.acUrl);
        dest.writeString(this.biliUrl);
        dest.writeString(this.createdAt);
        dest.writeString(this.diliUrl);
        dest.writeString(this.imgUrl);
        dest.writeString(this.leUrl);
        dest.writeInt(this.number);
        dest.writeString(this.objectId);
        dest.writeInt(this.playCount);
        dest.writeParcelable(this.superAnime, flags);
        dest.writeString(this.updatedAt);
        dest.writeString(this.videoName);
        dest.writeString(this.youkuUrl);
    }

    public Video() {
    }

    protected Video(Parcel in) {
        this.acUrl = in.readString();
        this.biliUrl = in.readString();
        this.createdAt = in.readString();
        this.diliUrl = in.readString();
        this.imgUrl = in.readString();
        this.leUrl = in.readString();
        this.number = in.readInt();
        this.objectId = in.readString();
        this.playCount = in.readInt();
        this.superAnime = in.readParcelable(SuperAnimeBean.class.getClassLoader());
        this.updatedAt = in.readString();
        this.videoName = in.readString();
        this.youkuUrl = in.readString();
    }

    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel source) {
            return new Video(source);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}
