package com.huadiao.web.service;

public interface HuadiaoHouseService extends Service {

    /**
     * 添加新番剧成功
     */
    String ADD_NEW_FANJU_SUCCEED = "addNewFanjuSucceed";


    /**
     * 修改番剧馆页面背景成功
     */
    String MODIFY_HUADIAO_HOUSE_BACKGROUND_SUCCEED = "modifyHuadiaoHouseBackgroundSucceed";

    /**
     * 修改番剧馆页面前景成功
     */
    String MODIFY_HUADIAO_HOUSE_FOREGROUND_SUCCEED = "modifyHuadiaoHouseForegroundSucceed";

    /**
     * 修改番剧封面边框成功
     */
    String MODIFY_HUADIAO_HOUSE_COVER_BORDER_SUCCEED = "modifyHuadiaoHouseCoverBorderSucceed";

    /**
     * 修改添加番剧的添加卡片背景成功
     */
    String MODIFY_HUADIAO_HOUSE_BUILD_CARD_BACKGROUND_SUCCEED = "modifyHuadiaoHouseBuildCardBackgroundSucceed";

    /**
     * 修改番剧馆标题背景成功
     */
    String MODIFY_HUADIAO_HOUSE_TITLE_COLOR_SUCCEED = "modifyHuadiaoHouseTitleColorSucceed";

    /**
     * 修改番剧馆标题背景成功
     */
    String MODIFY_HUADIAO_HOUSE_TITLE_BACKGROUND_SUCCEED = "modifyHuadiaoHouseTitleBackgroundSucceed";

    /**
     * 删除番剧馆番剧成功
     */
    String DELETE_HUADIAO_HOUSE_FANJU_SUCCEED = "deleteHuadiaoHouseFanjuSucceed";
}
