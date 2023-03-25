package com.huadiao.pojo;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 10 15 20:00
 */
public class Notes {
    private List<Note> noteList;
    private String authorUserAvatar;
    private String authorNickname;

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
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

    @Override
    public String toString() {
        return "Notes{" +
                "noteList=" + noteList +
                ", authorUserAvatar='" + authorUserAvatar + '\'' +
                ", authorNickname='" + authorNickname + '\'' +
                '}';
    }
}
