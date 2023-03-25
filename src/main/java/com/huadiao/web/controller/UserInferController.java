package com.huadiao.web.controller;

import com.huadiao.utils.microspring.xmlresolver.MethodSignatureMap;
import com.huadiao.web.service.impl.UserInferServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @projectName 花凋
 * @author flowerwine
 * @description 用户信息处理, 必须是登录的用户
 * @version 1.1
 */
public class UserInferController {
    private UserInferServiceImpl userInferServiceImpl;

    /**
     * 获取我的用户信息
     * @param request 请求对象
     * @return 返回我的用户信息
     */
    @MethodSignatureMap(methodSignature = "getUserInfer")
    public String getMyUserInfer(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return userInferServiceImpl.getMyUserInfer(myUid);
    }

    /**
     * 修改我的用户信息
     * @param request 请求对象
     * @param nickname 昵称
     * @param bornDate 出生日期
     * @param sex 性别
     * @param canvases 个人介绍
     * @param school 学校
     * @return 修改成功返回 userInferUpdateSucceed
     */
    @MethodSignatureMap(methodSignature = "updateUserInfer")
    public String updateMyUserInfer(HttpServletRequest request, String nickname, String bornDate, String sex, String canvases, String school) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return userInferServiceImpl.updateMyUserInfer(myUid, nickname, bornDate,  sex, canvases, school);
    }
}
