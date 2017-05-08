package com.tanwuyu.ivrtym.bucketanime.model;

/**
 * Created by ivrty on 2017/5/4.
 */

public class VideoReply {

    /**
     * createdAt : 2017-05-04 22:28:35
     * objectId : bhtATTTC
     * replyAuthor : {"__type":"Pointer","className":"_User","objectId":"cIEdDDDH"}
     * replyContent : 今天谁的鸡儿也别想放假
     * superVideo : {"__type":"Pointer","className":"Video","objectId":"K7yt1NN1"}
     * targetReply : {"__type":"Pointer","className":"VideoReply","objectId":"64Cc1115"}
     * updatedAt : 2017-05-04 22:37:50
     */

    private String createdAt;
    private String objectId;
    private ReplyAuthorBean replyAuthor;
    private String replyContent;
    private SuperVideoBean superVideo;
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

    public SuperVideoBean getSuperVideo() {
        return superVideo;
    }

    public void setSuperVideo(SuperVideoBean superVideo) {
        this.superVideo = superVideo;
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
         * objectId : cIEdDDDH
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

    public static class SuperVideoBean {
        /**
         * __type : Pointer
         * className : Video
         * objectId : K7yt1NN1
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
         * className : VideoReply
         * objectId : 64Cc1115
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
