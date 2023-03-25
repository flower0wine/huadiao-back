package com.huadiao.web.dao;

import com.huadiao.mapper.UserSettingsMapper;
import com.huadiao.pojo.MessageSettings;
import com.huadiao.pojo.UserSettingsInfer;
import com.huadiao.utils.microspring.requestprocess.DispatcherInterface;
import org.apache.ibatis.session.SqlSession;

/**
 * @projectName 花凋
 * @author flowerwine
 * @version 1.1
 */
public class UserSettingsDAO implements DispatcherInterface {

    /**
     * 新增用户设置
     * @param uid 用户 uid
     * @param userId 用户 userId
     */
    public void insertUserSettings(String uid, String userId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);
        userSettingsMapper.insertUserSettingsByUidAndUserId(uid, userId);
    }

    /**
     * 获取我的用户设置
     * @param myUid 我的 uid
     * @return 返回用户设置
     */
    public UserSettingsInfer selectUserSettingsByUid(String myUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);
        return userSettingsMapper.selectUserSettingsByUid(myUid);
    }

    /**
     * 获取信息设置
     * @param myUid 我的 uid
     * @return 返回我的消息设置
     */
    public MessageSettings selectMessageSettingsByUid(String myUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);
        return userSettingsMapper.selectMessageSettingsByUid(myUid);
    }

    /**
     * 更改用户设置
     * @param myUid 我的 uid
     * @param option 要更改的选项
     */
    public void updateUserSettingsByUid(String myUid, String option) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);
        userSettingsMapper.updateUserSettingsByUid(myUid, option);
    }
}
