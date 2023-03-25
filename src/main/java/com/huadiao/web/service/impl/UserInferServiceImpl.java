package com.huadiao.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.UserInfer;
import com.huadiao.utils.log.Loggers;
import com.huadiao.web.dao.UserInferDAO;
import com.huadiao.web.service.UserInferService;

/**
 * @projectName 花凋
 * @author flowerwine
 * @description 业务层面, 进行逻辑处理, 处理用户信息
 * @version 1.1
 */
public class UserInferServiceImpl implements UserInferService, Loggers {
    private UserInferDAO userInferDAO;

    /**
     * 获取我的用户信息
     * @param myUid 我的 uid
     * @return 返回用户信息
     */
    public String getMyUserInfer(String myUid) {
        LOGGER.debug("uid 为 {} 的用户尝试获取自己的用户信息", myUid);
        UserInfer userInfer = userInferDAO.selectUserInferByUid(myUid);
        return JSON.toJSONString(userInfer);
    }

    /**
     * 修改我的用户信息
     * @param myUid 我的 uid
     * @param nickname 昵称
     * @param bornDate 出生日期
     * @param sex 性别
     * @param canvases 个人介绍
     * @param school 学校
     * @return 用户信息修改成功返回 userInferUpdateSucceed
     */
    public String updateMyUserInfer(String myUid, String nickname, String bornDate, String sex, String canvases, String school) {
        userInferDAO.updateUserInferByUid(myUid, nickname, bornDate, sex, canvases, school);
        LOGGER.debug("uid 为 {} 的用户尝试修改自己的用户信息",myUid);
        return USER_INFER_UPDATE_SUCCEED;
    }
}
