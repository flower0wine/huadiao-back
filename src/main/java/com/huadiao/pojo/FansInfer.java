package com.huadiao.pojo;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description
 */
public class FansInfer {
    private String userAvatar;
    private String uid;
    private String nickname;
    private String canvases;

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCanvases() {
        return canvases;
    }

    public void setCanvases(String canvases) {
        this.canvases = canvases;
    }

    @Override
    public String toString() {
        return "FansInfer{" +
                "userAvatar='" + userAvatar + '\'' +
                ", uid='" + uid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", canvases='" + canvases + '\'' +
                '}';
    }
}
