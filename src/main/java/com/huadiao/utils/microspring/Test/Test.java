package com.huadiao.utils.microspring.Test;

import com.huadiao.mapper.MessageMapper;
import com.huadiao.pojo.ReplyMeMessage;
import com.huadiao.utils.microspring.requestprocess.DispatcherInterface;
import com.huadiao.utils.microspring.requestprocess.SqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description
 */
public class Test implements DispatcherInterface {
    public static void main(String[] args) throws IOException {
        SqlSession sqlSession = SqlSessionFactory.generateSqlSession();
        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);
        List<ReplyMeMessage> replyMeMessages = messageMapper.selectReplyMeMessageByUid("uid");
        System.out.println(replyMeMessages);
    }
}
