package com.huadiao.web.controller;

import com.huadiao.utils.microspring.xmlresolver.MethodSignatureMap;
import com.huadiao.web.service.impl.HomepageServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @projectName 花凋
 * @author flowerwine
 * @description 用户个人主页处理, 必须是登录的用户
 * @version 1.1
 */
public class HomepageController {
    private HomepageServiceImpl homepageServiceImpl;

    /**
     * 获取指定用户的个人主页信息
     * @param request 请求对象
     * @param uid 指定的 uid
     * @return 返回指定的个人主页信息
     */
    @MethodSignatureMap(methodSignature = "getHomepageInfer")
    public String getHomepageInformation(HttpServletRequest request, String uid) {
        HttpSession httpSession = request.getSession();
        String userId = (String) httpSession.getAttribute("userId");
        String myUid = (String) httpSession.getAttribute("uid");
        return homepageServiceImpl.getHomepageInferByUid(userId, myUid, uid);
    }

    /**
     * 修改个人主页背景
     * @param request 请求对象
     * @return 修改成功返回 modifyHomepageBackgroundSucceed
     */
    @MethodSignatureMap(methodSignature = "homepageBackground")
    public String modifyMyHomepageBackground(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return homepageServiceImpl.modifyMyHomepageBackground(request, myUid);
    }

    /**
     * 修改用户头像
     * @param request 请求对象
     * @return 修改成功返回 modifyUserAvatarSucceed
     * @throws Exception 可能抛出异常
     */
    @MethodSignatureMap(methodSignature = "userAvatar")
    public String modifyMyUserAvatar(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return homepageServiceImpl.modifyMyUserAvatar(request, myUid);
    }
}
