package com.huadiao.web.dao;

import com.huadiao.mapper.UserMapper;
import com.huadiao.pojo.User;
import com.huadiao.utils.microspring.requestprocess.DispatcherInterface;
import org.apache.ibatis.session.SqlSession;

/**
 * @projectName 花凋
 * @author flowerwine
 * @version 1.1
 */
public class UserDAO implements DispatcherInterface {

    /**
     * 根据用户名和密码查找用户
     * @param username 用户名
     * @param password 用户密码
     * @return 返回 user
     */
    public User selectUserByUsernameAndPassword(String username, String password) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.selectUserSurvive(username, password);
    }

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 返回 user
     */
    public User selectUserByUsername(String username) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.selectByUsernameUser(username);
    }

    /**
     * 添加新用户
     * @param userId 用户 ID
     * @param username 用户名
     * @param password 用户密码
     */
    public void insertNewUser(String userId, String username, String password) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.insertUser(userId, username, password);
    }

    /**
     * 根据用户 userId 查找其 uid
     * @param userId 用户 userId
     * @return 返回用户 uid
     */
    public String selectUidByUserId(String userId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.selectUidByUserId(userId);
    }

    /**
     * 根据用户 uid 查找其 userId
     * @param uid 用户 uid
     * @return 返回用户 userId
     */
    public String selectUserIdByUid(String uid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        return userMapper.selectUserIdByUid(uid);
    }

    /**
     * 更新用户登录时间
     * @param userId 用户 userId
     */
    public void updateUserLoginTime(String userId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        userMapper.updateLoginTime(userId);
    }
}
