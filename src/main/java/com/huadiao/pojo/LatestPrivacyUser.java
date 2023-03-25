package com.huadiao.pojo;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 09 20 15:27
 */
public class LatestPrivacyUser {
    private String uid;
    private String nickname;
    private String canvases;
    private String userAvatar;
    private String sessionId;
    private List<ChatContent> chatContentList;

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

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<ChatContent> getChatContentList() {
        return chatContentList;
    }

    public void setChatContentList(List<ChatContent> chatContentList) {
        this.chatContentList = chatContentList;
    }

    @Override
    public String toString() {
        return "LatestPrivacyUser{" +
                "uid='" + uid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", canvases='" + canvases + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", chatContentList=" + chatContentList +
                '}';
    }
}
