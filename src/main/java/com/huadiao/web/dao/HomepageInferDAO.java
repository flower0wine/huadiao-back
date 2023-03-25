package com.huadiao.web.dao;

import com.huadiao.mapper.HomepageInferMapper;
import com.huadiao.pojo.HomepageInfer;
import com.huadiao.utils.microspring.requestprocess.DispatcherInterface;
import org.apache.ibatis.session.SqlSession;

/**
 * @projectName 花凋
 * @author flowerwine
 * @version 1.1
 */
public class HomepageInferDAO implements DispatcherInterface {

    /**
     * 新增个人主页
     * @param uid 用户 uid
     * @param userId 用户 userId
     */
    public void insertHomepageInfer(String uid, String userId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HomepageInferMapper homepageInferMapper = sqlSession.getMapper(HomepageInferMapper.class);
        homepageInferMapper.insertAllByUserId(userId, uid);
    }

    /**
     * 根据 uid 获取用户头像链接
     * @param uid 用户 uid
     * @return 用户头像链接
     */
    public String selectUserAvatar(String uid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HomepageInferMapper homepageInferMapper = sqlSession.getMapper(HomepageInferMapper.class);
        return homepageInferMapper.selectUserAvatarByUid(uid);
    }

    /**
     * 查询用户部分个人主页信息
     * @param uid 要查询的 uid
     * @return 返回部分个人主页信息
     */
    public HomepageInfer selectSectionHomepageInferByUid(String uid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HomepageInferMapper homepageInferMapper = sqlSession.getMapper(HomepageInferMapper.class);
        return homepageInferMapper.selectSectionHomepageInferByUid(uid);
    }

    /**
     * 新增别人的浏览量, 我是浏览者
     * @param myUid 我的 uid
     * @param otherUid 别人的 uid
     */
    public void insertHomepageViews(String myUid, String otherUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HomepageInferMapper homepageInferMapper = sqlSession.getMapper(HomepageInferMapper.class);
        homepageInferMapper.insertHomepageVisit(otherUid, myUid);
    }

    /**
     * 根据 uid 更改个人主页背景
     * @param myUid 我的 uid
     * @param homepageBackground 要更换的背景部分链接
     */
    public void updatePageBackgroundByUid(String myUid, String homepageBackground) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HomepageInferMapper homepageInferMapper = sqlSession.getMapper(HomepageInferMapper.class);
        homepageInferMapper.updatePageBackgroundByUid(myUid, homepageBackground);
    }

    /**
     * 更改用户头像
     * @param myUid 我的 uid
     * @param userAvatar 要更换的头像的部分链接
     */
    public void updateUserAvatarByUid(String myUid, String userAvatar) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HomepageInferMapper homepageInferMapper = sqlSession.getMapper(HomepageInferMapper.class);
        homepageInferMapper.updateUserAvatarByUid(myUid, userAvatar);
    }
}
