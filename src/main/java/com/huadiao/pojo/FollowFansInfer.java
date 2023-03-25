package com.huadiao.pojo;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 05 12:30
 */
public class FollowFansInfer {
    List<FollowsInfer> follows;
    List<FansInfer> fans;
    /**
     * 是否为本人
     */
    boolean isMe;

    public List<FollowsInfer> getFollows() {
        return follows;
    }

    public void setFollows(List<FollowsInfer> follows) {
        this.follows = follows;
    }

    public List<FansInfer> getFans() {
        return fans;
    }

    public void setFans(List<FansInfer> fans) {
        this.fans = fans;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }

    @Override
    public String toString() {
        return "FollowFansInfer{" +
                "follows=" + follows +
                ", fans=" + fans +
                ", isMe=" + isMe +
                '}';
    }
}
