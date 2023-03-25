package com.huadiao.mapper;

import com.huadiao.pojo.MessageSettings;
import com.huadiao.pojo.UserSettingsInfer;
import org.apache.ibatis.annotations.Param;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 09 01 16:11
 */
public interface UserSettingsMapper {

    /**
     * 新增用户设置
     * @param myUid 我的 uid
     * @param myUserId 我的 userId
     */
    void insertUserSettingsByUidAndUserId(@Param("myUid") String myUid, @Param("myUserId") String myUserId);

    /**
     * 获取我的用户设置
     * @param myUid 我的 uid
     * @return 返回用户设置
     */
    UserSettingsInfer selectUserSettingsByUid(@Param("myUid") String myUid);

    /**
     * 更改用户设置
     * @param myUid 我的 uid
     * @param option 用户设置选项
     */
    void updateUserSettingsByUid(@Param("myUid") String myUid, @Param("option") String option);

    /**
     * 获取信息设置
     * @param myUid 我的 uid
     * @return 返回我的消息设置
     */
    MessageSettings selectMessageSettingsByUid(@Param("myUid") String myUid);
}
