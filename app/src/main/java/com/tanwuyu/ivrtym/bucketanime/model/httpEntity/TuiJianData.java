package com.tanwuyu.ivrtym.bucketanime.model.httpEntity;

/**
 * Created by ivrty on 2017/3/29.
 */

public class TuiJianData {

    /**
     * contentAnime : {"__type":"Object","animeArea":"中国","animeIntroduce":"\u201c天行九歌\u201d是玄机科技旗下\u201c秦时明月\u201d的双子星品牌，大预算、大制作、大宣发，是一部战国题材3A级玄幻动画大作，由秦时明月核心主创2016革新呈献，该剧将引领观众进入战国末年，风起云涌的大时代中，战国七雄在乱世中群雄并起，百家争鸣。","animeName":"秦时明月之天行九歌","animeState":1,"className":"Anime","createdAt":"2017-03-29 00:25:07","followCount":999,"imgUrl":"http://img.kan300.com/201603/txjg.jpg","objectId":"xidNG44G","showTime":{"__type":"Date","iso":"2017-03-29 00:00:00"},"updateWeek":4,"updatedAt":"2017-03-29 00:25:49"}
     * contentType : 2
     * contentVideo : {"__type":"Object","acUrl":"http://youku.com","biliUrl":"http://youku.com","className":"Video","collectCount":888,"createdAt":"2017-03-29 16:51:23","diliUrl":"http://www.dilidili.wang/watch3/54881/","imgUrl":"http://youku.com","leUrl":"http://youku.com","number":4,"objectId":"n4awXXX4","playCount":999,"superAnime":{"__type":"Pointer","className":"Anime","objectId":"XOKH888J"},"updatedAt":"2017-03-30 19:09:19","videoName":"封闭之国的余烬","youkuUrl":"http://youku.com"}
     * createdAt : 2017-03-30 19:28:28
     * objectId : 3FsN4114
     * updatedAt : 2017-03-30 19:29:41
     */

    private ContentAnimeBean contentAnime;
    private int contentType;
    private ContentVideoBean contentVideo;
    private String createdAt;
    private String objectId;
    private String updatedAt;

    public ContentAnimeBean getContentAnime() {
        return contentAnime;
    }

    public void setContentAnime(ContentAnimeBean contentAnime) {
        this.contentAnime = contentAnime;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
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

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class ContentAnimeBean {


        /**
         * __type : Object
         * animeArea : 中国
         * animeIntroduce : “天行九歌”是玄机科技旗下“秦时明月”的双子星品牌，大预算、大制作、大宣发，是一部战国题材3A级玄幻动画大作，由秦时明月核心主创2016革新呈献，该剧将引领观众进入战国末年，风起云涌的大时代中，战国七雄在乱世中群雄并起，百家争鸣。
         * animeName : 秦时明月之天行九歌
         * animeState : 1
         * className : Anime
         * createdAt : 2017-03-29 00:25:07
         * followCount : 999
         * imgUrl : http://img.kan300.com/201603/txjg.jpg
         * objectId : xidNG44G
         * showTime : {"__type":"Date","iso":"2017-03-29 00:00:00"}
         * updateWeek : 4
         * updatedAt : 2017-03-29 00:25:49
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

        public static class ShowTimeBean {
            /**
             * __type : Date
             * iso : 2017-03-29 00:00:00
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
         * collectCount : 888
         * createdAt : 2017-03-29 16:51:23
         * diliUrl : http://www.dilidili.wang/watch3/54881/
         * imgUrl : http://youku.com
         * leUrl : http://youku.com
         * number : 4
         * objectId : n4awXXX4
         * playCount : 999
         * superAnime : {"__type":"Pointer","className":"Anime","objectId":"XOKH888J"}
         * updatedAt : 2017-03-30 19:09:19
         * videoName : 封闭之国的余烬
         * youkuUrl : http://youku.com
         */

        private String __type;
        private String acUrl;
        private String biliUrl;
        private String className;
        private int collectCount;
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

        public int getCollectCount() {
            return collectCount;
        }

        public void setCollectCount(int collectCount) {
            this.collectCount = collectCount;
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
