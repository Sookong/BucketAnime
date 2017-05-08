package com.tanwuyu.ivrtym.bucketanime.model.httpEntity;

import android.os.Parcelable;

/**
 * Created by ivrty on 2017/3/27.
 */

public class Anime {
    /**
     * animeArea : 日本
     * animeIntroduce : 这个世界被划分为13个区，负责统一的唯一组织是ACCA。在那个组织中，前往各地区视察的“监察课”所属的课长，别名“弄得到香烟的吉恩”，是没有哪个组织“不吃他那套的男人”。然而视察后不久的他，却卷入了撼动世界的阴魔之中。
     * animeName : ACCA13区监察课
     * animeState : 1
     * createdAt : 2017-03-28 19:05:01
     * followCount : 999
     * imgUrl : http://i0.hdslb.com/bfs/bangumi/0aad6593530a088c531272b9c9c0a3b81db30317.jpg_225x300.jpg
     * lookPoint : 看点:xxxxxxxx
     * objectId : XOKH888J
     * playCount : 77
     * showTime : {"__type":"Date","iso":"2017-01-08 00:00:00"}
     * simpleIntroduce : 看点:xxxxxxxx
     * updateWeek : 2
     * updatedAt : 2017-04-27 17:46:54
     */

    private String animeArea;
    private String animeIntroduce;
    private String animeName;
    private int animeState;
    private String createdAt;
    private int followCount;
    private String imgUrl;
    private String lookPoint;
    private String objectId;
    private int playCount;
    private ShowTimeBean showTime;
    private String simpleIntroduce;
    private int updateWeek;
    private String updatedAt;

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

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public ShowTimeBean getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTimeBean showTime) {
        this.showTime = showTime;
    }

    public String getSimpleIntroduce() {
        return simpleIntroduce;
    }

    public void setSimpleIntroduce(String simpleIntroduce) {
        this.simpleIntroduce = simpleIntroduce;
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
