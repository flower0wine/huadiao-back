package com.huadiao.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.LatestPrivacyUser;
import com.huadiao.pojo.NoteMarkLikeMessage;
import com.huadiao.pojo.ReplyMeMessage;
import com.huadiao.pojo.SystemMessage;
import com.huadiao.utils.log.Loggers;
import com.huadiao.web.dao.MessageDAO;
import com.huadiao.web.dao.NoteDAO;
import com.huadiao.web.service.MessageService;

import java.util.List;

/**
 * @projectName 花凋
 * @author flowerwine
 * @description 业务处理, 逻辑处理, 用户消息
 * @version 1.1
 */
public class MessageServiceImpl implements MessageService, Loggers {
    private MessageDAO messageDAO;
    private NoteDAO noteDAO;

    /**
     * 获取回复我的信息
     * @param myUid 我的 uid
     * @return 获取成功返回 getReplyMeMessageSucceed
     */
    public String getReplyMeMessage(String myUid) {
        LOGGER.debug("uid 为 {} 的用户尝试获取回复我的信息！", myUid);
        List<ReplyMeMessage> replyMeMessages = messageDAO.selectReplyMeMessageByUid(myUid);
        return JSON.toJSONString(replyMeMessages);
    }

    /**
     * 获取系统消息
     * @param myUid 我的 uid
     * @return 返回系统消息
     */
    public String getSystemMessage(String myUid) {
        LOGGER.debug("uid 为 {} 的用户尝试获取系统消息", myUid);
        List<SystemMessage> systemMessages = messageDAO.selectSystemMessage();
        return JSON.toJSONString(systemMessages);
    }

    /**
     * 获取笔记评论被点赞的消息
     * @param myUid 我的 uid
     * @return 返回被点赞的消息
     */
    public String getNoteMarkMessage(String myUid) {
        LOGGER.debug("uid 为 {} 的用户尝试获取笔记评论被点赞的消息", myUid);
        List<NoteMarkLikeMessage> noteMarkLikeMessages = messageDAO.selectNoteMarkLikeMessageByUid(myUid);
        return JSON.toJSONString(noteMarkLikeMessages);
    }

    /**
     * 获取私信对象及聊天消息
     * @param myUid 我的 uid
     * @return 返回私信对象及聊天消息
     */
    public String getPrivateUserAndMessage(String myUid) {
        LOGGER.debug("uid 为 {} 的用户尝试获取私信对象及聊天消息", myUid);
        List<LatestPrivacyUser> latestPrivacyUsers = messageDAO.selectLatestPrivacyUserByUid(myUid);
        return JSON.toJSONString(latestPrivacyUsers);
    }
}
