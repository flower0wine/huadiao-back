package com.huadiao.web.dao;

import com.huadiao.mapper.NoteMapper;
import com.huadiao.pojo.Note;
import com.huadiao.pojo.NoteDetail;
import com.huadiao.pojo.RootMark;
import com.huadiao.utils.microspring.requestprocess.DispatcherInterface;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @projectName 花凋
 * @author flowerwine
 * @version 1.1
 */
public class NoteDAO implements DispatcherInterface {

    /**
     * 新增用户笔记
     * @param uid 用户 uid
     * @param userId 用户 userId
     * @param noteTitle 用户笔记标题
     * @param noteContent 用户笔记 HTML 内容
     * @param noteAbstract 用户笔记摘要
     */
    public void insertNewNote(String uid, String userId, String noteTitle, String noteContent, String noteAbstract) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        noteMapper.insertNewNote(uid, userId, noteTitle, noteContent, noteAbstract);
    }

    /**
     * 根据作者 uid, 和笔记编号, 查找笔记基本信息
     * @param authorUid 作者 uid
     * @param noteId 笔记唯一 id
     * @return 返回笔记基本信息
     */
    public NoteDetail selectBasicNoteInfer(String authorUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        return noteMapper.selectBasicNoteInfer(authorUid, noteId);
    }

    /**
     * 获取笔记浏览量
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 返回浏览量
     */
    public String selectNoteViews(String authorUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        return noteMapper.selectNoteViews(authorUid, noteId);
    }

    /**
     * 查找我是否不喜欢当前笔记
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     * @return 如果找到返回 我的 uid, 否则返回 null
     */
    public String selectIsUnlikeNote(String myUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        return noteMapper.selectIsUnlikeNote(myUid, noteId);
    }

    /**
     * 查找我是否喜欢当前笔记
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     * @return 如果找到返回 我的 uid, 否则返回 null
     */
    public String selectIsLikeNote(String myUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        return noteMapper.selectIsLikeNote(myUid, noteId);
    }

    /**
     * 查找我是否收藏了当前笔记
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     * @return 如果找到返回 我的 uid, 否则返回 null
     */
    public String selectIsStarNote(String myUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        return noteMapper.selectIsStarNote(myUid, noteId);
    }

    /**
     * 获取笔记的喜欢数量
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 返回喜欢数量
     */
    public String selectNoteLikeNumber(String authorUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        return noteMapper.selectNoteLikeNumber(authorUid, noteId);
    }

    /**
     * 获取笔记的收藏数量
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 返回收藏数量
     */
    public String selectNoteStarNumber(String authorUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        return noteMapper.selectNoteStarNumber(authorUid, noteId);
    }

    /**
     * 获取笔记的评论数量
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 返回评论数量
     */
    public String selectNoteMarkNumber(String authorUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        return noteMapper.selectNoteMarkNumber(authorUid, noteId);
    }

    /**
     * 获取笔记的评论
     * @param noteId 笔记 id
     * @param myUid 我的 uid
     * @return 返回评论
     */
    public List<RootMark> selectNoteMarks(String noteId, String myUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        return noteMapper.selectNoteMark(noteId, myUid);
    }

    /**
     * 用户给笔记评论点赞, 如果存在则根据住状态来更新, 喜欢则取消喜欢, 取消喜欢则再次喜欢
     * @param noteId 笔记 id
     * @param authorUid 作者 uid
     * @param likedUid 评论被点赞的用户 userId
     * @param myUid 我的 uid
     * @param rootMarkId 父评论 id
     * @param subMarkId 子评论 id, 子评论 id 可能为 null
     */
    public void insertNoteMarkLikeOrUpdate(String noteId, String authorUid, String likedUid, String myUid, String rootMarkId, String subMarkId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        noteMapper.insertNoteMarkLike(noteId, authorUid, myUid, likedUid, rootMarkId, subMarkId);
    }

    /**
     * 用户给笔记评论不喜欢, 如果存在则根据住状态来更新, 不喜欢则取消不喜欢, 取消不喜欢则再次不喜欢
     * @param noteId 笔记 id
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param unlikedUid 被点赞的用户的 uid
     * @param rootMarkId 父评论
     * @param subMarkId 子评论, 子评论可能为 null
     */
    public void insertNoteMarkUnlikeOrUpdate(String noteId, String authorUid, String myUid, String unlikedUid, String rootMarkId, String subMarkId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        noteMapper.insertNoteMarkUnlike(noteId, authorUid, myUid, unlikedUid, rootMarkId, subMarkId);
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
    public void insertNoteMark(String noteId, String markedUid, String myUid, String markContent, String rootMarkId, String subMarkId, String authorUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        noteMapper.insertNoteMark(noteId, markedUid, myUid, markContent, rootMarkId, subMarkId, authorUid);
    }

    /**
     * 新增笔记点赞
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     */
    public void insertNoteLike(String authorUid, String myUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        noteMapper.insertNoteLike(authorUid, noteId, myUid);
    }

    /**
     * 取消笔记点赞
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     */
    public void cancelNoteLike(String authorUid, String myUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        noteMapper.updateNoteLike(authorUid, noteId, myUid);
    }

    /**
     * 新增笔记不喜欢
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     */
    public void insertNoteUnlike(String authorUid, String myUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        noteMapper.insertNoteUnlike(authorUid, noteId, myUid);
    }

    /**
     * 取消笔记不喜欢
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     */
    public void cancelNoteUnlike(String authorUid, String myUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        noteMapper.updateNoteUnlike(authorUid, noteId, myUid);
    }

    /**
     * 返回指定用户的所有笔记
     * @param authorUid 作者 uid
     * @return 返回所有笔记
     */
    public List<Note> selectAllNoteByAuthorUid(String authorUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        return noteMapper.selectAllNoteByAuthorUid(authorUid);
    }


    /**
     * 获取笔记部分信息
     * @param noteId 笔记 id
     * @param authorUid 作者 uid
     * @return 返回笔记部分信息
     */
    public Note selectNoteSection(String noteId, String authorUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        return noteMapper.selectNoteSection(noteId, authorUid);
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
    public void insertNoteComment(String noteId, String markedUid, String markUid, String comment, String rootMarkId,
                                  String subMarkId, String authorUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        noteMapper.insertNoteComment(noteId, markedUid, markUid, comment, rootMarkId, subMarkId, authorUid);
    }
}
