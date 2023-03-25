package com.huadiao.web.dao;

import com.huadiao.mapper.MessageMapper;
import com.huadiao.pojo.LatestPrivacyUser;
import com.huadiao.pojo.NoteMarkLikeMessage;
import com.huadiao.pojo.ReplyMeMessage;
import com.huadiao.pojo.SystemMessage;
import com.huadiao.utils.microspring.requestprocess.DispatcherInterface;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName 花凋
 */
public class MessageDAO implements DispatcherInterface {

    /**
     * 获取回复我的信息
     * @param myUid 我的 uid
     * @return 返回回复我的信息
     */
    public List<ReplyMeMessage> selectReplyMeMessageByUid(String myUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);
        return messageMapper.selectReplyMeMessageByUid(myUid);
    }

    /**
     * 获取系统消息
     * @return 返回系统消息
     */
    public List<SystemMessage> selectSystemMessage() {
        SqlSession sqlSession = THREAD_LOCAL.get();
        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);
        return messageMapper.selectSystemMessage();
    }

    /**
     * 获取笔记评论被点赞的消息
     * @param myUid 我的 uid
     * @return 返回被点赞的消息
     */
    public List<NoteMarkLikeMessage> selectNoteMarkLikeMessageByUid(String myUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);
        return messageMapper.selectNoteMarkLikeMessageByUid(myUid);
    }

    /**
     * 获取私信对象及聊天消息
     * @param myUid 我的 uid
     * @return 返回私信对象及聊天消息
     */
    public List<LatestPrivacyUser> selectLatestPrivacyUserByUid(String myUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);
        return messageMapper.selectLatestPrivacyUserByUid(myUid);
    }

}
