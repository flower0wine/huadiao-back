package com.huadiao.web.service;

public interface UserInferService extends Service {

    /**
     * 用户信息修改成功
     */
    String USER_INFER_UPDATE_SUCCEED = "userInferUpdateSucceed";

    /**
     * 用户默认的个性签名
     */
    String USER_DEFAULT_CANVASES = "我是新来的小伙伴，请多多关照！";

    /**
     * 用户默认的性别, 未知
     */
    String USER_DEFAULT_SEX = "0";

    /**
     * 用户默认的出生日期
     */
    String USER_DEFAULT_BORN_DATE = null;

    /**
     * 用户默认的学校
     */
    String USER_DEFAULT_SCHOOL = null;

}
