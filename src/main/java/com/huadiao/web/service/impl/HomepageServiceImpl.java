package com.huadiao.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.HomepageInfer;
import com.huadiao.pojo.UserInfer;
import com.huadiao.utils.log.Loggers;
import com.huadiao.utils.microspring.requestprocess.fileupload.FormDataProcessor;
import com.huadiao.web.dao.FollowsFansDAO;
import com.huadiao.web.dao.HomepageInferDAO;
import com.huadiao.web.dao.UserInferDAO;
import com.huadiao.web.service.AbstractFollowsFansService;
import com.huadiao.web.service.HomepageService;
import com.huadiao.web.service.Service;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @projectName 花凋
 * @author flowerwine
 * @description 业务处理, 逻辑处理, 用户个人主页
 * @version 1.1
 */
public class HomepageServiceImpl implements HomepageService, Loggers {
    private HomepageInferDAO homepageInferDAO;
    private UserInferDAO userInferDAO;
    private FollowsFansDAO followsFansDAO;

    /**
     * 获取指定用户的个人主页所有信息, 并更新被浏览的人的个人主页的浏览量
     *
     * @param myUserId 我的 userId
     * @param myUid    我的 uid
     * @param otherUid 别人的 uid
     * @return 返回个人主页所有信息
     */
    public String getHomepageInferByUid(String myUserId, String myUid, String otherUid) {
        HomepageInfer homepageInfer = homepageInferDAO.selectSectionHomepageInferByUid(otherUid);
        // 获取被浏览的人的用户信息
        UserInfer userInfer = userInferDAO.selectUserInferByUid(otherUid);
        // 查询被浏览的人的关注和粉丝的数量
        List<Integer> followFanAmount = followsFansDAO.selectFollowsAndFansAmountByUserId(userInfer.getUserId());
        // 判断被浏览的人是不是自己
        if (myUid.equals(otherUid)) {
            homepageInfer.setMe(true);
        } else {
            homepageInfer.setMe(false);
            // 不是自己增加别人的浏览量, 一个人只增加一次, 第二次浏览则不会增加, 而是更新
            homepageInferDAO.insertHomepageViews(myUid, otherUid);
            // 查询我和他的关系
            List<Integer> relationList = followsFansDAO.selectRelationByBothUserId(myUserId, userInfer.getUserId());
            boolean[] relationArray = AbstractFollowsFansService.getRelation(relationList);

            // 设置关注关系
            homepageInfer.setFollowing(relationArray[0]);
            homepageInfer.setFollowed(relationArray[1]);
        }

        // 设置关注与粉丝数量
        homepageInfer.setFollowNumber(followFanAmount.get(0));
        homepageInfer.setFansNumber(followFanAmount.get(1));
        // 设置用户基本信息
        homepageInfer.setNickname(userInfer.getNickname());
        homepageInfer.setCanvases(userInfer.getCanvases());
        homepageInfer.setBornDate(userInfer.getBornDate());
        homepageInfer.setSchool(userInfer.getSchool());
        homepageInfer.setSex(userInfer.getSex());
        // 设置用户基本信息
        LOGGER.debug("userId, uid 分别为 {}, {} 的用户成功获取 userId, uid 分别为 {}, {} 的用户的个人主页信息！", myUserId, myUid, userInfer.getUserId(), otherUid);
        return JSON.toJSONString(homepageInfer);
    }

    /**
     * 根据 uid 更改个人主页背景
     * @param myUid 我的 uid
     * @return 修改成功返回 modifyHomepageBackgroundSucceed
     */
    public String modifyMyHomepageBackground(HttpServletRequest request, String myUid) throws Exception {
        LOGGER.debug("uid 为 {} 的用户尝试更新自己的个人主页背景", myUid);
        // 匿名内部类要求使用外部定义的变量全部是 final
        final String[] fileName = new String[1];
        new FormDataProcessor(request) {
            @Override
            public void fileData(FileItem fileItem) throws Exception {
                fileName[0] = FormDataProcessor.saveFile(fileItem, myUid, Service.HOMEPAGE_BACKGROUND);
                LOGGER.debug("uid 为 {} 的用户保存的个人主页部分链接为 {}", myUid, fileName[0]);
            }
        };
        // 保存图片部分路径
        homepageInferDAO.updatePageBackgroundByUid(myUid, fileName[0]);
        return MODIFY_HOMEPAGE_BACKGROUND_SUCCEED;
    }

    /**
     * 修改用户头像
     * @param request 请求对象
     * @param myUid 我的 uid
     * @return 修改成功返回 modifyUserAvatarSucceed
     * @throws Exception 可能抛出异常
     */
    public String modifyMyUserAvatar(HttpServletRequest request, String myUid) throws Exception {
        LOGGER.debug("uid 为 {} 的用户尝试修改自己的头像", myUid);
        // 匿名内部类要求使用外部定义的变量全部是 final
        final String[] fileName = new String[1];
        new FormDataProcessor(request) {
            @Override
            public void fileData(FileItem fileItem) throws Exception {
                fileName[0] = FormDataProcessor.saveFile(fileItem, myUid, Service.USER_AVATAR);
                LOGGER.debug("uid 为 {} 的用户保存的头像部分链接为 {}", myUid, fileName[0]);
            }
        };
        // 保存图片部分路径
        homepageInferDAO.updateUserAvatarByUid(myUid, fileName[0]);
        return MODIFY_USER_AVATAR_SUCCEED;
    }
}
