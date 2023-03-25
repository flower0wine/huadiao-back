package com.huadiao.web.controller;

import com.huadiao.utils.microspring.xmlresolver.MethodSignatureMap;
import com.huadiao.web.service.impl.HistoryServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description
 */
public class HistoryController {
    private HistoryServiceImpl historyServiceImpl;

    /**
     * 获取笔记历史记录
     * @param request 请求对象
     * @return 返回笔记历史记录
     */
    @MethodSignatureMap(methodSignature = "getNoteHistory")
    public String getUserAllNotesHistories(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return historyServiceImpl.getUserAllNotesHistories(myUid);
    }

    /**
     * 获取番剧馆历史记录
     * @param request 请求对象
     * @return 返回番剧馆历史记录
     */
    @MethodSignatureMap(methodSignature = "getFanjuHistory")
    public String getUserAllFanjuHistories(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return historyServiceImpl.getUserAllFanjuHistories(myUid);
    }

    /**
     * 删除笔记历史记录
     * @param request 请求对象
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 删除成功返回 noteHistoryDeleteSucceed
     */
    @MethodSignatureMap(methodSignature = "deleteNoteHistory")
    public String deleteNoteHistory(HttpServletRequest request, String authorUid, String noteId) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return historyServiceImpl.deleteNoteHistory(authorUid, myUid, noteId);
    }

    /**
     * 删除番剧馆历史记录
     * @param request 请求对象
     * @param authorUid 作者 uid
     * @return 删除成功返回 fanjuHistoryDeleteSucceed
     */
    @MethodSignatureMap(methodSignature = "deleteFanjuHistory")
    public String deleteFanjuHistory(HttpServletRequest request, String authorUid) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return historyServiceImpl.deleteFanjuHistory(authorUid, myUid);
    }
}
