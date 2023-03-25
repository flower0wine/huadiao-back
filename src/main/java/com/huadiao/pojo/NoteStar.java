package com.huadiao.pojo;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 29 20:49
 */
public class NoteStar {
    private String note_title;
    private String note_id;
    private String note_abstract;
    private String star_date;
    private String user_avatar;
    private String nickname;
    private String stared_uid;

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_id() {
        return note_id;
    }

    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }

    public String getNote_abstract() {
        return note_abstract;
    }

    public void setNote_abstract(String note_abstract) {
        this.note_abstract = note_abstract;
    }

    public String getStar_date() {
        return star_date;
    }

    public void setStar_date(String star_date) {
        this.star_date = star_date;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStared_uid() {
        return stared_uid;
    }

    public void setStared_uid(String stared_uid) {
        this.stared_uid = stared_uid;
    }

    @Override
    public String toString() {
        return "NoteStar{" +
                "note_title='" + note_title + '\'' +
                ", note_id='" + note_id + '\'' +
                ", note_abstract='" + note_abstract + '\'' +
                ", star_date='" + star_date + '\'' +
                ", user_avatar='" + user_avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", stared_uid='" + stared_uid + '\'' +
                '}';
    }
}
