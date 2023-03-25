package com.huadiao.pojo;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 29 17:27
 */
public class IndexInfer {

    private String uid;
    private String user_avatar;
    private String nickname;
    private Integer follow;
    private Integer fans;
    private String poem;
    private String poet;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public Integer getFollow() {
        return follow;
    }

    public void setFollow(Integer follow) {
        this.follow = follow;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public String getPoem() {
        return poem;
    }

    public void setPoem(String poem) {
        this.poem = poem;
    }

    public String getPoet() {
        return poet;
    }

    public void setPoet(String poet) {
        this.poet = poet;
    }

    @Override
    public String toString() {
        return "indexInfer{" +
                "uid='" + uid + '\'' +
                ", user_avatar='" + user_avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", follow=" + follow +
                ", fans=" + fans +
                ", poem='" + poem + '\'' +
                ", poet='" + poet + '\'' +
                '}';
    }
}
