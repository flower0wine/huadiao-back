package com.huadiao.web.controller;

import com.huadiao.utils.microspring.xmlresolver.MethodSignatureMap;
import com.huadiao.web.service.impl.HuadiaoHouseServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description
 */
public class HuadiaoHouseController {
    private HuadiaoHouseServiceImpl huadiaoHouseServiceImpl;

    /**
     * 获取番剧馆信息
     * @param request 请求对象
     * @param authorUid 作者 uid
     * @return 返回番剧馆信息
     */
    @MethodSignatureMap(methodSignature = "getFanjuInfer")
    public String getHuadiaoHouseInformation(HttpServletRequest request, String authorUid) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        String myUserId = (String) session.getAttribute("userId");
        return huadiaoHouseServiceImpl.getHuadiaoHouseInformation(myUid, myUserId, authorUid);
    }

    /**
     * 添加新番剧
     * @param request 请求对象
     * @param fanjuName 番剧名称
     * @param fanjuCover 番剧封面部分地址
     * @param signId 唯一标识
     * @return 添加成功返回 addNewFanjuSucceed
     */
    @MethodSignatureMap(methodSignature = "buildNewFanju")
    public String addNewFanju(HttpServletRequest request, String signId, String fanjuName, String fanjuCover) throws Exception {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        String myUserId = (String) session.getAttribute("userId");
        return huadiaoHouseServiceImpl.addNewFanju(request, signId, myUid, myUserId, fanjuName, fanjuCover);
    }

    /**
     * 修改番剧馆页面背景
     * @param request 请求对象
     * @param background 番剧馆页面背景, 获取到就是字符串
     * @return 修改成功返回 modifyHuadiaoHouseBackgroundSucceed
     * @throws Exception 可能抛出异常
     */
    @MethodSignatureMap(methodSignature = "modifyHuadiaoBackground")
    public String modifyHuadiaoHouseBackground(HttpServletRequest request, String background) throws Exception {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return huadiaoHouseServiceImpl.modifyHuadiaoHouseBackground(request, myUid, background);
    }

    /**
     * 修改番剧馆页面前景
     * @param request 请求对象
     * @param background 番剧馆页面前景, 获取到就是字符串
     * @return 修噶成功返回 modifyHuadiaoHouseForegroundSucceed
     * @throws Exception 可能抛出异常
     */
    @MethodSignatureMap(methodSignature = "modifyHuadiaoForeground")
    public String modifyHuadiaoHouseForeground(HttpServletRequest request, String background) throws Exception {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return huadiaoHouseServiceImpl.modifyHuadiaoHouseForeground(request, myUid, background);
    }

    /**
     * 修改番剧馆添加番剧卡片背景
     * @param request 请求对象
     * @param background 卡片颜色字符串, 可能为 null, 为 null 则是文件
     * @return 修改成功返回 modifyHuadiaoHouseBuildCardBackgroundSucceed
     */
    @MethodSignatureMap(methodSignature = "modifyHuadiaoCardBackground")
    public String modifyHuadiaoHouseBuildFanjuBackground(HttpServletRequest request, String background) throws Exception {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return huadiaoHouseServiceImpl.modifyHuadiaoHouseBuildCardBackground(request, myUid, background);
    }

    /**
     * 修改番剧馆标题背景
     * @param request 请求对象
     * @param background 标题背景字符串
     * @return 修改成功返回 modifyHuadiaoHouseTitleBackgroundSucceed
     */
    @MethodSignatureMap(methodSignature = "modifyTitleBackground")
    public String modifyHuadiaoHouseTitleBackground(HttpServletRequest request, String background) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return huadiaoHouseServiceImpl.modifyHuadiaoHouseTitleBackground(myUid, background);
    }

    /**
     * 根据唯一标识删除番剧
     * @param request 请求对象
     * @param signId 唯一标识
     * @return 删除成功返回 deleteHuadiaoHouseFanjuSucceed
     */
    @MethodSignatureMap(methodSignature = "deleteFanju")
    public String deleteHuadiaoHouseFanju(HttpServletRequest request, String signId) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return huadiaoHouseServiceImpl.deleteHuadiaoHouseFanju(myUid, signId);
    }

    /**
     * 修改番剧馆封面边框
     * @param request 请求对象
     * @param borderImgChoice 封面边框选择
     * @return 修改成功返回 modifyHuadiaoHouseCoverBorderSucceed
     */
    @MethodSignatureMap(methodSignature = "modifyBorderImg")
    public String modifyHuadiaoHouseCoverBorderImg(HttpServletRequest request, String borderImgChoice) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return huadiaoHouseServiceImpl.modifyHuadiaoHouseCoverBorderImg(myUid, borderImgChoice);
    }

    /**
     * 修改番剧馆标题背景
     * @param request 请求对象
     * @param titleColor 标题背景字符串
     * @return 修改成功返回 modifyHuadiaoHouseTitleColorSucceed
     */
    @MethodSignatureMap(methodSignature = "modifyTitleColor")
    public String modifyHuadiaoHouseTitleColor(HttpServletRequest request, String titleColor) {
        HttpSession session = request.getSession();
        String myUid = (String) session.getAttribute("uid");
        return huadiaoHouseServiceImpl.modifyHuadiaoHouseTitleColor(myUid, titleColor);
    }
}
