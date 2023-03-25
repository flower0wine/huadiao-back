package com.huadiao.web.service;

/**
 * @description 服务总接口
 */
public interface FollowsFansService extends Service {

    /**
     * 我和该用户互粉
     */
    int FOLLOW_AND_FAN = 2;

    /**
     * 该用户是我的关注, 并且他没有关注我
     */
    int FOLLOWING_UNFOLLOWED = 1;

    /**
     * 我被该用户关注, 但我没有关注他
     */
    int UNFOLLOWING_FOLLOWED = 2;

    /**
     * 确认关注
     */
    String CONFIRM_FOLLOW = "confirm";

    /**
     * 取消关注
     */
    String CANCEL_FOLLOW = "cancel";

    /**
     * 关注成功
     */
    String FOLLOW_SUCCEED = "followedSucceed";

    /**
     * 取消关注成功
     */
    String CANCEL_FOLLOW_SUCCEED = "cancelFollowedSucceed";

}
