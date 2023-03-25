package com.huadiao.mapper;

import com.huadiao.pojo.UserInfer;
import org.apache.ibatis.annotations.Param;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 28 18:13
 */
public interface UserInferMapper {


    /**
     * 根据 uid 查询用户信息
     * @param uid 要查询的 uid
     * @return 返回用户信息
     */
    UserInfer selectUserInferByUid(@Param("uid") String uid);

    // 新增用户信息
    void insertUserInfer(@Param("uid") String uid, @Param("userId") String userId, @Param("nickname") String nickname,
                   @Param("canvases") String canvases, @Param("sex") String sex,
                   @Param("bornDate") String bornDate, @Param("school") String school);

    /**
     * 根据我的 uid 来修改用户信息
     * @param myUid 我的 uid
     * @param nickname 昵称
     * @param canvases 个人介绍
     * @param sex 性别
     * @param bornDate 出生日期
     * @param school 学校
     */
    void updateUserInferByUid(@Param("myUid") String myUid, @Param("nickname") String nickname,
                  @Param("canvases") String canvases, @Param("sex") String sex,
                  @Param("bornDate") String bornDate, @Param("school") String school);

    // 根据 userId 获取 uid
    String selectUidByUserId(@Param("userId") String userId);

    /**
     * 根据 uid 查询昵称
     * @param uid 用户 uid
     * @return 返回昵称
     */
    String selectNicknameByUid(@Param("uid") String uid);
}
