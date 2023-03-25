package com.huadiao.pojo;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 10 15 12:49
 */
public class MessagePackage {
    private String sendUid;
    private String receiveUid;
    private String content;
    private String sendDate;

    public MessagePackage(String sendUid, String receiveUid, String content, String sendDate) {
        this.sendUid = sendUid;
        this.receiveUid = receiveUid;
        this.content = content;
        this.sendDate = sendDate;
    }

    public String getSendUid() {
        return sendUid;
    }

    public void setSendUid(String sendUid) {
        this.sendUid = sendUid;
    }

    public String getReceiveUid() {
        return receiveUid;
    }

    public void setReceiveUid(String receiveUid) {
        this.receiveUid = receiveUid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        return "MessagePackage{" +
                "sendUid='" + sendUid + '\'' +
                ", receiveUid='" + receiveUid + '\'' +
                ", content='" + content + '\'' +
                ", sendDate='" + sendDate + '\'' +
                '}';
    }
}
