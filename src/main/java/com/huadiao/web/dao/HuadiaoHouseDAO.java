package com.huadiao.web.dao;

import com.huadiao.mapper.HuadiaoHouseMapper;
import com.huadiao.pojo.HuadiaoHouseInfer;
import com.huadiao.utils.microspring.requestprocess.DispatcherInterface;
import org.apache.ibatis.session.SqlSession;

/**
 * @projectName 花凋
 * @author flowerwine
 * @version 1.1
 */
public class HuadiaoHouseDAO implements DispatcherInterface {

    /**
     * 新增用户番剧馆
     * @param uid 用户 uid
     * @param userId 用户 userId
     */
    public void insertHuadiaoHouse(String uid, String userId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);
        huadiaoHouseMapper.insertPanoperaSettings(uid, userId);
    }

    /**
     * 获取用户番剧馆信息
     * @param authorUid 作者 uid
     * @return 返回该用户的番剧馆信息
     */
    public HuadiaoHouseInfer selectHuadiaoHouseInferByUid(String authorUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);
        return huadiaoHouseMapper.selectHuadiaoHouseInferByUid(authorUid);
    }

    /**
     * 新增番剧
     * @param myUid 我的 uid
     * @param myUserId 我的 userId
     * @param fanjuName 番剧名称
     * @param fanjuCover 番剧封面
     * @param signId 唯一标识
     */
    public void insertNewFanjuByUid(String myUid, String signId, String myUserId, String fanjuName, String fanjuCover) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);
        huadiaoHouseMapper.insertNewFanjuByUid(myUid, signId, myUserId, fanjuName, fanjuCover);
    }

    /**
     * 更新番剧馆页面背景
     * @param myUid 我的 uid
     * @param huadiaoHouseBackground 番剧馆页面背景部分地址
     */
    public void updateHuadiaoHouseBackgroundByUid(String myUid, String huadiaoHouseBackground) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);
        huadiaoHouseMapper.updateHuadiaoHouseBackgroundByUid(myUid, huadiaoHouseBackground);
    }

    /**
     * 修改番剧馆页面前景
     * @param myUid 我的 uid
     * @param huadiaoHouseForeground 番剧馆页面前景部分地址
     */
    public void updateHuadiaoHouseForegroundByUid(String myUid, String huadiaoHouseForeground) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);
        huadiaoHouseMapper.updateHuadiaoHouseForegroundByUid(myUid, huadiaoHouseForeground);
    }

    /**
     * 修改页面添加卡片背景
     * @param myUid 我的 uid
     * @param buildCardBackground 添加卡片背景
     */
    public void updateHuadiaoHouseCardBackgroundByUid(String myUid, String buildCardBackground) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);
        huadiaoHouseMapper.updateCardBackgroundByUid(myUid, buildCardBackground);
    }

    /**
     * 修改番剧馆标题背景
     * @param myUid 我的 uid
     * @param huadiaoHouseTitleBackground 番剧馆标题背景字符串
     */
    public void updateHuadiaoHouseTitleBackgroundByUid(String myUid, String huadiaoHouseTitleBackground) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);
        huadiaoHouseMapper.updateHuadiaoHouseTitleBackgroundByUid(myUid, huadiaoHouseTitleBackground);
    }

    /**
     * 根据唯一标识删除番剧
     * @param myUid 我的 uid
     * @param signId 唯一标识
     */
    public void updateHuadiaoHouseFanjuStatusByUid(String myUid, String signId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);
        huadiaoHouseMapper.updateHuadiaoHouseFanjuStatusByUid(myUid, signId);
    }

    /**
     * 修改图片边框
     * @param myUid 我的 uid
     * @param borderImgChoice 图片边框选择
     */
    public void updateHuadiaoHouseChoiceByUid(String myUid, String borderImgChoice) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);
        huadiaoHouseMapper.updateHuadiaoHouseChoiceByUid(myUid, borderImgChoice);
    }

    /**
     * 修改番剧馆标题颜色
     * @param myUid 我的 uid
     * @param huadiaoHouseTitleColor 标题颜色字符串
     */
    public void updateHuadiaoHouseTitleColorByUid(String myUid, String huadiaoHouseTitleColor) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);
        huadiaoHouseMapper.updateHuadiaoHouseTitleColorByUid(myUid, huadiaoHouseTitleColor);
    }
}
