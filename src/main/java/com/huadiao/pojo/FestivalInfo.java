package com.huadiao.pojo;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Function
 * @Start_Time 2023 01 26 20:23
 */
public class FestivalInfo {
    private String festivalChineseName;
    private String festivalEnglishName;
    private String festivalDate;
    private String festivalEstablishTime;
    private String festivalOrigin;
    private String festivalMean;
    private String festivalDiet;
    private String festivalActivity;
    private Boolean isNextFestival;


    public String getFestivalChineseName() {
        return festivalChineseName;
    }

    public void setFestivalChineseName(String festivalChineseName) {
        this.festivalChineseName = festivalChineseName;
    }

    public String getFestivalEnglishName() {
        return festivalEnglishName;
    }

    public void setFestivalEnglishName(String festivalEnglishName) {
        this.festivalEnglishName = festivalEnglishName;
    }

    public String getFestivalDate() {
        return festivalDate;
    }

    public void setFestivalDate(String festivalDate) {
        this.festivalDate = festivalDate;
    }

    public String getFestivalEstablishTime() {
        return festivalEstablishTime;
    }

    public void setFestivalEstablishTime(String festivalEstablishTime) {
        this.festivalEstablishTime = festivalEstablishTime;
    }

    public String getFestivalOrigin() {
        return festivalOrigin;
    }

    public void setFestivalOrigin(String festivalOrigin) {
        this.festivalOrigin = festivalOrigin;
    }

    public String getFestivalMean() {
        return festivalMean;
    }

    public void setFestivalMean(String festivalMean) {
        this.festivalMean = festivalMean;
    }

    public String getFestivalDiet() {
        return festivalDiet;
    }

    public void setFestivalDiet(String festivalDiet) {
        this.festivalDiet = festivalDiet;
    }

    public String getFestivalActivity() {
        return festivalActivity;
    }

    public void setFestivalActivity(String festivalActivity) {
        this.festivalActivity = festivalActivity;
    }

    public Boolean getNextFestival() {
        return isNextFestival;
    }

    public void setNextFestival(Boolean nextFestival) {
        isNextFestival = nextFestival;
    }

    @Override
    public String toString() {
        return "FestivalInfo{" +
                "festivalChineseName='" + festivalChineseName + '\'' +
                ", festivalEnglishName='" + festivalEnglishName + '\'' +
                ", festivalDate='" + festivalDate + '\'' +
                ", festivalEstablishTime='" + festivalEstablishTime + '\'' +
                ", festivalOrigin='" + festivalOrigin + '\'' +
                ", festivalMean='" + festivalMean + '\'' +
                ", festivalDiet='" + festivalDiet + '\'' +
                ", festivalActivity='" + festivalActivity + '\'' +
                ", isNextFestival=" + isNextFestival +
                '}';
    }
}
