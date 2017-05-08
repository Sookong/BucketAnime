package com.tanwuyu.ivrtym.bucketanime.model;

/**
 * Created by ivrty on 2017/4/27.
 */

public class AnimeReply {

    /**
     * createdAt : 2017-05-04 20:58:59
     * objectId : eD1fCCCK
     * replyAuthor : {"__type":"Pointer","className":"_User","objectId":"Kh6M777V"}
     * replyContent : 笑摸三楼狗头
     * superAnime : {"__type":"Pointer","className":"Anime","objectId":"YcpNL77L"}
     * targetReply : {"__type":"Pointer","className":"AnimeReply","objectId":"pWyNEEEj"}
     * updatedAt : 2017-05-04 22:13:35
     */

    private String createdAt;
    private String objectId;
    private ReplyAuthorBean replyAuthor;
    private String replyContent;
    private SuperAnimeBean superAnime;
    private TargetReplyBean targetReply;
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

    public ReplyAuthorBean getReplyAuthor() {
        return replyAuthor;
    }

    public void setReplyAuthor(ReplyAuthorBean replyAuthor) {
        this.replyAuthor = replyAuthor;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public SuperAnimeBean getSuperAnime() {
        return superAnime;
    }

    public void setSuperAnime(SuperAnimeBean superAnime) {
        this.superAnime = superAnime;
    }

    public TargetReplyBean getTargetReply() {
        return targetReply;
    }

    public void setTargetReply(TargetReplyBean targetReply) {
        this.targetReply = targetReply;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class ReplyAuthorBean {
        /**
         * __type : Pointer
         * className : _User
         * objectId : Kh6M777V
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

    public static class SuperAnimeBean {
        /**
         * __type : Pointer
         * className : Anime
         * objectId : YcpNL77L
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

    public static class TargetReplyBean {
        /**
         * __type : Pointer
         * className : AnimeReply
         * objectId : pWyNEEEj
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
