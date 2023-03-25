package com.huadiao.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.MessageSettings;
import com.huadiao.pojo.UserSettingsInfer;
import com.huadiao.utils.log.Loggers;
import com.huadiao.web.dao.UserSettingsDAO;
import com.huadiao.web.service.UserSettingsService;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description
 */
public class UserSettingsServiceImpl implements UserSettingsService, Loggers {
    private UserSettingsDAO userSettingsDAO;

    /**
     * 获取我的用户设置
     * @param myUid 我的 uid
     * @return 返回用户设置
     */
    public String getMyUserSettings(String myUid) {
        LOGGER.debug("uid 为 {} 的用户尝试获取自己的用户设置", myUid);
        UserSettingsInfer userSettingsInfer = userSettingsDAO.selectUserSettingsByUid(myUid);
        return JSON.toJSONString(userSettingsInfer);
    }

    /**
     * 获取我的消息设置
     * @param myUid 我的 uid
     * @return 返回我的消息设置
     */
    public String getMyMessageSettings(String myUid) {
        LOGGER.debug("uid 为 {} 的用户尝试获取自己的消息设置", myUid);
        MessageSettings messageSettings = userSettingsDAO.selectMessageSettingsByUid(myUid);
        return JSON.toJSONString(messageSettings);
    }

    /**
     * 更改用户设置
     * @param myUid 我的 uid
     * @param option 要更改的选项
     * @return 修改成功返回 modifyUserSettingsSucceed
     */
    public String modifyMyUserSettings(String myUid, String option) {
        LOGGER.debug("uid 为 {} 的用户修改用户设置, 修改的参数 option 为 {}",myUid, option);
        userSettingsDAO.updateUserSettingsByUid(myUid, option);
        return MODIFY_USER_SETTINGS_SUCCEED;
    }
}
