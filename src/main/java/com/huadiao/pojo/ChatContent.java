package com.huadiao.pojo;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 09 22 22:55
 */
public class ChatContent {
    private String sessionContent;
    private String sendDate;
    private String sendUid;

    public String getSessionContent() {
        return sessionContent;
    }

    public void setSessionContent(String sessionContent) {
        this.sessionContent = sessionContent;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendUid() {
        return sendUid;
    }

    public void setSendUid(String sendUid) {
        this.sendUid = sendUid;
    }

    @Override
    public String toString() {
        return "ChatContent{" +
                "sessionContent='" + sessionContent + '\'' +
                ", sendDate='" + sendDate + '\'' +
                ", sendUid='" + sendUid + '\'' +
                '}';
    }
}
