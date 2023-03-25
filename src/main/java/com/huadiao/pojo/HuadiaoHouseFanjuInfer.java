package com.huadiao.pojo;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description 番剧信息
 */
public class HuadiaoHouseFanjuInfer {
    private String fanjuName;
    private String fanjuCover;
    private String buildDate;
    private String signId;

    public String getFanjuName() {
        return fanjuName;
    }

    public void setFanjuName(String fanjuName) {
        this.fanjuName = fanjuName;
    }

    public String getFanjuCover() {
        return fanjuCover;
    }

    public void setFanjuCover(String fanjuCover) {
        this.fanjuCover = fanjuCover;
    }

    public String getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(String buildDate) {
        this.buildDate = buildDate;
    }

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    @Override
    public String toString() {
        return "HuadiaoHouseFanjuInfer{" +
                "fanjuName='" + fanjuName + '\'' +
                ", fanjuCover='" + fanjuCover + '\'' +
                ", buildDate='" + buildDate + '\'' +
                ", signId='" + signId + '\'' +
                '}';
    }
}
