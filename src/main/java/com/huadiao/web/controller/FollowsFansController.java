package com.huadiao.web.controller;

import com.huadiao.utils.microspring.xmlresolver.MethodSignatureMap;
import com.huadiao.web.service.impl.FollowsFansServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @projectName 花凋
 * @author flowerwine
 * @description 用户关注与粉丝处理, 必须是登录的用户
 * @version 1.1
 */
public class FollowsFansController {
    private FollowsFansServiceImpl followsFansServiceImpl;

    /**
     * 更改我和别人的关系
     * @param uid 别人的 uid
     * @param followStatus 客户端发送过来的, 关注或取消关注密钥, confirm 确认关注, cancel 取消关注
     * @return 返回提示信息, followedSucceed 关注成功, cancelFollowedSucceed 取消关注成功, 未知错误返回 error
     */
    @MethodSignatureMap(methodSignature = "changeFollowStatus")
    public String changeFollowRelation(HttpServletRequest request, String uid, String followStatus) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        String myUserId = (String) session.getAttribute("userId");
        return followsFansServiceImpl.changeFollowRelation(myUid, myUserId, uid, followStatus);
    }

    /**
     * 获取指定用户的关注与粉丝
     * @param request 请求对象
     * @param viewedUid 被查看的用户 uid
     * @return 返回关注与粉丝
     */
    @MethodSignatureMap(methodSignature = "getFollowsFans")
    public String getUserFollowsAndFans(HttpServletRequest request, String viewedUid) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return followsFansServiceImpl.getUserFollowsAndFans(myUid, viewedUid);
    }
}
