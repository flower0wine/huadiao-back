package com.huadiao.pojo;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 01 17:04
 */
public class HuadiaoHouseInfer {
    /**
     * 被访问的用户信息
     */
    private String authorUid;
    private String authorUserId;
    private String huadiaoHouseTitleColor;
    private String huadiaoHouseTitleBackgroundColor;
    private String huadiaoHouseBackground;
    private String huadiaoHouseForeground;
    private String huadiaoHouseCoverBorder;
    private String buildingCardBackground;
    private List<HuadiaoHouseFanjuInfer> huadiaoHouseFanjuInferList;
    /**
     * 访问者的用户信息
     */
    private String myUid;
    private String myUserId;
    /**
     * 是否为本人
     */
    private boolean isAuthor;

    public HuadiaoHouseInfer() {
    }

    public HuadiaoHouseInfer(List<HuadiaoHouseFanjuInfer> huadiaoHouseFanjuInferList) {
        this.huadiaoHouseFanjuInferList = huadiaoHouseFanjuInferList;
    }

    public String getAuthorUid() {
        return authorUid;
    }

    public void setAuthorUid(String authorUid) {
        this.authorUid = authorUid;
    }

    public String getAuthorUserId() {
        return authorUserId;
    }

    public void setAuthorUserId(String authorUserId) {
        this.authorUserId = authorUserId;
    }

    public String getHuadiaoHouseTitleColor() {
        return huadiaoHouseTitleColor;
    }

    public void setHuadiaoHouseTitleColor(String huadiaoHouseTitleColor) {
        this.huadiaoHouseTitleColor = huadiaoHouseTitleColor;
    }

    public String getHuadiaoHouseTitleBackgroundColor() {
        return huadiaoHouseTitleBackgroundColor;
    }

    public void setHuadiaoHouseTitleBackgroundColor(String huadiaoHouseTitleBackgroundColor) {
        this.huadiaoHouseTitleBackgroundColor = huadiaoHouseTitleBackgroundColor;
    }

    public String getHuadiaoHouseBackground() {
        return huadiaoHouseBackground;
    }

    public void setHuadiaoHouseBackground(String huadiaoHouseBackground) {
        this.huadiaoHouseBackground = huadiaoHouseBackground;
    }

    public String getHuadiaoHouseForeground() {
        return huadiaoHouseForeground;
    }

    public void setHuadiaoHouseForeground(String huadiaoHouseForeground) {
        this.huadiaoHouseForeground = huadiaoHouseForeground;
    }

    public String getHuadiaoHouseCoverBorder() {
        return huadiaoHouseCoverBorder;
    }

    public void setHuadiaoHouseCoverBorder(String huadiaoHouseCoverBorder) {
        this.huadiaoHouseCoverBorder = huadiaoHouseCoverBorder;
    }

    public String getBuildingCardBackground() {
        return buildingCardBackground;
    }

    public void setBuildingCardBackground(String buildingCardBackground) {
        this.buildingCardBackground = buildingCardBackground;
    }

    public List<HuadiaoHouseFanjuInfer> getHuadiaoHouseFanjuInferList() {
        return huadiaoHouseFanjuInferList;
    }

    public void setHuadiaoHouseFanjuInferList(List<HuadiaoHouseFanjuInfer> huadiaoHouseFanjuInferList) {
        this.huadiaoHouseFanjuInferList = huadiaoHouseFanjuInferList;
    }

    public String getMyUid() {
        return myUid;
    }

    public void setMyUid(String myUid) {
        this.myUid = myUid;
    }

    public String getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId;
    }

    public boolean isAuthor() {
        return isAuthor;
    }

    public void setAuthor(boolean author) {
        isAuthor = author;
    }

    @Override
    public String toString() {
        return "HuadiaoHouseInfer{" +
                "authorUid='" + authorUid + '\'' +
                ", authorUserId='" + authorUserId + '\'' +
                ", huadiaoHouseTitleColor='" + huadiaoHouseTitleColor + '\'' +
                ", huadiaoHouseTitleBackgroundColor='" + huadiaoHouseTitleBackgroundColor + '\'' +
                ", huadiaoHouseBackground='" + huadiaoHouseBackground + '\'' +
                ", huadiaoHouseForeground='" + huadiaoHouseForeground + '\'' +
                ", huadiaoHouseCoverBorder='" + huadiaoHouseCoverBorder + '\'' +
                ", buildingCardBackground='" + buildingCardBackground + '\'' +
                ", huadiaoHouseFanjuInferList=" + huadiaoHouseFanjuInferList +
                ", myUid='" + myUid + '\'' +
                ", myUserId='" + myUserId + '\'' +
                ", isAuthor=" + isAuthor +
                '}';
    }
}
