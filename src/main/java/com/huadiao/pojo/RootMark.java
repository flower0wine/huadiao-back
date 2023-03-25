package com.huadiao.pojo;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 23 17:16
 */
public class RootMark {
    private String uid;
    private String nickname;
    private String userAvatar;
    private String markContent;
    private String rootMarkId;
    private String markDate;
    private String markLike;
    private String rootStatus;
    private String rootUnlike;
    private List<SubMark> markList;

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

    public String getRootMarkId() {
        return rootMarkId;
    }

    public void setRootMarkId(String rootMarkId) {
        this.rootMarkId = rootMarkId;
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

    public String getRootStatus() {
        return rootStatus;
    }

    public void setRootStatus(String rootStatus) {
        this.rootStatus = rootStatus;
    }

    public String getRootUnlike() {
        return rootUnlike;
    }

    public void setRootUnlike(String rootUnlike) {
        this.rootUnlike = rootUnlike;
    }

    public List<SubMark> getMarkList() {
        return markList;
    }

    public void setMarkList(List<SubMark> markList) {
        this.markList = markList;
    }

    @Override
    public String toString() {
        return "RootMark{" +
                "uid='" + uid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", markContent='" + markContent + '\'' +
                ", rootMarkId='" + rootMarkId + '\'' +
                ", markDate='" + markDate + '\'' +
                ", markLike='" + markLike + '\'' +
                ", rootStatus='" + rootStatus + '\'' +
                ", rootUnlike='" + rootUnlike + '\'' +
                ", markList=" + markList +
                '}';
    }
}
