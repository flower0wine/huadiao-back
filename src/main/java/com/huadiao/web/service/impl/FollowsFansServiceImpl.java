package com.huadiao.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.FansInfer;
import com.huadiao.pojo.FollowFansInfer;
import com.huadiao.pojo.FollowsInfer;
import com.huadiao.utils.log.Loggers;
import com.huadiao.web.dao.FollowsFansDAO;
import com.huadiao.web.dao.MessageDAO;
import com.huadiao.web.dao.UserDAO;
import com.huadiao.web.service.FollowsFansService;

import java.util.List;

/**
 * @projectName 花凋
 * @author flowerwine
 * @description 业务处理, 逻辑处理, 用户关注与粉丝
 * @version 1.1
 */
public class FollowsFansServiceImpl implements FollowsFansService, Loggers {
    private FollowsFansDAO followsFansDAO;
    private UserDAO userDAO;
    private MessageDAO messageDAO;

    /**
     * 更改我和别人的关系
     * @param myUid 我的 uid
     * @param myUserId 我的 userId
     * @param otherUid 别人的 uid
     * @param followStatus 客户端发送过来的, 关注或取消关注密钥, confirm 确认关注, cancel 取消关注
     * @return 返回提示信息, followedSucceed 关注成功, cancelFollowedSucceed 取消关注成功
     */
    public String changeFollowRelation(String myUid, String myUserId, String otherUid, String followStatus) {
        String otherUserId = userDAO.selectUserIdByUid(otherUid);
        LOGGER.debug("userId, uid 分别为 {}, {} 的用户企图修改与 userId, uid 分别为 {}, {} 的用户的关注状态, 修改关键字为 {}", myUserId, myUid, otherUserId, otherUid, followStatus);
        // 如果其他人的 userId 不为 null, 并且不是对自己进行关注
        if(otherUserId != null && !myUid.equals(otherUid)) {
            // 关注别人
            if(CONFIRM_FOLLOW.equals(followStatus)) {
                followsFansDAO.insertRelationByBothUserIdAndUid(myUid, myUserId, otherUid, otherUserId);
                return FOLLOW_SUCCEED;
                // 取消关注
            } else if(CANCEL_FOLLOW.equals(followStatus)) {
                followsFansDAO.updateRelationByBothUserId(myUserId, otherUserId);
                return CANCEL_FOLLOW_SUCCEED;
            }
        }
        LOGGER.debug("userId, uid 为 {}, {}, 对 userId, uid 分别为 {}, {} 的用户的关注状态未修改成功！ followStatus 关键字为 {}", myUserId, myUid, otherUserId, otherUid, followStatus);
        return ERROR;
    }

    /**
     * 获取指定用户的关注与粉丝
     * @param myUid 我的 uid
     * @param viewedUid 其他人的 uid
     * @return 返回关注与粉丝
     */
    public String getUserFollowsAndFans(String myUid, String viewedUid) {
        LOGGER.debug("uid 为 {} 的用户尝试获取 uid 为 {} 的用户的关注与粉丝", myUid, viewedUid);
        FollowFansInfer followFansInfer = new FollowFansInfer();
        List<FollowsInfer> follows = followsFansDAO.selectFollowsByUid(viewedUid);
        List<FansInfer> fans = followsFansDAO.selectFansByUid(viewedUid);
        boolean isMe = myUid.equals(viewedUid);

        followFansInfer.setFollows(follows);
        followFansInfer.setFans(fans);
        followFansInfer.setMe(isMe);
        return JSON.toJSONString(followFansInfer);
    }
}
