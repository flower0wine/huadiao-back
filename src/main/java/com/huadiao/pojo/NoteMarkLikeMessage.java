package com.huadiao.pojo;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 30 12:56
 */
public class NoteMarkLikeMessage {
    private String rootMarkId;
    private String subMarkId;
    private String noteId;
    private List list;
    private List<LikeMark> likeMarkList;

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

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public List<LikeMark> getLikeMarkList() {
        return likeMarkList;
    }

    public void setLikeMarkList(List<LikeMark> likeMarkList) {
        this.likeMarkList = likeMarkList;
    }

    @Override
    public String toString() {
        return "LikeMessage{" +
                "rootMarkId='" + rootMarkId + '\'' +
                ", subMarkId='" + subMarkId + '\'' +
                ", noteId='" + noteId + '\'' +
                ", list=" + list +
                ", likeMarkList=" + likeMarkList +
                '}';
    }
}
