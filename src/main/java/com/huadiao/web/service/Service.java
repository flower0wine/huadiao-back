package com.huadiao.web.service;

public interface Service {
    /**
     * 统一未知请求返回值
     */
    String ERROR = "error";

    /**
     * 下载后要超出的硬盘位置
     */
    String HARDWARE_PATH = "A:/huadiao_images";

    /**
     * 个人主页保存地址
     */
    String HOMEPAGE_BACKGROUND = HARDWARE_PATH + "/homepageBackground";

    /**
     * 用户头像保存地址
     */
    String USER_AVATAR = HARDWARE_PATH + "/userAvatar";

    /**
     * 用户番剧馆基本图片保存地址
     */
    String HUADIAO_HOUSE_CONFIG = HARDWARE_PATH + "/huadiaoHouseConfig";

    /**
     * 用户番剧封面保存地址
     */
    String HUADIAO_HOUSE_FANJU = HARDWARE_PATH + "/huadiaoHouseFanju";
}
