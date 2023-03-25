package com.huadiao.pojo;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 24 10:09
 */
public class ReplyMeMessage {
    private String authorUid;
    private String markUid;
    private String userAvatar;
    private String nickname;
    private String markContent;
    private String markDate;
    private String rootMarkId;
    private String subMarkId;
    private String noteId;
    private String read;
    private String likeStatus;

    public String getAuthorUid() {
        return authorUid;
    }

    public void setAuthorUid(String authorUid) {
        this.authorUid = authorUid;
    }

    public String getMarkUid() {
        return markUid;
    }

    public void setMarkUid(String markUid) {
        this.markUid = markUid;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getRootMarkId() {
        return rootMarkId;
    }

    public void setRootMarkId(String rootMarkId) {
        this.rootMarkId = rootMarkId;
    }

    public String getSubMarkId() {
        return subMarkId;
    }

    public void setSubMarkId(String subMarkId) {
        this.subMarkId = subMarkId;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(String likeStatus) {
        this.likeStatus = likeStatus;
    }

    @Override
    public String toString() {
        return "ReplyMeMessage{" +
                "authorUid='" + authorUid + '\'' +
                ", markUid='" + markUid + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", markContent='" + markContent + '\'' +
                ", markDate='" + markDate + '\'' +
                ", rootMarkId='" + rootMarkId + '\'' +
                ", subMarkId='" + subMarkId + '\'' +
                ", noteId='" + noteId + '\'' +
                ", read='" + read + '\'' +
                ", likeStatus='" + likeStatus + '\'' +
                '}';
    }
}
