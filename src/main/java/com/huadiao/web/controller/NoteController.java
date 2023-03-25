package com.huadiao.web.controller;

import com.alibaba.fastjson.JSON;
import com.huadiao.utils.log.Loggers;
import com.huadiao.utils.microspring.xmlresolver.MethodSignatureMap;
import com.huadiao.web.service.impl.NoteServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @projectName 花凋
 * @author flowerwine
 * @description 用户笔记处理, 必须是登录的用户
 * @version 1.1
 */
public class NoteController implements Loggers {
    private NoteServiceImpl noteServiceImpl;

    /**
     * 获取笔记相关信息
     * @param request 请求对象
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 返回笔记
     */
    @MethodSignatureMap(methodSignature = "getNote")
    public String getNoteDetail(HttpServletRequest request, String authorUid, String noteId) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        String myUserId = (String) session.getAttribute("userId");
        return noteServiceImpl.getNoteDetail(authorUid, noteId, myUserId, myUid);
    }

    /**
     * 用户给笔记评论点赞, 如果存在则根据住状态来更新, 喜欢则取消喜欢, 取消喜欢则再次喜欢
     * @param request 请求对象
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @param likedUid 被点赞的用户的 id
     * @param rootMarkId 父评论的 id
     * @param subMarkId 子评论 id, 子评论 id 可能为 null
     * @return 喜欢或取消喜欢成功返回 noteMarkLikesSucceed
     */
    @MethodSignatureMap(methodSignature = "noteCommentLike")
    public String giveNoteMarkLikesOrCancel(HttpServletRequest request, String authorUid, String noteId, String likedUid, String rootMarkId, String subMarkId) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return noteServiceImpl.giveNoteMarkLikesOrCancel(noteId, authorUid, likedUid, myUid, rootMarkId, subMarkId);
    }

    /**
     * 用户取消给评论点赞, 如果存在则根据住状态来更新, 喜欢则取消喜欢, 取消喜欢则再次喜欢
     * @param request 请求对象
     * @param noteId 笔记 id
     * @param authorUid 作者 uid
     * @param likedUid 被点赞的用户的 id
     * @param rootMarkId 父评论的 id
     * @param subMarkId 子评论 id, 子评论 id 可能为 null
     * @return 不喜欢或取消不喜欢成功返回 noteMarkUnlikesSucceed
     */
    @MethodSignatureMap(methodSignature = "noteCommentUnlike")
    public String giveNoteMarkUnlikesOrCancel(HttpServletRequest request, String authorUid, String noteId, String likedUid, String rootMarkId, String subMarkId) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return noteServiceImpl.giveNoteMarkUnlikesOrCancel(noteId, authorUid, likedUid, myUid, rootMarkId, subMarkId);
    }

    /**
     * 新增笔记评论
     * @param request 请求对象
     * @param noteId 笔记 id
     * @param markedUid 被评论的人的 uid
     * @param markContent 笔记内容
     * @param rootMarkId 父评论 id
     * @param subMarkId 子评论 id, 子评论 id 可能为 null
     * @param authorUid 作者 uid
     * @return 添加成功返回 addNoteMarkSucceed
     */
    @MethodSignatureMap(methodSignature = "addNoteMark")
    public String addNewNoteMark(HttpServletRequest request, String noteId, String markedUid, String markContent, String rootMarkId, String subMarkId, String authorUid) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return noteServiceImpl.addNewNoteMark(noteId, markedUid, myUid, markContent, rootMarkId, subMarkId, authorUid);
    }

    /**
     * 新增笔记喜欢
     * @param request 请求对象
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 新增笔记成功返回 addNewNoteLikeSucceed
     */
    @MethodSignatureMap(methodSignature = "likeNote")
    public String addNewNoteLike(HttpServletRequest request, String authorUid, String noteId) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return noteServiceImpl.addNewNoteLike(authorUid, myUid, noteId);
    }

    /**
     * 取消笔记喜欢
     * @param request 请求对象
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 取消笔记成功返回 cancelNewNoteLikeSucceed
     */
    @MethodSignatureMap(methodSignature = "cancelLikeNote")
    public String cancelNewNoteLike(HttpServletRequest request, String authorUid, String noteId) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return noteServiceImpl.cancelNewNoteLike(authorUid, myUid, noteId);
    }

    /**
     * 新增笔记不喜欢
     * @param request 请求对象
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 不喜欢笔记成功返回 addNewNoteUnlikeSucceed
     */
    @MethodSignatureMap(methodSignature = "unlikeNote")
    public String addNewNoteUnlike(HttpServletRequest request, String authorUid, String noteId) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return noteServiceImpl.addNewNoteUnlike(authorUid, myUid, noteId);
    }

    /**
     * 取消笔记不喜欢
     * @param request 请求对象
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 取消不喜欢笔记成功返回 cancelNoteUnlikeSucceed
     */
    @MethodSignatureMap(methodSignature = "cancelUnlikeNote")
    public String cancelNoteUnlike(HttpServletRequest request, String authorUid, String noteId) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return noteServiceImpl.cancelNoteUnlike(authorUid, myUid, noteId);
    }

    /**
     * 获取指定用户的所有笔记
     * @param request 请求对象
     * @param authorUid 作者 uid
     * @return 返回所有笔记
     */
    @MethodSignatureMap(methodSignature = "allNote")
    public String getAllNotesByAuthorUid(HttpServletRequest request, String authorUid) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return noteServiceImpl.getAllNotesByAuthorUid(myUid, authorUid);
    }
}
