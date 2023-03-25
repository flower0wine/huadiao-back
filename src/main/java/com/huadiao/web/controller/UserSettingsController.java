package com.huadiao.web.controller;

import com.huadiao.utils.microspring.xmlresolver.MethodSignatureMap;
import com.huadiao.web.service.impl.UserSettingsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description
 */
public class UserSettingsController {
    private UserSettingsServiceImpl userSettingsServiceImpl;


    /**
     * 获取我的用户设置
     * @param request 请求对象
     * @return 返回用户设置
     */
    @MethodSignatureMap(methodSignature = "getUserSettings")
    public String getMyUserSettings(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return userSettingsServiceImpl.getMyUserSettings(myUid);
    }

    /**
     * 获取我的消息设置
     * @param request 请求对象
     * @return 返回我的消息设置
     */
    @MethodSignatureMap(methodSignature = "getMessageSettings")
    public String getMyMessageSettings(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return userSettingsServiceImpl.getMyMessageSettings(myUid);
     }

    /**
     * 更改用户设置
     * @param request 请求对象
     * @param option 要更改的选项
     * @return 修改成功返回 modifyUserSettingsSucceed
     */
    @MethodSignatureMap(methodSignature = "modifyUserSettings")
    public String modifyMyUserSettings(HttpServletRequest request, String option) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return userSettingsServiceImpl.modifyMyUserSettings(myUid, option);
    }
}
