package com.tanwuyu.ivrtym.bucketanime.model.httpEntity;

/**
 * Created by ivrty on 2017/3/31.
 */

public class TuiJianHeadData {
    /**
     * contentAnime : {"__type":"Object","animeArea":"日本","animeIntroduce":"《龙的牙医》故事发生在一个架空的世界，在龙之国传说中龙与人类签订了契约互帮互助，主角是专门帮国家守护神龙清理蛀牙菌的新人牙医野乃子(野ノ子)，龙之国目前正在处于和敌国的战争中而且战争日趋激烈，某天野乃子在龙的巨大牙齿上发现了晕倒的敌国少年兵，少年的名字叫贝尔，而野乃子将这名少年当做龙的牙医捡了回来。","animeName":"龙的牙医","animeState":0,"className":"Anime","createdAt":"2017-03-29 00:35:17","followCount":999,"imgUrl":"http://www.dilidili.com/uploads/allimg/161028/1_1119321371.jpg","lookPoint":"看点:xxxxxxxx","objectId":"LfyTLRR9","showTime":{"__type":"Date","iso":"2017-01-08 00:00:00"},"updateWeek":6,"updatedAt":"2017-03-30 20:41:11"}
     * contentImgUrl : http://m.dilidili.wang/old/images/sy.jpg
     * contentType : 0
     * contentUrl : http://m.dilidili.wang/old/
     * contentVideo : {"__type":"Object","acUrl":"http://youku.com","biliUrl":"http://youku.com","className":"Video","createdAt":"2017-03-29 16:51:52","diliUrl":"http://www.dilidili.wang/watch3/55757/","imgUrl":"http://youku.com","leUrl":"http://youku.com","number":12,"objectId":"6P8Bhhhr","playCount":999,"superAnime":{"__type":"Pointer","className":"Anime","objectId":"XOKH888J"},"updatedAt":"2017-03-30 19:09:20","videoName":"鸟的去向","youkuUrl":"http://youku.com"}
     * createdAt : 2017-03-31 17:14:12
     * objectId : BTRc2225
     * title : 宫崎骏系列动画推荐
     * updatedAt : 2017-03-31 17:21:52
     */

    private ContentAnimeBean contentAnime;
    private String contentImgUrl;
    private int contentType;
    private String contentUrl;
    private ContentVideoBean contentVideo;
    private String createdAt;
    private String objectId;
    private String title;
    private String updatedAt;

    public ContentAnimeBean getContentAnime() {
        return contentAnime;
    }

    public void setContentAnime(ContentAnimeBean contentAnime) {
        this.contentAnime = contentAnime;
    }

    public String getContentImgUrl() {
        return contentImgUrl;
    }

    public void setContentImgUrl(String contentImgUrl) {
        this.contentImgUrl = contentImgUrl;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public ContentVideoBean getContentVideo() {
        return contentVideo;
    }

    public void setContentVideo(ContentVideoBean contentVideo) {
        this.contentVideo = contentVideo;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class ContentAnimeBean {
        /**
         * __type : Object
         * animeArea : 日本
         * animeIntroduce : 《龙的牙医》故事发生在一个架空的世界，在龙之国传说中龙与人类签订了契约互帮互助，主角是专门帮国家守护神龙清理蛀牙菌的新人牙医野乃子(野ノ子)，龙之国目前正在处于和敌国的战争中而且战争日趋激烈，某天野乃子在龙的巨大牙齿上发现了晕倒的敌国少年兵，少年的名字叫贝尔，而野乃子将这名少年当做龙的牙医捡了回来。
         * animeName : 龙的牙医
         * animeState : 0
         * className : Anime
         * createdAt : 2017-03-29 00:35:17
         * followCount : 999
         * imgUrl : http://www.dilidili.com/uploads/allimg/161028/1_1119321371.jpg
         * lookPoint : 看点:xxxxxxxx
         * objectId : LfyTLRR9
         * showTime : {"__type":"Date","iso":"2017-01-08 00:00:00"}
         * updateWeek : 6
         * updatedAt : 2017-03-30 20:41:11
         */

        private String __type;
        private String animeArea;
        private String animeIntroduce;
        private String animeName;
        private int animeState;
        private String className;
        private String createdAt;
        private int followCount;
        private String imgUrl;
        private String lookPoint;
        private String objectId;
        private ShowTimeBean showTime;
        private int updateWeek;
        private String updatedAt;

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

        public String getLookPoint() {
            return lookPoint;
        }

        public void setLookPoint(String lookPoint) {
            this.lookPoint = lookPoint;
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

        public static class ShowTimeBean {
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
        }
    }

    public static class ContentVideoBean {
        /**
         * __type : Object
         * acUrl : http://youku.com
         * biliUrl : http://youku.com
         * className : Video
         * createdAt : 2017-03-29 16:51:52
         * diliUrl : http://www.dilidili.wang/watch3/55757/
         * imgUrl : http://youku.com
         * leUrl : http://youku.com
         * number : 12
         * objectId : 6P8Bhhhr
         * playCount : 999
         * superAnime : {"__type":"Pointer","className":"Anime","objectId":"XOKH888J"}
         * updatedAt : 2017-03-30 19:09:20
         * videoName : 鸟的去向
         * youkuUrl : http://youku.com
         */

        private String __type;
        private String acUrl;
        private String biliUrl;
        private String className;
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

        public String get__type() {
            return __type;
        }

        public void set__type(String __type) {
            this.__type = __type;
        }

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

        public static class SuperAnimeBean {
            /**
             * __type : Pointer
             * className : Anime
             * objectId : XOKH888J
             */

            private String __type;
            private String className;
            private String objectId;

            public String get__type() {
                return __type;
            }

            public void set__type(String __type) {
                this.__type = __type;
            }

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }

            public String getObjectId() {
                return objectId;
            }

            public void setObjectId(String objectId) {
                this.objectId = objectId;
            }
        }
    }
}
