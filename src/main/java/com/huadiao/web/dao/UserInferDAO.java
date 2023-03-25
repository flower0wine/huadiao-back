package com.huadiao.web.dao;

import com.huadiao.mapper.UserInferMapper;
import com.huadiao.pojo.UserInfer;
import com.huadiao.utils.microspring.requestprocess.DispatcherInterface;
import org.apache.ibatis.session.SqlSession;

/**
 * @projectName 花凋
 * @author flowerwine
 * @version 1.1
 */
public class UserInferDAO implements DispatcherInterface {
    /**
     * 插入用户信息
     * @param uid 用户 uid
     * @param userId 用户 userId
     * @param nickname 用户昵称
     * @param canvases 用户个人介绍
     * @param sex 用户性别
     * @param bornDate 用户出生日期
     * @param school 用户学校
     */
    public void insertUserInfer(String uid, String userId, String nickname, String canvases, String sex, String bornDate, String school) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        UserInferMapper userInferMapper = sqlSession.getMapper(UserInferMapper.class);
        userInferMapper.insertUserInfer(uid, userId, nickname, canvases, sex, bornDate, school);
    }

    /**
     * 通过用户 uid 获取用户昵称
     * @param uid 用户 uid
     * @return 返回昵称
     */
    public String selectNicknameByUid(String uid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        UserInferMapper userInferMapper = sqlSession.getMapper(UserInferMapper.class);
        return userInferMapper.selectNicknameByUid(uid);
    }

    /**
     * 根据 uid 查询用户信息
     * @param uid 用户 uid
     * @return 返回用户信息
     */
    public UserInfer selectUserInferByUid(String uid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        UserInferMapper userInferMapper = sqlSession.getMapper(UserInferMapper.class);
        return userInferMapper.selectUserInferByUid(uid);
    }

    /**
     * 根据我的 uid 来修改用户信息
     * @param myUid 我的 uid
     * @param nickname 昵称
     * @param canvases 个人介绍
     * @param sex 性别
     * @param bornDate 出生日期
     * @param school 学校
     */
    public void updateUserInferByUid(String myUid, String nickname, String bornDate, String sex, String canvases, String school) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        UserInferMapper userInferMapper = sqlSession.getMapper(UserInferMapper.class);
        userInferMapper.updateUserInferByUid(myUid, nickname, canvases, sex, bornDate, school);
    }
}
