package com.huadiao.pojo;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description
 */
public class FollowsInfer {
    private String userAvatar;
    private String uid;
    private String nickname;
    private String canvases;
    /**
     * 关注需要分组
     */
    private Integer favoritesId;
    private String favoritesName;

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

    public Integer getFavoritesId() {
        return favoritesId;
    }

    public void setFavoritesId(Integer favoritesId) {
        this.favoritesId = favoritesId;
    }

    public String getFavoritesName() {
        return favoritesName;
    }

    public void setFavoritesName(String favoritesName) {
        this.favoritesName = favoritesName;
    }

    @Override
    public String toString() {
        return "FollowsInfer{" +
                "userAvatar='" + userAvatar + '\'' +
                ", uid='" + uid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", canvases='" + canvases + '\'' +
                ", favoritesId=" + favoritesId +
                ", favoritesName='" + favoritesName + '\'' +
                '}';
    }
}
