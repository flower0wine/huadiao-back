package com.huadiao.web.controller;

import com.huadiao.utils.microspring.xmlresolver.MethodSignatureMap;
import com.huadiao.web.service.impl.MessageServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @projectName 花凋
 * @author flowerwine
 * @description 用户消息处理, 必须是登录的用户
 * @version 1.1
 */
public class MessageController {
    private MessageServiceImpl messageServiceImpl;

    /**
     * 获取回复我的信息
     * @param request 请求对象
     * @return 获取成功返回 getReplyMeMessageSucceed
     */
    @MethodSignatureMap(methodSignature = "getReplyMessage")
    public String getReplyMeMessage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return messageServiceImpl.getReplyMeMessage(myUid);
    }

    /**
     * 获取系统消息
     * @param request 请求对象
     * @return 返回系统消息
     */
    @MethodSignatureMap(methodSignature = "getSystemMessage")
    public String getSystemMessage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return messageServiceImpl.getSystemMessage(myUid);
    }

    /**
     * 获取笔记评论被点赞的消息
     * @param request 请求对象
     * @return 返回被点赞消息
     */
    @MethodSignatureMap(methodSignature = "getNoteMarkLike")
    public String getNoteMarkMessage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return messageServiceImpl.getNoteMarkMessage(myUid);
    }

    /**
     * 获取私信对象及聊天消息
     * @param request 请求对象
     * @return 返回私信对象及聊天消息
     */
    @MethodSignatureMap(methodSignature = "getPrivateMessage")
    public String getPrivateUserAndMessage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return messageServiceImpl.getPrivateUserAndMessage(myUid);
    }
}
