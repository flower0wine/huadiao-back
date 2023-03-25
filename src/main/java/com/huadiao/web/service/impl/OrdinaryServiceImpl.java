package com.huadiao.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.IndexInfer;
import com.huadiao.pojo.ResponseMessage;
import com.huadiao.pojo.User;
import com.huadiao.utils.CreateUserId;
import com.huadiao.utils.GeneratorCookie;
import com.huadiao.utils.log.Loggers;
import com.huadiao.web.dao.*;
import com.huadiao.web.service.OrdinaryService;
import com.huadiao.web.service.UserInferService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName 花凋
 * @description 业务处理层面, 主要进行数据处理外的其他的操作, 该类属于通用级别
 */
public class OrdinaryServiceImpl implements OrdinaryService, Loggers {
    private UserDAO userDAO;
    private HuadiaoHouseDAO huadiaoHouseDAO;
    private HomepageInferDAO homepageInferDAO;
    private UserSettingsDAO userSettingsDAO;
    private UserInferDAO userInferDAO;
    private FollowsFansDAO followsFansDAO;

    /**
     * 获取用户状态, 是已登录状态还是未登录状态
     *
     * @param cookies  用户 cookies
     * @param myUserId 用户 userId
     * @param myUid    用户 uid
     * @return 返回处理结果, 如果登录返回 json 格式数据, 未登录返回 error 字符串
     */
    public String getUserStatus(Cookie[] cookies, String myUserId, String myUid) {
        // 响应信息, 初始化为 error
        String processResult = ERROR;
        LOGGER.info("uid 为 {}, userId 为 {} 的用户尝试获取登录状态", myUid, myUserId);
        if (cookies == null || cookies.length < 1) {
            processResult = JSON.toJSONString(new ResponseMessage(processResult, ResponseMessage.ERROR_MESSAGE));
        } else {
            // 判断用户是否登录过，以维持登录状态
            for (Cookie cookie : cookies) {
                String str = cookie.getName();
                if (COOKIE_KEY_USER_ID.equals(str)) {
                    if (myUserId != null && myUserId.equals(cookie.getValue())) {
                        IndexInfer indexInfer = new IndexInfer();
                        List<Integer> list = followsFansDAO.selectFollowsAndFansAmountByUserId(myUserId);
                        String nickname = userInferDAO.selectNicknameByUid(myUid);
                        String userAvatar = homepageInferDAO.selectUserAvatar(myUid);

                        // 填充信息
                        indexInfer.setFollow(list.get(0));
                        indexInfer.setFans(list.get(1));
                        indexInfer.setNickname(nickname);
                        indexInfer.setUid(myUid);
                        indexInfer.setUser_avatar(userAvatar);
                        // 转换为 JSON 格式
                        processResult = JSON.toJSONString(indexInfer);
                        break;
                    }
                }
            }
        }
        LOGGER.info("uid 为 {}, userId 为 {} 的用户成功获取登录状态", myUid, myUserId);
        return processResult;
    }

    /**
     * 新用户注册
     *
     * @param username  注册的用户名
     * @param password  注册的密码
     * @param checkCode 注册的验证码
     * @return 1. 如果用户存在返回 sameUsername
     * 2. 如果用户名不符合要求返回 wrongUsername
     * 3. 如果密码不符合要求返回 wrongPassword
     * 4. 如果验证码错误返回 wrongCode
     * 5. 如果用户名未填写返回 nullUsername
     * 6. 如果密码未填写返回 nullPassword
     * 7. 如果验证码未填写返回 nullCheckCode
     * 8. 注册成功返回 succeedRegister
     */
    public String registerNewUser(HttpSession session, String username, String password, String checkCode) {
        User user = userDAO.selectUserByUsername(username);
        if (user != null) {
            return SAME_USERNAME;
        }
        // 账号或密码或验证码为 null
        if (password == null) {
            return NULL_PASSWORD;
        } else if (checkCode == null) {
            return NULL_CHECK_CODE;
        } else if (username == null) {
            return NULL_USERNAME;
        }
        LOGGER.info("有用户尝试注册!, username 为 {}, password 为 {}", username, password);
        // 用户名长度是否符合要求
        boolean checkUsernameLength = USERNAME_MIN_LENGTH <= username.length() &&
                username.length() <= USERNAME_MAX_LENGTH;
        // 用户名是否包含不允许的字符
        boolean usernameHasNotAllowedChar = Pattern.matches(USERNAME_REGEX, username);
        if (!(checkUsernameLength) || usernameHasNotAllowedChar) {
            return WRONG_USERNAME;
        }
        // 密码长度是否符合要求
        boolean checkPasswordLength = PASSWORD_MIN_LENGTH <= password.length() &&
                password.length() <= PASSWORD_MAX_LENGTH;
        // 密码是否包含不允许的字符
        boolean passwordHasNotAllowedChar = Pattern.matches(PASSWORD_REGEX, password);
        if (!(checkPasswordLength) || passwordHasNotAllowedChar) {
            // 匹配成功说明密码不符合要求
            if (Pattern.matches(PASSWORD_REGEX, password)) {
                return WRONG_PASSWORD;
            }
        }
        // 获取之前保存的 checkCode
        System.out.println(session);
        String checkCodeCopy = session.getAttribute("checkCode").toString();
        if (checkCodeCopy.equals(checkCode.toUpperCase())) {
            // 分配用户 ID
            String userId = CreateUserId.createUserId();
            boolean isInsert = true;
            // 因为分配的 userId 可能重复, 会抛出错误, 所以使用循环, 直到加入为止
            while (isInsert) {
                try {
                    userId = CreateUserId.createUserId();
                    userDAO.insertNewUser(userId, username, password);
                    isInsert = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 查找用户 uid
            String uid = userDAO.selectUidByUserId(userId);
            // 加入用户信息
            userInferDAO.insertUserInfer(uid, userId, userId,
                    UserInferService.USER_DEFAULT_CANVASES,
                    UserInferService.USER_DEFAULT_SEX,
                    UserInferService.USER_DEFAULT_BORN_DATE,
                    UserInferService.USER_DEFAULT_SCHOOL
            );
            // 新增番剧馆
            huadiaoHouseDAO.insertHuadiaoHouse(uid, userId);
            // 新增个人主页
            homepageInferDAO.insertHomepageInfer(uid, userId);
            // 新增用户设置
            userSettingsDAO.insertUserSettings(uid, userId);
            LOGGER.info("新用户加入! uid 为 {}, userId 为 {}",uid, userId);
            // 向客户端发送注册成功
            return REGISTER_SUCCEED;
        } else {
            // 验证码错误
            return WRONG_CODE;
        }
    }

    /**
     * 用户登录
     *
     * @param response 响应对象
     * @param session  HttpSession 对象
     * @param username 用户名
     * @param password 用户密码
     * @return 1. 如果用户不存在返回 noUser
     * 2. 登录成功返回 loginSucceed
     */
    public String userLogin(HttpServletResponse response, HttpSession session, String username, String password) {
        User user = userDAO.selectUserByUsernameAndPassword(username, password);
        // 查找不到用户
        if (user == null) {
            return NO_USER;
        }
        LOGGER.info("uid 为 {}, username 为 {}, userId 为 {} 的用户尝试登录账号", user.getUid(), user.getUsername(), user.getUserId());
        // 设置 cookie 保持用户登录状态，这里用 userId 作为识别的依据
        response.addCookie(GeneratorCookie.newMoreProCookie(COOKIE_KEY_USER_ID, user.getUserId(), 31536000, true, "/"));
        // 存入 HttpSession 以便后续使用
        session.setAttribute("username", username);
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("uid", user.getUid());
        return LOGIN_SUCCEED;
    }

    /**
     * 用户登出
     *
     * @param session  HttpSession 对象
     * @param response 响应对象
     * @return 登出成功返回 loginOutSucceed
     */
    public String userLoginOut(HttpSession session, HttpServletResponse response) {
        String userId = (String) session.getAttribute("userId");
        String uid = (String) session.getAttribute("uid");
        if (userId != null) {
            LOGGER.info("uid 为 {}, userId 为 {} 的用户尝试登出",  uid, userId);
            // 删除 cookie, 设置 cookie 过期时间为 0 即可
            response.addCookie(GeneratorCookie.newMoreProCookie(COOKIE_KEY_USER_ID, "", 0, true, "/"));
            // 退出登录销毁 session
            session.invalidate();
            return LOGIN_OUT_SUCCEED;
        } else {
            return ERROR;
        }
    }
}
