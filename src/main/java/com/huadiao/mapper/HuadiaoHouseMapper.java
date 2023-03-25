package com.huadiao.mapper;

import com.huadiao.pojo.HuadiaoHouseInfer;
import org.apache.ibatis.annotations.Param;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 01 11:40
 */
public interface HuadiaoHouseMapper {

    // 加入新的番剧页面配置（仅限用户刚开始登录时）
    void insertPanoperaSettings(@Param("uid") String uid, @Param("userId") String userId);

    /**
     * 修改番剧馆标题颜色
     * @param myUid 我的 uid
     * @param huadiaoHouseTitleColor 标题颜色字符串
     */
    void updateHuadiaoHouseTitleColorByUid(@Param("myUid") String myUid, @Param("huadiaoHouseTitleColor") String huadiaoHouseTitleColor);

    /**
     * 修改番剧馆标题背景
     * @param myUid 我的 uid
     * @param huadiaoHouseTitleBackground 番剧馆标题背景字符串
     */
    void updateHuadiaoHouseTitleBackgroundByUid(@Param("myUid") String myUid, @Param("huadiaoHouseTitleBackground") String huadiaoHouseTitleBackground);

    /**
     * 更新番剧馆页面背景
     * @param myUid 我的 uid
     * @param huadiaoHouseBackground 番剧馆页面背景部分地址
     */
    void updateHuadiaoHouseBackgroundByUid(@Param("myUid") String myUid, @Param("huadiaoHouseBackground") String huadiaoHouseBackground);

    /**
     * 修改番剧馆页面前景
     * @param myUid 我的 uid
     * @param huadiaoHouseForeground 番剧馆页面前景部分地址
     */
    void updateHuadiaoHouseForegroundByUid(@Param("myUid") String myUid, @Param("huadiaoHouseForeground") String huadiaoHouseForeground);

    /**
     * 修改图片边框
     * @param myUid 我的 uid
     * @param borderImgChoice 图片边框选择
     */
    void updateHuadiaoHouseChoiceByUid(@Param("myUid") String myUid, @Param("borderImgChoice") String borderImgChoice);

    /**
     * 修改页面添加卡片背景
     * @param myUid 我的 uid
     * @param buildCardBackground 添加卡片背景
     */
    void updateCardBackgroundByUid(@Param("myUid") String myUid, @Param("buildCardBackground") String buildCardBackground);

    /**
     * 新增番剧
     * @param myUid 我的 uid
     * @param myUserId 我的 userId
     * @param fanjuName 番剧名称
     * @param fanjuCover 番剧封面
     * @param signId 唯一标识
     */
    void insertNewFanjuByUid(@Param("myUid") String myUid, @Param("signId") String signId, @Param("myUserId") String myUserId,
                             @Param("fanjuName") String fanjuName, @Param("fanjuCover") String fanjuCover);

    /**
     * 根据唯一标识删除番剧
     * @param myUid 我的 uid
     * @param signId 番剧唯一标识
     */
    void updateHuadiaoHouseFanjuStatusByUid(@Param("myUid") String myUid, @Param("signId") String signId);

    /**
     * 获取用户番剧馆信息
     * @param authorUid 要查询的用户 uid
     * @return 返回该用户的番剧馆信息
     */
    HuadiaoHouseInfer selectHuadiaoHouseInferByUid(@Param("authorUid") String authorUid);

}
