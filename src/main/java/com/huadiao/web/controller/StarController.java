package com.huadiao.web.controller;

import com.huadiao.utils.microspring.xmlresolver.MethodSignatureMap;
import com.huadiao.web.service.impl.StarServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description
 */
public class StarController {
    private StarServiceImpl starServiceImpl;

    /**
     * 新增笔记收藏, 也可能是取消收藏后再次收藏
     * @param request 请求对象
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 收藏成功返回 addNewNoteStarSucceed
     */
    @MethodSignatureMap(methodSignature = "starNote")
    public String addNewNoteStar(HttpServletRequest request, String authorUid, String noteId) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return starServiceImpl.addNewNoteStar(authorUid, myUid, noteId);
    }

    /**
     * 取消笔记收藏
     * @param request 请求对象
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 取消收藏成功返回 cancelNewNoteStar
     */
    @MethodSignatureMap(methodSignature = "cancelNoteStar")
    public String cancelNewNoteStar(HttpServletRequest request, String authorUid, String noteId) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return starServiceImpl.cancelNewNoteStar(authorUid, myUid, noteId);
    }

    /**
     * 获取我收藏的所有笔记
     * @param request 请求对象
     * @return 返回所有收藏的笔记
     */
    @MethodSignatureMap(methodSignature = "getNoteStar")
    public String getUserAllNoteStars(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return starServiceImpl.getUserAllNoteStars(myUid);
    }
}
