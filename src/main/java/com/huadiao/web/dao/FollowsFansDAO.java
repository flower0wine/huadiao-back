package com.huadiao.web.dao;

import com.huadiao.mapper.FollowFansMapper;
import com.huadiao.pojo.FansInfer;
import com.huadiao.pojo.FollowsInfer;
import com.huadiao.utils.microspring.requestprocess.DispatcherInterface;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @projectName 花凋
 * @author flowerwine
 * @version 1.1
 */
public class FollowsFansDAO implements DispatcherInterface {

    /**
     * 返回用户关注个数和粉丝个数
     * @param userId 用户 userId
     * @return 返回一个集合, 下标 0 是关注个数, 下标 1 是粉丝个数
     */
    public List<Integer> selectFollowsAndFansAmountByUserId(String userId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        FollowFansMapper followFansMapper = sqlSession.getMapper(FollowFansMapper.class);
        return followFansMapper.countFollowAndFansByUserId(userId);
    }

    /**
     * 获取我与其他人的关系
     * @param myUserId 我的 userId
     * @param otherUserId 别人的 userId
     * @return 返回一个集合：
     *          1. 如果集合长度为 0, 则说明两人没有任何关系
     *          2. 如果集合长度为 2, 说明两人互粉
     *          3. 如果集合中只有一个元素, 并且该元素为 1, 则我关注他，他没有关注我
     *          4. 如果集合中只有一个元素, 并且该元素为 2, 则我没有关注他, 但他关注我
     */
    public List<Integer> selectRelationByBothUserId(String myUserId, String otherUserId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        FollowFansMapper followFansMapper = sqlSession.getMapper(FollowFansMapper.class);
        return followFansMapper.selectRelationByBothUserId(myUserId, otherUserId);
    }

    /**
     * 根据我和别人的 uid、userId 新增关系, 也可以是取关后再关注, 总之只能是关注别人, 不能取关
     * @param myUid 我的 uid
     * @param myUserId 我的 userId
     * @param otherUid 别人的 uid
     * @param otherUserId 别人的 userId
     */
    public void insertRelationByBothUserIdAndUid(String myUid, String myUserId, String otherUid, String otherUserId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        FollowFansMapper followFansMapper = sqlSession.getMapper(FollowFansMapper.class);
        followFansMapper.insertRelationByBothUserIdAndUid(myUid, myUserId, otherUid, otherUserId);
    }

    /**
     * 根据我和别人的 userId 来取消我对别人的关注
     * @param myUserId 我的 userId
     * @param otherUserId 别人的 userId
     */
    public void updateRelationByBothUserId(String myUserId, String otherUserId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        FollowFansMapper followFansMapper = sqlSession.getMapper(FollowFansMapper.class);
        followFansMapper.updateRelationByBothUserId(myUserId, otherUserId);
    }

    /**
     * 获取指定用户的关注
     * @param viewedUid 用户 uid
     * @return 返回关注信息
     */
    public List<FollowsInfer> selectFollowsByUid(String viewedUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        FollowFansMapper followFansMapper = sqlSession.getMapper(FollowFansMapper.class);
        return followFansMapper.selectFollowsByUid(viewedUid);
    }

    /**
     * 获取指定用户的粉丝
     * @param viewedUid 用户 uid
     * @return 返回粉丝信息
     */
    public List<FansInfer> selectFansByUid(String viewedUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        FollowFansMapper followFansMapper = sqlSession.getMapper(FollowFansMapper.class);
        return followFansMapper.selectFansByUid(viewedUid);
    }
}
