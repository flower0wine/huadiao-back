package com.huadiao.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.HuadiaoHouseInfer;
import com.huadiao.utils.log.Loggers;
import com.huadiao.utils.microspring.requestprocess.fileupload.FormDataProcessor;
import com.huadiao.web.dao.HistoryDAO;
import com.huadiao.web.dao.HuadiaoHouseDAO;
import com.huadiao.web.service.HuadiaoHouseService;
import com.huadiao.web.service.Service;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description 花凋番剧馆
 */
public class HuadiaoHouseServiceImpl implements HuadiaoHouseService, Loggers {
    private HuadiaoHouseDAO huadiaoHouseDAO;
    private HistoryDAO historyDAO;


    /**
     * 获取给定用户的 番剧馆信息
     * @param myUid 我的 uid
     * @param myUserId 我的 userId
     * @param authorUid 作者 uid
     * @return 返回番剧馆信息
     */
    public String getHuadiaoHouseInformation(String myUid, String myUserId, String authorUid) {
        LOGGER.debug("uid 为 {} 的用户尝试获取 uid 为 {} 的用户的番剧馆信息", myUid, authorUid);
        HuadiaoHouseInfer huadiaoHouseInfers;
        huadiaoHouseInfers = huadiaoHouseDAO.selectHuadiaoHouseInferByUid(authorUid);
        if(huadiaoHouseInfers == null) {
            huadiaoHouseInfers = new HuadiaoHouseInfer(new ArrayList<>());
        }
        // 添加番剧馆浏览记录
        historyDAO.insertViewedFanjuHistoryByUid(authorUid, myUid);

        // 设置访问者个人信息
        huadiaoHouseInfers.setMyUid(myUid);
        huadiaoHouseInfers.setMyUserId(myUserId);
        huadiaoHouseInfers.setAuthor(myUid.equals(authorUid));
        return JSON.toJSONString(huadiaoHouseInfers);
    }

    /**
     * 添加新番剧
     * @param myUid 我的 uid
     * @param myUserId 我的 userId
     * @param fanjuName 番剧名称
     * @param fanjuCover 番剧封面部分地址, 为文件时则为 null
     * @param signId 唯一标识
     * @return 添加成功返回 addNewFanjuSucceed
     */
    public String addNewFanju(HttpServletRequest request, String signId, String myUid, String myUserId, String fanjuName, String fanjuCover) throws Exception {
        LOGGER.debug("uid 为 {} 的用户尝试新增番剧", myUid);
        // 初始化字符串数组为 fanjuCover, 因为前端传来的可能不是文件, 而是字符串
        final String[] fileName = new String[]{fanjuCover};
        new FormDataProcessor(request) {
            @Override
            public void fileData(FileItem fileItem) throws Exception {
                fileName[0] = FormDataProcessor.saveFile(fileItem, myUid, Service.HUADIAO_HOUSE_FANJU);
            }
        };
        LOGGER.debug("uid 为 {} 的新增番剧名称为 {}, 唯一标识为 {}, 番剧封面部分地址为 {}", myUid, fanjuName, signId, fileName[0]);
        huadiaoHouseDAO.insertNewFanjuByUid(myUid, signId, myUserId, fanjuName, fileName[0]);
        return ADD_NEW_FANJU_SUCCEED;
    }

    /**
     * 修改番剧馆页面背景
     * @param request 请求对象
     * @param myUid 我的 uid
     * @param huadiaoHouseBackground 番剧馆背景部分地址
     * @return 修改番剧馆页面背景成功返回 modifyHuadiaoHouseBackgroundSucceed
     * @throws Exception 可能抛出异常
     */
    public String modifyHuadiaoHouseBackground(HttpServletRequest request, String myUid, String huadiaoHouseBackground) throws Exception {
        LOGGER.debug("uid 为 {} 的用户尝试修改自己的番剧馆背景", myUid);
        // 初始化字符串数组为 huadiaoHouseBackground, 因为前端传来的可能不是文件, 而是字符串
        final String[] fileName = new String[]{huadiaoHouseBackground};
        new FormDataProcessor(request) {
            @Override
            public void fileData(FileItem fileItem) throws Exception {
                fileName[0] = FormDataProcessor.saveFile(fileItem, myUid, Service.HUADIAO_HOUSE_CONFIG);
            }
        };
        LOGGER.debug("uid 为 {} 的用户修改番剧馆页面背景的部分地址为 {}", myUid, fileName[0]);
        huadiaoHouseDAO.updateHuadiaoHouseBackgroundByUid(myUid, fileName[0]);
        return MODIFY_HUADIAO_HOUSE_BACKGROUND_SUCCEED;
    }

    /**
     * 修改番剧馆页面前景
     * @param request 请求对象
     * @param myUid 我的 uid
     * @param huadiaoHouseForeground 番剧馆页面前景部分地址
     * @return 修改成功返回 modifyHuadiaoHouseForegroundSucceed
     * @throws Exception 可能抛出的异常
     */
    public String modifyHuadiaoHouseForeground(HttpServletRequest request, String myUid, String huadiaoHouseForeground) throws Exception {
        LOGGER.debug("uid 为 {} 的用户尝试修改番剧馆页面前景", myUid);
        // 初始化字符串数组为 huadiaoHouseForeground, 因为前端传来的可能不是文件, 而是字符串
        final String[] fileName = new String[]{huadiaoHouseForeground};
        new FormDataProcessor(request) {
            @Override
            public void fileData(FileItem fileItem) throws Exception {
                fileName[0] = FormDataProcessor.saveFile(fileItem, myUid, Service.HUADIAO_HOUSE_CONFIG);
            }
        };
        LOGGER.debug("uid 为 {} 的用户修改番剧馆页面前景的部分地址为 {}", myUid, fileName[0]);
        huadiaoHouseDAO.updateHuadiaoHouseForegroundByUid(myUid, fileName[0]);
        return MODIFY_HUADIAO_HOUSE_FOREGROUND_SUCCEED;
    }

    /**
     * 修改番剧馆添加番剧卡片背景
     * @param request 请求对象
     * @param myUid 我的 uid
     * @param buildCardBackground 添加卡片背景部分地址, 可能为 null, 为 null 则是文件
     * @return 修改成功返回 modifyHuadiaoHouseBuildCardBackgroundSucceed
     * @throws Exception 可能抛出异常
     */
    public String modifyHuadiaoHouseBuildCardBackground(HttpServletRequest request, String myUid, String buildCardBackground) throws Exception {
        LOGGER.debug("uid 为 {} 的用户尝试修改番剧馆添加卡片背景", myUid);
        // 初始化字符串数组为 buildCardBackground, 因为前端传来的可能不是文件, 而是字符串
        final String[] fileName = new String[]{buildCardBackground};
        new FormDataProcessor(request) {
            @Override
            public void fileData(FileItem fileItem) throws Exception {
                fileName[0] = FormDataProcessor.saveFile(fileItem, myUid, Service.HUADIAO_HOUSE_CONFIG);
            }
        };
        LOGGER.debug("uid 为 {} 的用户修改番剧馆添加卡片背景的部分地址为 {}", myUid, buildCardBackground);
        huadiaoHouseDAO.updateHuadiaoHouseCardBackgroundByUid(myUid, fileName[0]);
        return MODIFY_HUADIAO_HOUSE_BUILD_CARD_BACKGROUND_SUCCEED;
    }

    /**
     * 修改番剧馆标题背景
     * @param myUid 我的 uid
     * @param huadiaoHouseTitleBackground 番剧馆标题背景字符串
     * @return 修改成功返回 modifyHuadiaoHouseTitleBackgroundSucceed
     */
    public String modifyHuadiaoHouseTitleBackground(String myUid, String huadiaoHouseTitleBackground) {
        LOGGER.debug("uid 为 {} 的用户尝试修改番剧馆标题背景", myUid);
        huadiaoHouseDAO.updateHuadiaoHouseTitleBackgroundByUid(myUid, huadiaoHouseTitleBackground);
        return MODIFY_HUADIAO_HOUSE_TITLE_BACKGROUND_SUCCEED;
    }

    /**
     * 根据唯一标识删除番剧
     * @param myUid 我的 uid
     * @param signId 唯一标识
     * @return 删除成功返回 deleteHuadiaoHouseFanjuSucceed
     */
    public String deleteHuadiaoHouseFanju(String myUid, String signId) {
        LOGGER.debug("uid 为 {} 的用户尝试删除 signId 为 {} 的番剧", myUid, signId);
        huadiaoHouseDAO.updateHuadiaoHouseFanjuStatusByUid(myUid, signId);
        return DELETE_HUADIAO_HOUSE_FANJU_SUCCEED;
    }

    /**
     * 修改番剧馆封面边框
     * @param myUid 我的 uid
     * @param borderImgChoice 番剧封面边框选择
     * @return 修改成功返回 modifyHuadiaoHouseCoverBorderSucceed
     */
    public String modifyHuadiaoHouseCoverBorderImg(String myUid, String borderImgChoice) {
        LOGGER.debug("uid 为 {} 的用户尝试修改番剧馆封面边框图片为 {}", myUid, borderImgChoice);
        huadiaoHouseDAO.updateHuadiaoHouseChoiceByUid(myUid, borderImgChoice);
        return MODIFY_HUADIAO_HOUSE_COVER_BORDER_SUCCEED;
    }

    /**
     * 修改番剧馆标题颜色
     * @param myUid 我的 uid
     * @param huadiaoHouseTitleColor 标题颜色字符串
     * @return 修改成功返回 modifyHuadiaoHouseTitleColorSucceed
     */
    public String modifyHuadiaoHouseTitleColor(String myUid, String huadiaoHouseTitleColor) {
        LOGGER.debug("uid 为 {} 的用户尝试修改番剧馆标题颜色", myUid);
        huadiaoHouseDAO.updateHuadiaoHouseTitleColorByUid(myUid, huadiaoHouseTitleColor);
        return MODIFY_HUADIAO_HOUSE_TITLE_COLOR_SUCCEED;
    }

}
