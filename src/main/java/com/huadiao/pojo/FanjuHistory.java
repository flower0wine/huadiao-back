package com.huadiao.pojo;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 29 9:30
 */
public class FanjuHistory {
    private String authorUserAvatar;
    private String authorUid;
    private String authorNickname;
    private String viewDate;
    private String fanjuCover;
    private String fanjuTitle;
    private String fanjuCanvases;

    public String getAuthorUserAvatar() {
        return authorUserAvatar;
    }

    public void setAuthorUserAvatar(String authorUserAvatar) {
        this.authorUserAvatar = authorUserAvatar;
    }

    public String getAuthorUid() {
        return authorUid;
    }

    public void setAuthorUid(String authorUid) {
        this.authorUid = authorUid;
    }

    public String getAuthorNickname() {
        return authorNickname;
    }

    public void setAuthorNickname(String authorNickname) {
        this.authorNickname = authorNickname;
    }

    public String getViewDate() {
        return viewDate;
    }

    public void setViewDate(String viewDate) {
        this.viewDate = viewDate;
    }

    public String getFanjuCover() {
        return fanjuCover;
    }

    public void setFanjuCover(String fanjuCover) {
        this.fanjuCover = fanjuCover;
    }

    public String getFanjuTitle() {
        return fanjuTitle;
    }

    public void setFanjuTitle(String fanjuTitle) {
        this.fanjuTitle = fanjuTitle;
    }

    public String getFanjuCanvases() {
        return fanjuCanvases;
    }

    public void setFanjuCanvases(String fanjuCanvases) {
        this.fanjuCanvases = fanjuCanvases;
    }

    @Override
    public String toString() {
        return "FanjuHistory{" +
                "authorUserAvatar='" + authorUserAvatar + '\'' +
                ", authorUid='" + authorUid + '\'' +
                ", authorNickname='" + authorNickname + '\'' +
                ", viewDate='" + viewDate + '\'' +
                ", fanjuCover='" + fanjuCover + '\'' +
                ", fanjuTitle='" + fanjuTitle + '\'' +
                ", fanjuCanvases='" + fanjuCanvases + '\'' +
                '}';
    }
}
