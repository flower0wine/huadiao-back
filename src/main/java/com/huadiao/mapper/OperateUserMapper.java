package com.huadiao.mapper;

import com.huadiao.pojo.systemSpecific.UserAllInfos;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 12 22 12:29
 */
public interface OperateUserMapper {

    // 获取所有用户的所有有用信息
    List<UserAllInfos> selectUsersAllInfos();

    // 更改对应用户信息
    void updateUserInfos(@Param("uid") String uid,
                         @Param("userId") String userId,
                         @Param("username") String username,
                         @Param("beforeUsername") String beforeUsername,
                         @Param("updateUsernameDate") String updateUsernameDate,
                         @Param("loginTime") String loginTime,
                         @Param("registerTime") String registerTime,
                         @Param("logOff") String logOff,
                         @Param("logOffDate") String logOffDate);

    // 删除用户
    void deleteUser(@Param("uid") String uid, @Param("userId") String userId);
}
