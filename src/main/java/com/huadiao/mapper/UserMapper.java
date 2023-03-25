package com.huadiao.mapper;

import com.huadiao.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 25 16:08
 */
public interface UserMapper {
    // @Param("username") String username 中，@Param 中的是在 .xml 中的 #{} 变量名，而后面的则是传入的类型

//    通过用户名查找用户
    User selectByUsernameUser(@Param("username") String username);

    // 判断用户是否存在
    User selectUserSurvive(@Param("username") String username, @Param("password") String password);

    // 加入新用户
    void insertUser(@Param("userId") String userId, @Param("username") String username, @Param("password") String password);

    // 通过用户名查找 userId
    String selectUserIdByUsername(@Param("username") String username);

    // 通过 userId 查找用户
    List<String> selectAllUserId();

    // 修改登录时间
    void updateLoginTime(@Param("userId") String userId);

    // 根据用户 userId 查找 uid
    String selectUidByUserId(@Param("userId") String userId);

    // 根据用户 uid 查找 userId
    String selectUserIdByUid(@Param("uid") String uid);

}
