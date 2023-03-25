package com.huadiao.pojo;


/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 15 19:36
 *
 * 该类是用于我的笔记页面的展示，单个笔记页面信息太多，这个类信息不全
 */
public class Note {
    private String noteId;
    private String noteTitle;
    private String buildDate;
    private String noteAbstract;
    private String viewNumber;
    private String likeNumber;
    private String starNumber;
    private String markNumber;

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public String getNoteAbstract() {
        return noteAbstract;
    }

    public void setNoteAbstract(String noteAbstract) {
        this.noteAbstract = noteAbstract;
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

    @Override
    public String toString() {
        return "Note{" +
                "noteId='" + noteId + '\'' +
                ", noteTitle='" + noteTitle + '\'' +
                ", buildDate='" + buildDate + '\'' +
                ", noteAbstract='" + noteAbstract + '\'' +
                ", viewNumber='" + viewNumber + '\'' +
                ", likeNumber='" + likeNumber + '\'' +
                ", starNumber='" + starNumber + '\'' +
                ", markNumber='" + markNumber + '\'' +
                '}';
    }
}
