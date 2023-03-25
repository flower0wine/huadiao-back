package com.huadiao.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.Note;
import com.huadiao.pojo.NoteDetail;
import com.huadiao.pojo.Notes;
import com.huadiao.pojo.RootMark;
import com.huadiao.utils.log.Loggers;
import com.huadiao.web.dao.*;
import com.huadiao.web.service.AbstractFollowsFansService;
import com.huadiao.web.service.NoteService;

import java.util.List;

/**
 * @projectName 花凋
 * @author flowerwine
 * @description 业务层面, 逻辑处理, 用户笔记
 * @version 1.1
 */
public class NoteServiceImpl implements NoteService, Loggers {
    private NoteDAO noteDAO;
    private UserInferDAO userInferDAO;
    private FollowsFansDAO followsFansDAO;
    private HomepageInferDAO homepageInferDAO;
    private HistoryDAO historyDAO;

    /**
     * 新增用户笔记
     * @param uid 用户 uid
     * @param userId 用户 userId
     * @param noteTitle 用户笔记标题
     * @param noteContent 用户笔记 HTML 内容
     * @param noteAbstract 用户笔记摘要
     * @return 上传成功返回 uploadSucceed
     */
    public String buildNewNote(String uid, String userId, String noteTitle, String noteContent, String noteAbstract) {
        noteDAO.insertNewNote(uid, userId, noteTitle, noteContent, noteAbstract);
        LOGGER.debug("userId, uid 分别为 {}, {} 的用户创建并提交了一个笔记！", userId, uid);
        return NOTE_UPLOAD_SUCCEED;
    }

    /**
     * 获取笔记相关信息
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @param myUserId 我的 userId
     * @param myUid 我的 uid
     * @return 返回笔记
     */
    public String getNoteDetail(String authorUid, String noteId, String myUserId, String myUid) {
        NoteDetail noteDetail = noteDAO.selectBasicNoteInfer(authorUid, noteId);
        // 获取我和作者的头像链接
        String myAvatar = homepageInferDAO.selectUserAvatar(myUid);
        String authorAvatar = homepageInferDAO.selectUserAvatar(authorUid);
        // 获取我和作者的昵称
        String myNickName = userInferDAO.selectNicknameByUid(myUid);
        String authorNickName = userInferDAO.selectNicknameByUid(authorUid);
        // 获取笔记浏览量, 点赞数, 收藏数, 评论数
        String views = noteDAO.selectNoteViews(authorUid, noteId);
        String likeNumber = noteDAO.selectNoteLikeNumber(authorUid, noteId);
        String starNumber = noteDAO.selectNoteStarNumber(authorUid, noteId);
        String markNumber = noteDAO.selectNoteMarkNumber(authorUid, noteId);
        // 获取我对该笔记的态度, 即喜欢, 不喜欢, 收藏
        String like = noteDAO.selectIsLikeNote(myUid, noteId);
        String unlike = noteDAO.selectIsUnlikeNote(myUid, noteId);
        String star = noteDAO.selectIsStarNote(myUid, noteId);
        // 获取我与作者的关系
        List<Integer> relationList = followsFansDAO.selectRelationByBothUserId(myUserId, noteDetail.getAuthorUserId());
        boolean[] relationArray = AbstractFollowsFansService.getRelation(relationList);
        // 获取笔记评论
        List<RootMark> noteMarks = noteDAO.selectNoteMarks(noteId, myUid);
        // 添加笔记浏览记录
        historyDAO.insertViewedNoteHistoryByUid(authorUid, myUid, noteId);

        // 设置昵称
        noteDetail.setAuthorNickname(authorNickName);
        noteDetail.setViewerNickname(myNickName);
        // 设置头像链接
        noteDetail.setAuthorUserAvatar(authorAvatar);
        noteDetail.setViewerUserAvatar(myAvatar);
        // 设置我对作者的态度
        noteDetail.setMyLike(myUid.equals(like));
        noteDetail.setMyUnlike(myUid.equals(unlike));
        noteDetail.setMyStar(myUid.equals(star));
        // 设置笔记的浏览量, 喜欢, 收藏数, 评论数
        noteDetail.setLikeNumber(likeNumber);
        noteDetail.setStarNumber(starNumber);
        noteDetail.setMarkNumber(markNumber);
        noteDetail.setViewNumber(views);
        // 设置我与作者的关系
        noteDetail.setFollowing(relationArray[0]);
        noteDetail.setFollowed(relationArray[1]);
        noteDetail.setMe(myUid.equals(authorUid));
        // 设置笔记评论
        noteDetail.setRootMarkList(noteMarks);
        // 设置我的剩余信息
        noteDetail.setViewerUid(myUid);
        LOGGER.debug("userId, uid 分别为 {}, {} 的用户成功获取 userId, uid 分别为 {}, {} 的用户的一个 noteId 为 {} 的笔记！", myUserId, myUid, noteDetail.getAuthorUserId(), authorUid, noteId);
        return JSON.toJSONString(noteDetail);
    }

    /**
     * 用户给笔记评论点赞, 如果存在则根据住状态来更新, 喜欢则取消喜欢, 取消喜欢则再次喜欢
     * @param noteId 笔记 id
     * @param authorUid 作者 uid
     * @param likedUid 评论被点赞的用户 userId
     * @param myUid 我的 uid
     * @param rootMarkId 父评论 id
     * @param subMarkId 子评论 id, 子评论 id 可能为 null
     * @return 点赞或取消点赞成功返回 noteMarkLikesSucceed
     */
    public String giveNoteMarkLikesOrCancel(String noteId, String authorUid, String likedUid, String myUid, String rootMarkId, String subMarkId) {
        noteDAO.insertNoteMarkLikeOrUpdate(noteId, authorUid, likedUid, myUid, rootMarkId, subMarkId);
        LOGGER.debug("uid 分别为 {} 的用户成功修改了 uid 分别为 {} 的用户的一个 noteId 为 {} 的笔记中的 rootMarkId 为 {} 并且 subMarkId 为 {} 的评论喜欢状态！", myUid, likedUid, noteId, rootMarkId, subMarkId);
        return NOTE_MARK_LIKES_SUCCEED;
    }

    /**
     * 用户给评论不喜欢, 如果存在则根据住状态来更新, 不喜欢则取消不喜欢, 取消不喜欢则再次不喜欢
     * @param noteId 笔记 id
     * @param myUid 我的 uid
     * @param authorUid 作者 uid
     * @param unlikedUid 被不喜欢的用户的 uid
     * @param rootMarkId 父评论
     * @param subMarkId 子评论, 子评论可能为 null
     * @return 不喜欢或取消不喜欢成功返回 noteMarkUnlikesSucceed
     */
    public String giveNoteMarkUnlikesOrCancel(String noteId, String authorUid, String unlikedUid, String myUid, String rootMarkId, String subMarkId) {
        noteDAO.insertNoteMarkUnlikeOrUpdate(noteId, authorUid, myUid, unlikedUid, rootMarkId, subMarkId);
        LOGGER.debug("uid 为 {} 的用户成功修改了 uid 为 {} 的用户的一个 noteId 为 {} 的笔记中的 rootMarkId 为 {} 并且 subMarkId 为 {} 的评论的不喜欢状态！", myUid, unlikedUid, noteId, rootMarkId, subMarkId);
        return NOTE_MARK_UNLIKES_SUCCEED;
    }

    /**
     * 新增笔记评论, 子评论 id 为 null 时则说明该评论时回复作者
     * @param noteId 笔记 id
     * @param markedUid 被回复的人的 uid
     * @param myUid 我的 uid
     * @param markContent 评论内容
     * @param rootMarkId 父评论 id
     * @param subMarkId 子评论 id, 可能为 null
     * @param authorUid 作者 uid
     */
    public String addNewNoteMark(String noteId, String markedUid, String myUid, String markContent, String rootMarkId, String subMarkId, String authorUid) {
        // 没有子评论的父评论回复的都是作者
        if(subMarkId == null) {
            markedUid = authorUid;
        }
        noteDAO.insertNoteMark(noteId, markedUid, myUid, markContent, rootMarkId, subMarkId, authorUid);
        LOGGER.debug("uid 为 {} 的用户成功为 uid 为 {} 的用户的一个 noteId 为 {} 的笔记添加了一条 rootMarkId 为 {} 并且 subMarkId 为 {} 的评论", myUid, authorUid, noteId, rootMarkId, subMarkId);
        return ADD_NOTE_MARK_SUCCEED;
    }

    /**
     * 新增笔记点赞
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     * @return 新增笔记成功返回 addNewNoteLikeSucceed
     */
    public String addNewNoteLike(String authorUid, String myUid, String noteId) {
        noteDAO.insertNoteLike(authorUid, myUid, noteId);
        LOGGER.debug("uid 为 {} 的用户成功给了 uid 为 {} 的用户的一个 noteId 为 {} 的笔记一个赞！", myUid, authorUid, noteId);
        return ADD_NEW_NOTE_LIKE_SUCCEED;
    }


    /**
     * 取消笔记点赞
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     * @return 取消笔记成功返回 cancelNewNoteLikeSucceed
     */
    public String cancelNewNoteLike(String authorUid, String myUid, String noteId) {
        noteDAO.cancelNoteLike(authorUid, myUid, noteId);
        LOGGER.debug("uid 为 {} 的用户取消了 uid 为 {} 的用户的一个 noteId 为 {} 的笔记的点赞！", myUid, authorUid, noteId);
        return CANCEL_NEW_NOTE_LIKE_SUCCEED;
    }

    /**
     * 新增笔记不喜欢
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     * @return 不喜欢笔记成功后返回 addNewNoteUnlikeSucceed
     */
    public String addNewNoteUnlike(String authorUid, String myUid, String noteId) {
        noteDAO.insertNoteUnlike(authorUid, myUid, noteId);
        LOGGER.debug("uid 为 {} 的用户成功给了 uid 为 {} 的用户的一个 noteId 为 {} 的笔记不喜欢！", myUid, authorUid, noteId);
        return ADD_NEW_NOTE_UNLIKE_SUCCEED;
    }

    /**
     * 取消笔记不喜欢
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     * @return 取消不喜欢笔记成功
     */
    public String cancelNoteUnlike(String authorUid, String myUid, String noteId) {
        noteDAO.cancelNoteUnlike(authorUid, myUid, noteId);
        LOGGER.debug("uid 为 {} 的用户取消了给 uid 为 {} 的用户的一个 noteId 为 {} 的笔记的不喜欢！", myUid, authorUid, noteId);
        return CANCEL_NOTE_UNLIKE_SUCCEED;
    }

    /**
     * 获取指定用户的所有笔记
     * @param myUid 我的 uid
     * @param authorUid 作者 uid
     * @return 返回笔记
     */
    public String getAllNotesByAuthorUid(String myUid, String authorUid) {
        // 获取数据
        List<Note> noteList = noteDAO.selectAllNoteByAuthorUid(authorUid);
        String authorUserAvatar = homepageInferDAO.selectUserAvatar(authorUid);
        String authorNickname = userInferDAO.selectNicknameByUid(authorUid);

        // 填充数据
        Notes notes = new Notes();
        notes.setNoteList(noteList);
        notes.setAuthorNickname(authorNickname);
        notes.setAuthorUserAvatar(authorUserAvatar);
        LOGGER.debug("uid 为 {} 的用户成功获取了 uid 为 {} 的用户的所有笔记", myUid, authorUid);
        return JSON.toJSONString(notes);
    }

    /**
     * 新增笔记评论
     * @param noteId 笔记 id
     * @param markedUid 被评论的用户的 uid
     * @param markUid 评论的用户的 uid, 评论的用户其实只能是当前用户
     * @param comment 评论内容
     * @param rootMarkId 父评论的 id
     * @param subMarkId 子评论的 id, 可能为 null
     * @param authorUid 作者的 uid
     */
    public String addNoteComment(String noteId, String markedUid, String markUid, String comment, String rootMarkId,
                                 String subMarkId, String authorUid) {
        noteDAO.insertNoteComment(noteId, markedUid, markUid, comment, rootMarkId, subMarkId, authorUid);
        LOGGER.debug("uid 为 {} 的用户给 uid 为 {} 的用户的一个笔记 noteId 为 {} 添加了一条评论, 其中 rootMarkId 为 {}, subMarkId 为 {}, 被评论的用户的 uid 为 {}", markUid, authorUid, noteId, rootMarkId, subMarkId, markedUid);
        return ADD_NOTE_COMMENT_SUCCEED;
    }
}
