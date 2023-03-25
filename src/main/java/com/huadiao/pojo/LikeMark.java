package com.huadiao.pojo;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 30 22:44
 */
public class LikeMark {
    private String userAvatar;
    private String nickname;
    private String uid;
    private String likedDate;
    private String read;

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLikedDate() {
        return likedDate;
    }

    public void setLikedDate(String likedDate) {
        this.likedDate = likedDate;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "LikeMark{" +
                "userAvatar='" + userAvatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", uid='" + uid + '\'' +
                ", likedDate='" + likedDate + '\'' +
                ", read='" + read + '\'' +
                '}';
    }
}
