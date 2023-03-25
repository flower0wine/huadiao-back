package com.huadiao.mapper;

import com.huadiao.pojo.HomepageInfer;
import com.huadiao.pojo.Notes;
import org.apache.ibatis.annotations.Param;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 04 18:12
 */
public interface HomepageInferMapper {

    // 根据 uid 查找用户个人主页信息
    HomepageInfer selectAllByUid(@Param("uid") String uid);

    // 插入个人主页部分信息（表homepage）
    void insertAllByUserId(@Param("userId") String userId, @Param("uid") String uid);

    // 新增个人主页浏览量
    void insertHomepageVisit(@Param("uid") String uid, @Param("visitUid") String visitUid);

    /**
     * 根据 uid 更改个人主页背景
     * @param myUid 我的 uid
     * @param homepageBackground 要更换的背景部分链接
     */
    void updatePageBackgroundByUid(@Param("myUid") String myUid, @Param("homepageBackground") String homepageBackground);

    /**
     * 更改用户头像
     * @param myUid 我的 uid
     * @param userAvatar 要更换的头像的部分链接
     */
    void updateUserAvatarByUid(@Param("myUid") String myUid, @Param("userAvatar") String userAvatar);

    /**
     * 根据 uid 获取用户头像链接
     * @param uid 用户 uid
     * @return 用户头像链接
     */
    String selectUserAvatarByUid(@Param("uid") String uid);

    /**
     * 查询用户部分个人主页信息
     * @param uid 要查询的 uid
     * @return 返回部分个人主页信息
     */
    HomepageInfer selectSectionHomepageInferByUid(@Param("uid") String uid);

    // 根据 uid 获取用户头像
    Notes selectNoteAuthor(@Param("uid") String uid);
}
