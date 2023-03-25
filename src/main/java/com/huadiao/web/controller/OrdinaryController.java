package com.huadiao.web.controller;

import com.huadiao.utils.microspring.xmlresolver.MethodSignatureMap;
import com.huadiao.web.service.impl.OrdinaryServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @projectName 花凋
 * @author flowerwine
 * @description 基本请求处理, 包括未登录的用户
 * @version 1.1
 */
public class OrdinaryController {
    private OrdinaryServiceImpl ordinaryServiceImpl;

    /**
     * 获取用户状态, 是已登录状态还是未登录状态
     * @param request 请求对象
     * @return 返回处理结果, 如果登录返回 json 格式数据, 未登录返回 error 字符串
     */
    @MethodSignatureMap(methodSignature = "status", allowTourAccess = true)
    public String getUserStatus(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String myUserId = (String) session.getAttribute("userId");
        String myUid = (String) session.getAttribute("uid");
        Cookie[] cookies = request.getCookies();
        return ordinaryServiceImpl.getUserStatus(cookies, myUserId, myUid);
    }

    /**
     * 用户注册
     * @param request 请求对象
     * @return
     * 1. 如果用户存在返回 sameUsername
     * 2. 如果用户名不符合要求返回 wrongUsername
     * 3. 如果密码不符合要求返回 wrongPassword
     * 4. 如果验证码错误返回 wrongCode
     * 5. 如果用户名未填写返回 nullUsername
     * 6. 如果密码未填写返回 nullPassword
     * 7. 如果验证码未填写返回 nullCheckCode
     * 8. 注册成功返回 succeedRegister
     */
    @MethodSignatureMap(methodSignature = "register", allowTourAccess = true)
    public String registerNewUser(HttpServletRequest request, String username, String password, String checkCode) {
        HttpSession session = request.getSession();
        return ordinaryServiceImpl.registerNewUser(session, username, password, checkCode);
    }

    /**
     * 用户登录
     * @param request 请求对象
     * @param response 响应对象
     * @return 1. 如果用户不存在返回 noUser
     *         2. 登录成功返回 loginSucceed
     */
    @MethodSignatureMap(methodSignature = "login", allowTourAccess = true)
    public String userLogin(HttpServletRequest request, HttpServletResponse response, String username, String password) {
        HttpSession session = request.getSession();
        return ordinaryServiceImpl.userLogin(response, session, username, password);
    }

    /**
     * 用户登出
     * @param request 请求对象
     * @param response 响应对象
     * @return 操作成功返回 correct
     */
    @MethodSignatureMap(methodSignature = "loginOut")
    public String userLoginOut(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        return ordinaryServiceImpl.userLoginOut(session, response);
    }

}
