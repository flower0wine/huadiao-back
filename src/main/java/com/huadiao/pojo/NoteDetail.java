package com.huadiao.pojo;

import java.util.List;

/**
 * @projectName  huadiao
 * @author  flowerwine
 * @version  1.0
 *
 * 该类用于单个笔记展示，显示详细信息
 */
public class NoteDetail {

    /**
     * 作者信息, 包括笔记
     */
    private String authorUid;
    private String authorUserId;
    private String authorUserAvatar;
    private String authorNickname;
    private String noteTitle;
    private String noteContent;
    private String buildDate;
    private String viewNumber;
    private String likeNumber;
    private String starNumber;
    private String markNumber;
    /**
     * 我对该笔记的评价, 喜欢, 不喜欢, 收藏
     */
    private boolean isMyStar;
    private boolean isMyLike;
    private boolean isMyUnlike;
    /**
     * 浏览者与作者的关系
     */
    private boolean following;
    private boolean followed;
    private boolean me;
    /**
     * 浏览者的信息
     */
    private String viewerUid;
    private String viewerNickname;
    private String viewerUserAvatar;
    /**
     * 该笔记下的所有评论
     */
    private List<RootMark> rootMarkList;

    public String getAuthorUid() {
        return authorUid;
    }

    public void setAuthorUid(String authorUid) {
        this.authorUid = authorUid;
    }

    public String getAuthorUserId() {
        return authorUserId;
    }

    public void setAuthorUserId(String authorUserId) {
        this.authorUserId = authorUserId;
    }

    public String getAuthorUserAvatar() {
        return authorUserAvatar;
    }

    public void setAuthorUserAvatar(String authorUserAvatar) {
        this.authorUserAvatar = authorUserAvatar;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }

    public void setAuthorNickname(String authorNickname) {
        this.authorNickname = authorNickname;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public String getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(String viewNumber) {
        this.viewNumber = viewNumber;
    }

    public String getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(String likeNumber) {
        this.likeNumber = likeNumber;
    }

    public String getStarNumber() {
        return starNumber;
    }

    public void setStarNumber(String starNumber) {
        this.starNumber = starNumber;
    }

    public String getMarkNumber() {
        return markNumber;
    }

    public void setMarkNumber(String markNumber) {
        this.markNumber = markNumber;
    }

    public boolean isMyStar() {
        return isMyStar;
    }

    public void setMyStar(boolean myStar) {
        isMyStar = myStar;
    }

    public boolean isMyLike() {
        return isMyLike;
    }

    public void setMyLike(boolean myLike) {
        isMyLike = myLike;
    }

    public boolean isMyUnlike() {
        return isMyUnlike;
    }

    public void setMyUnlike(boolean myUnlike) {
        isMyUnlike = myUnlike;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public boolean isFollowed() {
        return followed;
    }

    public void setFollowed(boolean followed) {
        this.followed = followed;
    }

    public boolean isMe() {
        return me;
    }

    public void setMe(boolean me) {
        this.me = me;
    }

    public String getViewerUid() {
        return viewerUid;
    }

    public void setViewerUid(String viewerUid) {
        this.viewerUid = viewerUid;
    }

    public String getViewerNickname() {
        return viewerNickname;
    }

    public void setViewerNickname(String viewerNickname) {
        this.viewerNickname = viewerNickname;
    }

    public String getViewerUserAvatar() {
        return viewerUserAvatar;
    }

    public void setViewerUserAvatar(String viewerUserAvatar) {
        this.viewerUserAvatar = viewerUserAvatar;
    }

    public List<RootMark> getRootMarkList() {
        return rootMarkList;
    }

    public void setRootMarkList(List<RootMark> rootMarkList) {
        this.rootMarkList = rootMarkList;
    }

    @Override
    public String toString() {
        return "NoteDetail{" +
                "authorUid='" + authorUid + '\'' +
                ", authorUserId='" + authorUserId + '\'' +
                ", authorUserAvatar='" + authorUserAvatar + '\'' +
                ", authorNickname='" + authorNickname + '\'' +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteContent='" + noteContent + '\'' +
                ", buildDate='" + buildDate + '\'' +
                ", viewNumber='" + viewNumber + '\'' +
                ", likeNumber='" + likeNumber + '\'' +
                ", starNumber='" + starNumber + '\'' +
                ", markNumber='" + markNumber + '\'' +
                ", isMyStar=" + isMyStar +
                ", isMyLike=" + isMyLike +
                ", isMyUnlike=" + isMyUnlike +
                ", following=" + following +
                ", followed=" + followed +
                ", me=" + me +
                ", viewerUid='" + viewerUid + '\'' +
                ", viewerNickname='" + viewerNickname + '\'' +
                ", viewerUserAvatar='" + viewerUserAvatar + '\'' +
                ", rootMarkList=" + rootMarkList +
                '}';
    }
}
