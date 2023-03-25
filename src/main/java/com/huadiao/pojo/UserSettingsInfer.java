package com.huadiao.pojo;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 09 01 16:54
 */
public class UserSettingsInfer {
    private String uid;
    private String userId;
    private String publicStarStatus;
    private String publicBornStatus;
    private String publicFanjuStatus;
    private String publicSchoolStatus;
    private String publicFollowStatus;
    private String publicCanvasesStatus;
    private String publicHomepageStatus;
    private String messageRemindStatus;
    private String messageReplyStatus;
    private String messageLikeStatus;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPublicStarStatus() {
        return publicStarStatus;
    }

    public void setPublicStarStatus(String publicStarStatus) {
        this.publicStarStatus = publicStarStatus;
    }

    public String getPublicBornStatus() {
        return publicBornStatus;
    }

    public void setPublicBornStatus(String publicBornStatus) {
        this.publicBornStatus = publicBornStatus;
    }

    public String getPublicFanjuStatus() {
        return publicFanjuStatus;
    }

    public void setPublicFanjuStatus(String publicFanjuStatus) {
        this.publicFanjuStatus = publicFanjuStatus;
    }

    public String getPublicSchoolStatus() {
        return publicSchoolStatus;
    }

    public void setPublicSchoolStatus(String publicSchoolStatus) {
        this.publicSchoolStatus = publicSchoolStatus;
    }

    public String getPublicFollowStatus() {
        return publicFollowStatus;
    }

    public void setPublicFollowStatus(String publicFollowStatus) {
        this.publicFollowStatus = publicFollowStatus;
    }

    public String getPublicCanvasesStatus() {
        return publicCanvasesStatus;
    }

    public void setPublicCanvasesStatus(String publicCanvasesStatus) {
        this.publicCanvasesStatus = publicCanvasesStatus;
    }

    public String getPublicHomepageStatus() {
        return publicHomepageStatus;
    }

    public void setPublicHomepageStatus(String publicHomepageStatus) {
        this.publicHomepageStatus = publicHomepageStatus;
    }

    public String getMessageRemindStatus() {
        return messageRemindStatus;
    }

    public void setMessageRemindStatus(String messageRemindStatus) {
        this.messageRemindStatus = messageRemindStatus;
    }

    public String getMessageReplyStatus() {
        return messageReplyStatus;
    }

    public void setMessageReplyStatus(String messageReplyStatus) {
        this.messageReplyStatus = messageReplyStatus;
    }

    public String getMessageLikeStatus() {
        return messageLikeStatus;
    }

    public void setMessageLikeStatus(String messageLikeStatus) {
        this.messageLikeStatus = messageLikeStatus;
    }

    @Override
    public String toString() {
        return "UserSettingsInfer{" +
                "uid='" + uid + '\'' +
                ", userId='" + userId + '\'' +
                ", publicStarStatus='" + publicStarStatus + '\'' +
                ", publicBornStatus='" + publicBornStatus + '\'' +
                ", publicFanjuStatus='" + publicFanjuStatus + '\'' +
                ", publicSchoolStatus='" + publicSchoolStatus + '\'' +
                ", publicFollowStatus='" + publicFollowStatus + '\'' +
                ", publicCanvasesStatus='" + publicCanvasesStatus + '\'' +
                ", publicHomepageStatus='" + publicHomepageStatus + '\'' +
                ", messageRemindStatus='" + messageRemindStatus + '\'' +
                ", messageReplyStatus='" + messageReplyStatus + '\'' +
                ", messageLikeStatus='" + messageLikeStatus + '\'' +
                '}';
    }
}
