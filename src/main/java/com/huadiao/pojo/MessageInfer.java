package com.huadiao.pojo;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 30 11:14
 */
public class MessageInfer {
    private List<ReplyMeMessage> replyMeMessageList;
    private List<SystemMessage> systemMessageList;
    private List<NoteMarkLikeMessage> likeMessageList;
    private List<LatestPrivacyUser> latestPrivacyUserList;
    private MessageSettings messageSettings;

    public List<ReplyMeMessage> getReplyMeMessageList() {
        return replyMeMessageList;
    }

    public void setReplyMeMessageList(List<ReplyMeMessage> replyMeMessageList) {
        this.replyMeMessageList = replyMeMessageList;
    }

    public List<SystemMessage> getSystemMessageList() {
        return systemMessageList;
    }

    public void setSystemMessageList(List<SystemMessage> systemMessageList) {
        this.systemMessageList = systemMessageList;
    }

    public List<NoteMarkLikeMessage> getLikeMessageList() {
        return likeMessageList;
    }

    public void setLikeMessageList(List<NoteMarkLikeMessage> likeMessageList) {
        this.likeMessageList = likeMessageList;
    }

    public List<LatestPrivacyUser> getLatestPrivacyUserList() {
        return latestPrivacyUserList;
    }

    public void setLatestPrivacyUserList(List<LatestPrivacyUser> latestPrivacyUserList) {
        this.latestPrivacyUserList = latestPrivacyUserList;
    }

    public MessageSettings getMessageSettings() {
        return messageSettings;
    }

    public void setMessageSettings(MessageSettings messageSettings) {
        this.messageSettings = messageSettings;
    }

    @Override
    public String toString() {
        return "MessageInfer{" +
                "replyMeMessageList=" + replyMeMessageList +
                ", systemMessageList=" + systemMessageList +
                ", likeMessageList=" + likeMessageList +
                ", latestPrivacyUserList=" + latestPrivacyUserList +
                ", messageSettings=" + messageSettings +
                '}';
    }
}
