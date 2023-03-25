package com.huadiao.mapper;

import com.huadiao.pojo.Note;
import com.huadiao.pojo.NoteDetail;
import com.huadiao.pojo.RootMark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 15 19:35
 */
public interface NoteMapper {

    // 插入新的笔记
    void insertNewNote(@Param("uid") String uid, @Param("userId") String userId, @Param("noteTitle") String noteTitle, @Param("noteContent") String noteContent, @Param("noteAbstract") String noteAbstract);

    // 更新之前的笔记
    void updateNoteContent(@Param("userId") String userId, @Param("noteTitle") String noteTitle, @Param("noteContent") String noteContent, @Param("id") String id, @Param("noteAbstract") String noteAbstract);

    // 删除笔记
    void deleteNote(@Param("userId") String userId, @Param("id") String id);

    /**
     * 获取指定用户的所有笔记
     * @param authorUid 作者 uid
     * @return 返回笔记
     */
    List<Note> selectAllNoteByAuthorUid(@Param("authorUid") String authorUid);

    /**
     * 检索某个用户的某个笔记
     * 说明：authorUid 和 myuid 的区别： authorUid 是检索到的笔记作者的 uid，myuid 是检索该笔记的用户的 uid, id 是 noteId,笔记的唯一 id
     */
    NoteDetail selectNoteByUidAndUserId(@Param("authorUid") String uid, @Param("id") String id, @Param("myUid") String myUid);

    /**
     * 根据作者 uid, 和笔记编号, 查找笔记基本信息
     * @param authorUid 作者 uid
     * @param noteId 笔记唯一 id
     * @return 返回笔记基本信息
     */
    NoteDetail selectBasicNoteInfer(@Param("authorUid") String authorUid, @Param("noteId") String noteId);

    /**
     * 获取笔记浏览量
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 返回 浏览量
     */
    String selectNoteViews(@Param("authorUid") String authorUid, @Param("noteId") String noteId);

    /**
     * 获取笔记的喜欢数量
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 返回喜欢数量
     */
    String selectNoteLikeNumber(@Param("authorUid") String authorUid, @Param("noteId") String noteId);

    /**
     * 获取笔记的收藏数量
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 返回收藏数量
     */
    String selectNoteStarNumber(@Param("authorUid") String authorUid, @Param("noteId") String noteId);

    /**
     * 获取笔记的评论数量
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @return 返回评论数量
     */
    String selectNoteMarkNumber(@Param("authorUid") String authorUid, @Param("noteId") String noteId);

    /**
     * 查找我是否不喜欢当前笔记
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     * @return 如果找到返回 我的 uid, 否则返回 null
     */
    String selectIsUnlikeNote(@Param("myUid") String myUid, @Param("noteId") String noteId);

    /**
     * 查找我是否喜欢当前笔记
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     * @return 如果找到返回 我的 uid, 否则返回 null
     */
    String selectIsLikeNote(@Param("myUid") String myUid, @Param("noteId") String noteId);

    /**
     * 查找我是否收藏了当前笔记
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     * @return 如果找到返回 我的 uid, 否则返回 null
     */
    String selectIsStarNote(@Param("myUid") String myUid, @Param("noteId") String noteId);

    /**
     * 新增笔记浏览量
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @param viewUid 浏览者 uid
     */
    void insertNoteViewNumber(@Param("authorUid") String authorUid, @Param("noteId") String noteId, @Param("viewUid") String viewUid);

    /**
     * 新增笔记点赞
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @param myUid 我的 uid
     */
    void insertNoteLike(@Param("authorUid") String authorUid, @Param("noteId") String noteId, @Param("myUid") String myUid);

    /**
     * 取消笔记点赞
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @param myUid 我的 uid
     */
    void updateNoteLike(@Param("authorUid") String authorUid, @Param("noteId") String noteId, @Param("myUid") String myUid);

    /**
     * 新增笔记不喜欢
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @param myUid 我的 uid
     */
    void insertNoteUnlike(@Param("authorUid") String authorUid, @Param("noteId") String noteId, @Param("myUid") String myUid);

    /**
     * 取消笔记不喜欢
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @param myUid 我的 uid
     */
    void updateNoteUnlike(@Param("authorUid") String authorUid, @Param("noteId") String noteId, @Param("myUid") String myUid);

    /**
     * 新增笔记评论
     * @param noteId 笔记 id
     * @param markedUid 被回复的人的 uid
     * @param myUid 我的 uid
     * @param markContent 评论内容
     * @param rootMarkId 父评论 id
     * @param subMarkId 子评论 id, 可能为 null
     * @param authorUid 作者 uid
     */
    void insertNoteMark(@Param("noteId") String noteId, @Param("markedUid") String markedUid, @Param("myUid") String myUid,
                        @Param("markContent") String markContent, @Param("rootMarkId") String rootMarkId,
                        @Param("subMarkId") String subMarkId, @Param("authorUid") String authorUid);

    Note selectNoteSection(@Param("noteId") String noteId, @Param("authorUid") String authorUid);

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
    void insertNoteComment(@Param("noteId") String noteId, @Param("markedUid") String markedUid, @Param("markUid") String markUid,
                           @Param("comment") String comment, @Param("rootMarkId") String rootMarkId,
                           @Param("subMarkId") String subMarkId, @Param("authorUid") String authorUid);

    // 获取特定笔记的评论
    List<RootMark> selectNoteMark(@Param("noteId") String noteId, @Param("myUid") String myUid);

    /**
     * 新增笔记评论喜欢, 如果存在则根据住状态来更新, 喜欢则取消喜欢, 取消喜欢则再次喜欢
     * @param noteId 笔记 id
     * @param authorUid 作者 uid
     * @param myUid 我的 uid, 即添加喜欢评论的 uid
     * @param likedUid 被喜欢的评论的用户的 uid
     * @param rootMarkId 父评论
     * @param subMarkId 子评论
     */
    void insertNoteMarkLike(@Param("noteId") String noteId, @Param("authorUid") String authorUid, @Param("myUid") String myUid, @Param("likedUid") String likedUid, @Param("rootMarkId") String rootMarkId,
                        @Param("subMarkId") String subMarkId);


    /**
     * 新增笔记评论不喜欢, 如果存在则根据住状态来更新, 不喜欢则取消不喜欢, 取消不喜欢则再次不喜欢
     * @param noteId 笔记 id
     * @param authorUid 作者 uid
     * @param myUid 我的 uid, 即添加不喜欢评论的 uid
     * @param unlikedUid 被不喜欢的评论的用户的 uid
     * @param rootMarkId 父评论
     * @param subMarkId 子评论
     */
    void insertNoteMarkUnlike(@Param("noteId") String noteId, @Param("authorUid") String authorUid, @Param("myUid") String myUid,
                              @Param("unlikedUid") String unlikedUid, @Param("rootMarkId") String rootMarkId,
                              @Param("subMarkId") String subMarkId);

}
