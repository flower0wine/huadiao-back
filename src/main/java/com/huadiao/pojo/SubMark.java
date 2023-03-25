package com.huadiao.pojo;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 23 17:33
 */
public class SubMark {
    private String uid;
    private String nickname;
    private String userAvatar;
    private String markContent;
    private String markDate;
    private String markLike;
    private String selfId;
    private String subStatus;
    private String subUnlike;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getMarkContent() {
        return markContent;
    }

    public void setMarkContent(String markContent) {
        this.markContent = markContent;
    }

    public String getMarkDate() {
        return markDate;
    }

    public void setMarkDate(String markDate) {
        this.markDate = markDate;
    }

    public String getMarkLike() {
        return markLike;
    }

    public void setMarkLike(String markLike) {
        this.markLike = markLike;
    }

    public String getSelfId() {
        return selfId;
    }

    public void setSelfId(String selfId) {
        this.selfId = selfId;
    }

    public String getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(String subStatus) {
        this.subStatus = subStatus;
    }

    public String getSubUnlike() {
        return subUnlike;
    }

    public void setSubUnlike(String subUnlike) {
        this.subUnlike = subUnlike;
    }

    @Override
    public String toString() {
        return "SubMark{" +
                "uid='" + uid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", markContent='" + markContent + '\'' +
                ", markDate='" + markDate + '\'' +
                ", markLike='" + markLike + '\'' +
                ", selfId='" + selfId + '\'' +
                ", subStatus='" + subStatus + '\'' +
                ", subUnlike='" + subUnlike + '\'' +
                '}';
    }
}
