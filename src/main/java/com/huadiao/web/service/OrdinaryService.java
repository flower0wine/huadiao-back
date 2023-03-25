package com.huadiao.web.service;

public interface OrdinaryService extends Service {
    /**
     * 用户 cookie 的键名
     */
    String COOKIE_KEY_USER_ID = "User_ID";

    /**
     * 相同的用户名
     */
    String SAME_USERNAME = "sameUsername";

    /**
     * 注册成功
     */
    String REGISTER_SUCCEED = "succeedRegister";

    /**
     * 错误的验证码
     */
    String WRONG_CODE = "wrongCode";

    /**
     * 不符合要求的用户名
     */
    String WRONG_USERNAME = "wrongUsername";

    /**
     * 用户名正则表达式, 要求不能出现除字母、数字或下划线以外的字符
     */
    String USERNAME_REGEX = "[^0-9a-zA-Z_]";

    /**
     * 密码正则表达式, 要求不能出现除字母、数字或下划线以及 (!, -, @) 以外的字符
     */
    String PASSWORD_REGEX = "[^0-9a-zA-Z_!-@]";

    /**
     * 用户名最大长度
     */
    int USERNAME_MAX_LENGTH = 20;

    /**
     * 用户名最小长度
     */
    int USERNAME_MIN_LENGTH = 8;

    /**
     * 密码最大长度
     */
    int PASSWORD_MAX_LENGTH = 32;

    /**
     * 密码最小长度
     */
    int PASSWORD_MIN_LENGTH = 8;

    /**
     * 不符合要求的密码
     */
    String WRONG_PASSWORD = "wrongPassword";

    /**
     * 密码为空
     */
    String NULL_PASSWORD = "nullPassword";

    /**
     * 验证码为空
     */
    String NULL_CHECK_CODE = "nullCheckCode";

    /**
     * 用户名为 null
     */
    String NULL_USERNAME = "nullUsername";

    /**
     * 没有这个用户
     */
    String NO_USER = "noUser";

    /**
     * 登录成功
     */
    String LOGIN_SUCCEED = "loginSucceed";

    /**
     * 登出成功
     */
    String LOGIN_OUT_SUCCEED = "loginOutSucceed";

}
