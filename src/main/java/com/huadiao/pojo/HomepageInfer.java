package com.huadiao.pojo;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 29 22:56
 */
public class HomepageInfer {
    private String userId;
    private String uid;
    private String userAvatar;
    private String pageBackground;
    private String nickname;
    private String bornDate;
    private String sex;
    private String canvases;
    private String school;
    private Integer followNumber;
    private Integer fansNumber;
    private Integer starNumber;
    private Integer likeNumber;
    private Integer fanjusNumber;
    private Integer notesNumber;
    private Integer visitNumber;
    private Boolean me;
    private Boolean following;
    private Boolean followed;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getPageBackground() {
        return pageBackground;
    }

    public void setPageBackground(String pageBackground) {
        this.pageBackground = pageBackground;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCanvases() {
        return canvases;
    }

    public void setCanvases(String canvases) {
        this.canvases = canvases;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(Integer followNumber) {
        this.followNumber = followNumber;
    }

    public Integer getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(Integer fansNumber) {
        this.fansNumber = fansNumber;
    }

    public Integer getStarNumber() {
        return starNumber;
    }

    public void setStarNumber(Integer starNumber) {
        this.starNumber = starNumber;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getFanjusNumber() {
        return fanjusNumber;
    }

    public void setFanjusNumber(Integer fanjusNumber) {
        this.fanjusNumber = fanjusNumber;
    }

    public Integer getNotesNumber() {
        return notesNumber;
    }

    public void setNotesNumber(Integer notesNumber) {
        this.notesNumber = notesNumber;
    }

    public Integer getVisitNumber() {
        return visitNumber;
    }

    public void setVisitNumber(Integer visitNumber) {
        this.visitNumber = visitNumber;
    }

    public Boolean getMe() {
        return me;
    }

    public void setMe(Boolean me) {
        this.me = me;
    }

    public Boolean getFollowing() {
        return following;
    }

    public void setFollowing(Boolean following) {
        this.following = following;
    }

    public Boolean getFollowed() {
        return followed;
    }

    public void setFollowed(Boolean followed) {
        this.followed = followed;
    }

    @Override
    public String toString() {
        return "HomepageInfer{" +
                "userId='" + userId + '\'' +
                ", uid='" + uid + '\'' +
                ", userAvatar='" + userAvatar + '\'' +
                ", pageBackground='" + pageBackground + '\'' +
                ", nickname='" + nickname + '\'' +
                ", bornDate='" + bornDate + '\'' +
                ", sex='" + sex + '\'' +
                ", canvases='" + canvases + '\'' +
                ", school='" + school + '\'' +
                ", followNumber=" + followNumber +
                ", fansNumber=" + fansNumber +
                ", starNumber=" + starNumber +
                ", likeNumber=" + likeNumber +
                ", fanjusNumber=" + fanjusNumber +
                ", notesNumber=" + notesNumber +
                ", visitNumber=" + visitNumber +
                ", me=" + me +
                ", following=" + following +
                ", followed=" + followed +
                '}';
    }

}
