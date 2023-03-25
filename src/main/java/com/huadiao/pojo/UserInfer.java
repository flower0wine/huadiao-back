package com.huadiao.pojo;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 28 18:08
 */
public class UserInfer {
    private String uid;
    private String userId;
    private String nickname;
    private String canvases;
    private String sex;
    private String bornDate;
    private String school;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "UserInfer{" +
                "uid='" + uid + '\'' +
                ", userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", canvases='" + canvases + '\'' +
                ", sex='" + sex + '\'' +
                ", bornDate='" + bornDate + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
