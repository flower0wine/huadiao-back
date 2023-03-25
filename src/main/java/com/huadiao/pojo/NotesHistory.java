package com.huadiao.pojo;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 29 9:30
 */
public class NotesHistory {
    private String authorNickname;
    private String authorUserAvatar;
    private String authorUid;
    private String viewedDate;
    private String noteAbstract;
    private String noteTitle;
    private String noteId;

    public String getAuthorNickname() {
        return authorNickname;
    }

    public void setAuthorNickname(String authorNickname) {
        this.authorNickname = authorNickname;
    }

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

    public String getViewedDate() {
        return viewedDate;
    }

    public void setViewedDate(String viewedDate) {
        this.viewedDate = viewedDate;
    }

    public String getNoteAbstract() {
        return noteAbstract;
    }

    public void setNoteAbstract(String noteAbstract) {
        this.noteAbstract = noteAbstract;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    @Override
    public String toString() {
        return "NotesHistory{" +
                "authorNickname='" + authorNickname + '\'' +
                ", authorUserAvatar='" + authorUserAvatar + '\'' +
                ", authorUid='" + authorUid + '\'' +
                ", viewedDate='" + viewedDate + '\'' +
                ", noteAbstract='" + noteAbstract + '\'' +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteId='" + noteId + '\'' +
                '}';
    }
}
