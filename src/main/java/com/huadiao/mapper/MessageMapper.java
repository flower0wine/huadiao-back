package com.huadiao.mapper;

import com.huadiao.pojo.LatestPrivacyUser;
import com.huadiao.pojo.NoteMarkLikeMessage;
import com.huadiao.pojo.ReplyMeMessage;
import com.huadiao.pojo.SystemMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 13 17:30
 */
public interface MessageMapper {

    // 新增系统消息
    void insertSystemMessage(@Param("title") String title, @Param("content") String content);

    // 删除系统消息
    void deleteSystemMessage(@Param("id") String messageId);

    // 获取系统消息的 id，与上一个方法配套书写
    String selectPrimaryKey();

    /**
     * 获取系统消息
     * @return 返回系统消息
     */
    List<SystemMessage> selectSystemMessage();

    /**
     * 获取回复我的信息
     * @param myUid 我的 uid
     * @return 返回回复我的信息
     */
    List<ReplyMeMessage> selectReplyMeMessageByUid(@Param("myUid") String myUid);

    // 更新所有的回复我的为已读
    void updateReplyMessageRead(@Param("uid") String uid);

    /**
     * 获取笔记评论被点赞的消息
     * @param myUid 我的 uid
     * @return 返回被点赞的消息
     */
    List<NoteMarkLikeMessage> selectNoteMarkLikeMessageByUid(@Param("myUid") String myUid);

    /**
     * 获取私信对象及聊天消息
     * @param myUid 我的 uid
     * @return 返回私信对象及聊天消息
     */
    List<LatestPrivacyUser> selectLatestPrivacyUserByUid(@Param("myUid") String myUid);

    // 删除回复消息提醒
    void deleteReplyMessage(@Param("uid") String uid, @Param("rootMarkId") String rootMarkId, @Param("noteId") String noteId);

    // 删除点赞消息提醒
    void deleteMarkLikeMessage(@Param("noteId") String noteId, @Param("rootMarkId") String rootMarkId, @Param("subMarkId") String subMarkId);

    // 创建私信对象
    void insertPrivacyUser(@Param("uid") String uid, @Param("privacyUid") String privacyUid, @Param("sessionId") String sessionId, @Param("gratefulMessage") String gratefulMessage);

    // 删除私信对象
    void deletePrivacyUser(@Param("uid") String uid, @Param("privacyUid") String privacyUid);

    // 存储会话消息
    void insertSessionMessage(@Param("sendUid") String sendUid, @Param("receiveUid") String receiveUid, @Param("sessionId") String sessionId, @Param("sessionContent") String sessionContent);
}
