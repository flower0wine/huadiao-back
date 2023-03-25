package com.huadiao.pojo;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 09 01 21:48
 */
public class MessageSettings {
    private String uid;
    private String userAvatar;
    private String nickname;
    private String messageRemindStatus;
    private String messageReplyStatus;
    private String messageLikeStatus;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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

    public String getMessageRemindStatus() {
        return messageRemindStatus;
    }

    public void setMessageRemindStatus(String messageRemindStatus) {
        this.messageRemindStatus = messageRemindStatus;
    }

    public String getMessageReplyStatus() {
        return messageReplyStatus;
    }

    public void setMessageReplyStatus(String messageReplyStatus) {
        this.messageReplyStatus = messageReplyStatus;
    }

    public String getMessageLikeStatus() {
        return messageLikeStatus;
    }

    public void setMessageLikeStatus(String messageLikeStatus) {
        this.messageLikeStatus = messageLikeStatus;
    }

    @Override
    public String toString() {
        return "MessageSettings{" +
                "uid='" + uid + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", messageRemindStatus='" + messageRemindStatus + '\'' +
                ", messageReplyStatus='" + messageReplyStatus + '\'' +
                ", messageLikeStatus='" + messageLikeStatus + '\'' +
                '}';
    }
}
