package com.huadiao.web;


import com.huadiao.mapper.*;
import com.huadiao.pojo.*;
import com.huadiao.pojo.systemSpecific.UserAllInfos;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 06 29 11:22
 * <p>
 * <p>
 * 该类的存在是为了降低代码的重复率，是一个专门操作数据库的类，里面提供了许多操作数据库的静态方法
 */
public class mysqlProcess {

    static String resource = "huadiaoconfig/mybatis-config.xml";

    static InputStream inputStream;

    static {
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    对于不同用户，一个 sqlSessionFactory 就可以应对，但 session 必须要有多个
    static SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


    //    检查是否存在相同用户名
    public static boolean checkSameUsername(String username) {
//        该 session 用来执行 sql 语句，传入 true 自动提交事务
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectByUsernameUser(username);

        sqlSession.close();

        return user != null;
    }

    // 判断用户是否存在
    public static boolean checkUserSurvive(String username, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectUserSurvive(username, password);

        sqlSession.close();

        return user != null;
    }

    //    创建新用户
    public static void insertNewUser(String userId, String username, String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        userMapper.insertUser(userId, username, password);

        sqlSession.commit();

        sqlSession.close();
    }

    // 根据用户名查找 userId
    public static String selectUserIdByUsername(String username) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        String userId = userMapper.selectUserIdByUsername(username);

        sqlSession.close();

        return userId;
    }

    // 根据 userID 查找用户信息
    public static UserInfer selectUserInferByUserId(String userId) {

        return null;
    }

    // 根据 uid 查找用户信息
    public static HomepageInfer selectAllInferByUid(String uid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HomepageInferMapper homepageInferMapper = sqlSession.getMapper(HomepageInferMapper.class);

        HomepageInfer homepageInfer = homepageInferMapper.selectAllByUid(uid);

        sqlSession.close();

        return homepageInfer;
    }

    // 新增用户信息
    public static void insertUserInfer(String uid, String userId, String nickname, String canvases, String sex, String bornDate, String school) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserInferMapper userInferMapper = sqlSession.getMapper(UserInferMapper.class);

        userInferMapper.insertUserInfer(uid, userId, nickname, canvases, sex, bornDate, school);

        sqlSession.commit();

        sqlSession.close();
    }

    // 根据 userId 更改用户信息
    public static void updateUserInferByUserId(String userId, String nickname, String canvases, String sex, String bornDate, String school) {
    }

    // 修改登录时间
    public static void updateUserLoginTime(String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        userMapper.updateLoginTime(userId);

        sqlSession.commit();

        sqlSession.close();
    }

    // 根据用户 userId 查找 uid（user_infer表）
    public static String selectUidByUserIdFromUserInfer(String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserInferMapper userInferMapper = sqlSession.getMapper(UserInferMapper.class);

        String uid = userInferMapper.selectUidByUserId(userId);

        sqlSession.close();

        return uid;
    }

    // 根据用户 userId 查找 uid（user表）
    public static String selectUidByUserIdFromUser(String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        String uid = userMapper.selectUidByUserId(userId);

        sqlSession.close();

        return uid;
    }

    // 根据用户 uid 查找 userId（user_infer表）
    public static String selectUserIdByUid(String uid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        String userId = userMapper.selectUserIdByUid(uid);

        sqlSession.close();

        return userId;
    }

    // 根据 userId 查找 nickname
    public static String selectNicknameByUserId(String userId) {
        return null;
    }

    // 新增个人主页部分信息(homepage 表)
    public static void insertAllByUserId(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HomepageInferMapper homepageInferMapper = sqlSession.getMapper(HomepageInferMapper.class);

        homepageInferMapper.insertAllByUserId(userId, uid);

        sqlSession.commit();

        sqlSession.close();
    }

    // 新增个人主页浏览量
    public static void insertHomepageVisit(String uid, String visitUid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HomepageInferMapper homepageInferMapper = sqlSession.getMapper(HomepageInferMapper.class);

        homepageInferMapper.insertHomepageVisit(uid, visitUid);

        sqlSession.commit();

        sqlSession.close();
    }

    // 更新个人主页背景
    public static void updatePageBackgroundByUserId(String userId, String pageBackground) {
    }

    // 上传头像
    public static void updateUserAvatar(String userId, String userAvatar) {
    }

    // 获取用户头像
    public static String selectUserAvatarByUserId(String userId) {
        return null;
    }

    // 插入新的番剧页面配置
    public static void insertPanoperaSettings(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);

        huadiaoHouseMapper.insertPanoperaSettings(uid, userId);

        sqlSession.commit();

        sqlSession.close();
    }

    // 更新番剧页面标题颜色
    public static void updateTitleColor(String userId, String titleColor) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 更新番剧页面标题背景
    public static void updateTitleBackground(String userId, String titleBackground) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 更新番剧页面背景
    public static void updatePageTwoBackground(String userId, String pageTwoBackground) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 更新番剧页面前景
    public static void updatePageTwoForeground(String userId, String pageTwoForeground) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 更新番剧页面图片边框
    public static void updateBorderImg(String userId, String borderImg) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 更新番剧页面卡片背景
    public static void updateCardColor(String userId, String cardBackground) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 加入新的番剧
    public static void insertFanjuByUserId(String uid, String userId, String fanjuName, String fanjuCover) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 删除番剧
    public static void updateFanjuByUserIdAndAddDate(String userId, String addDate) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HuadiaoHouseMapper huadiaoHouseMapper = sqlSession.getMapper(HuadiaoHouseMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 获取页面除番剧外的信息
    public static List<HuadiaoHouseInfer> selectPanoperaFanjusInferByUserId(String userId) {

        return null;
    }

    // 建立两人的关系，如果关系已经建立，则将建立一人关注另一人的关系
    public static void insertRelationByBothId(String uid, String userId, String followedUid, String sessionId, String gratefulMessage) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        FollowFansMapper followFansMapper = sqlSession.getMapper(FollowFansMapper.class);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);

        String followedId = userMapper.selectUserIdByUid(followedUid);

        messageMapper.insertPrivacyUser(uid, followedUid, sessionId, gratefulMessage);

        followFansMapper.insertRelationByBothUserIdAndUid(uid, userId, followedUid, followedId);

        sqlSession.commit();

        sqlSession.close();
    }

    // 删除私信对象
    public static void deletePrivacyUser(String uid, String privacyUid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);

        messageMapper.deletePrivacyUser(uid, privacyUid);

        sqlSession.commit();

        sqlSession.close();
    }

    // 取消两人中一方对另一方的关注
    public static void updateRelationByBothId(String userId, String otherUid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        FollowFansMapper followFansMapper = sqlSession.getMapper(FollowFansMapper.class);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        String followedId = userMapper.selectUserIdByUid(otherUid);

        followFansMapper.updateRelationByBothUserId(userId, followedId);

        sqlSession.commit();

        sqlSession.close();
    }

    // 获取主页信息
    public static HomepageInfer selectHomepageInfer(String otherUid, String myUid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HomepageInferMapper homepageInferMapper = sqlSession.getMapper(HomepageInferMapper.class);

        HomepageInfer homepageInfer = homepageInferMapper.selectAllByUid(otherUid);

        // 这个集合返回值如果为空，两人没有任何关系，如果是1，说明当前用户关注他，如果是2，说明当前用户被他关注，如果是 1和2 ，说明两人互粉
        List<Integer> integers = mysqlProcess.selectRelationByBothId(userId, homepageInfer.getUserId());

        // 获取用户关注，粉丝数量
        List<Integer> integers1 = mysqlProcess.countFollowAndFansByUserId(homepageInfer.getUserId());
        homepageInfer.setFollowNumber(integers1.get(0));
        homepageInfer.setFansNumber(integers1.get(1));

        // 如果是查询我自己的主页
        if (myUid.equals(otherUid)) {
            homepageInfer.setMe(true);
        } else {
            homepageInfer.setMe(false);
        }

        sqlSession.close();

        return homepageInfer;
    }

    // 获取两个人的关系
    public static List<Integer> selectRelationByBothId(String userId, String followedId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        FollowFansMapper followFansMapper = sqlSession.getMapper(FollowFansMapper.class);

        List<Integer> integers = followFansMapper.selectRelationByBothUserId(userId, followedId);

        sqlSession.close();

        return integers;
    }

    // 获取关注信息
    public static Map<String, Object> selectFollowFansInfer(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        FollowFansMapper followFansMapper = sqlSession.getMapper(FollowFansMapper.class);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        String targetUserId = userMapper.selectUserIdByUid(uid);

        List<FollowsInfer> follow = followFansMapper.selectFollowsByUid(targetUserId);


        Map<String, Object> map = new HashMap<>();
        map.put("follow", follow);

        if (targetUserId.equals(userId)) {
            map.put("me", true);
        } else {
            map.put("me", false);
        }

        sqlSession.close();

        return map;
    }

    // 获取用户关注和粉丝的数量
    public static List<Integer> countFollowAndFansByUserId(String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        FollowFansMapper followFansMapper = sqlSession.getMapper(FollowFansMapper.class);

        List<Integer> integers = followFansMapper.countFollowAndFansByUserId(userId);

        sqlSession.close();

        return integers;
    }

    // 新增系统消息
    public static String insertSystemMessage(String title, String content) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MessageMapper systemMessageMapper = sqlSession.getMapper(MessageMapper.class);

        systemMessageMapper.insertSystemMessage(title, content);

        // 获取系统消息的主键
        String id = systemMessageMapper.selectPrimaryKey();

        sqlSession.commit();

        sqlSession.close();

        return id;
    }

    // 删除系统消息
    public static void deleteSystemMessage(String systemMessageId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);

        messageMapper.deleteSystemMessage(systemMessageId);

        sqlSession.commit();
        sqlSession.close();
    }

    // 新增笔记
    public static void insertNewNote(String uid, String userId, String noteTitle, String noteContent, String noteAbstract) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);

        noteMapper.insertNewNote(uid, userId, noteTitle, noteContent, noteAbstract);

        sqlSession.commit();

        sqlSession.close();
    }

    // 修改笔记
    public static void updateNoteContent(String userId, String noteTitle, String noteContent, String id, String noteAbstract) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);

        noteMapper.updateNoteContent(userId, noteTitle, noteContent, id, noteAbstract);

        sqlSession.commit();

        sqlSession.close();
    }

    // 删除笔记
    public static void deleteNote(String userId, String id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);

        noteMapper.deleteNote(userId, id);

        sqlSession.commit();

        sqlSession.close();
    }

    // 检索某个用户的所有笔记
    public static Notes selectNotesByUid(String uid) {
       return null;
    }

    // 检索某个用户的某个笔记
    public static NoteDetail selectNoteByUidAndId(String id, String myUid, String userId, String otherUid, String noteId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);
        FollowFansMapper followFansMapper = sqlSession.getMapper(FollowFansMapper.class);

        // 获取笔记信息
        NoteDetail noteDetail = noteMapper.selectNoteByUidAndUserId(otherUid, id, myUid);
        // 这个集合返回值如果为空，两人没有任何关系，如果是1，说明当前用户关注他，如果是2，说明当前用户被他关注，如果是 1和2 ，说明两人互粉

        // 获取全部评论
        List<RootMark> marks = noteMapper.selectNoteMark(noteId, myUid);
        noteDetail.setRootMarkList(marks);

        sqlSession.close();

        return noteDetail;
    }

    // 新增笔记不喜欢的数量
    public static void insertNoteUnlike(String authorUid, String noteId, String unlikeUid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);

        noteMapper.insertNoteUnlike(authorUid, noteId, unlikeUid);

        sqlSession.commit();

        sqlSession.close();
    }

    // 更新笔记获赞数
    public static void insertNoteLike(String authorUid, String noteId, String likeUid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);

        noteMapper.insertNoteLike(authorUid, noteId, likeUid);

        sqlSession.commit();

        sqlSession.close();
    }

    // 新增回复作者的笔记
    public static void insertRootMark(String noteId, String otherUid, String uid, String userId, String markContent, String rootId, String authorUid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);

        //noteMapper.insertRootMark(noteId, otherUid, uid, userId, markContent, rootId, authorUid);

        sqlSession.commit();

        sqlSession.close();
    }

    // 新增回复评论的评论
    public static void insertSubMark(String noteId, String otherUid, String uid, String userId, String markContent, String rootId, String selfId, String authorUid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);

        //noteMapper.insertSubMark(noteId, otherUid, uid, userId, markContent, rootId, selfId, authorUid);

        sqlSession.commit();

        sqlSession.close();
    }

    public static MessageInfer selectMessageInfer(String myUid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);

        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);

        // 获取所有回复我的评论
        //获取系统消息
        List<SystemMessage> systemMessages = messageMapper.selectSystemMessage();
        // 获取收到的赞
        // 获取信息设置
        //MessageSettings messageSettings = userSettingsMapper.selectMessageSettings(userId);
        // 获取近期消息对象

        // 装填
        MessageInfer messageInfer = new MessageInfer();
        messageInfer.setSystemMessageList(systemMessages);
        //messageInfer.setMessageSettings(messageSettings);

        sqlSession.close();

        return messageInfer;
    }

    // 获取系统消息
    public static List<SystemMessage> selectSystemMessage() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);

        List<SystemMessage> systemMessages = messageMapper.selectSystemMessage();

        sqlSession.close();

        return systemMessages;
    }

    // 更新所有的回复我的为已读
    public static void updateReplyMessageRead(String uid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);

        messageMapper.updateReplyMessageRead(uid);

        sqlSession.commit();

        sqlSession.close();
    }

    // 新增点赞或取消点赞评论
    public static void insertMarkLike(String noteId, String uid, String likeUid, String rootMarkId, String subMarkId) {

    }

    // 新增不喜欢或取消不喜欢评论
    public static void insertMarkUnlike(String noteId, String uid, String likeUid, String rootMarkId, String subMarkId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 记录访问过的笔记, viewedUid 是作者的 uid
    public static void insertViewedNote(String viewedUid, String viewUid, String noteId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HistoryMapper historyMapper = sqlSession.getMapper(HistoryMapper.class);

        // 记入访问历史记录

        sqlSession.commit();

        sqlSession.close();
    }

    // 记录访问过的番剧馆
    public static void insertViewedFanju(String viewedUid, String viewUid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HistoryMapper historyMapper = sqlSession.getMapper(HistoryMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 删除笔记记录
    public static void deleteNoteHistory(String viewedUid, String viewUid, String noteId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HistoryMapper historyMapper = sqlSession.getMapper(HistoryMapper.class);

        historyMapper.deleteNoteHistory(viewedUid, viewUid, noteId);

        sqlSession.commit();

        sqlSession.close();
    }

    // 删除番剧馆记录
    public static void deleteFanjuHistory(String viewedUid, String viewUid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        HistoryMapper historyMapper = sqlSession.getMapper(HistoryMapper.class);

        historyMapper.deleteFanjuHistory(viewedUid, viewUid);

        sqlSession.commit();

        sqlSession.close();
    }

    // 获取历史记录
    public static HistoryInfer selectHistory(String viewUid) {


        return null;

    }

    // 新增笔记收藏
    public static void insertNoteStar(String staredUid, String uid, String noteId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MyStarMapper starMapper = sqlSession.getMapper(MyStarMapper.class);

        starMapper.insertNoteStar(staredUid, uid, noteId);

        sqlSession.commit();

        sqlSession.close();
    }

    // 取消笔记收藏
    public static void cancelNoteStar(String staredUid, String uid, String noteId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MyStarMapper starMapper = sqlSession.getMapper(MyStarMapper.class);

        starMapper.cancelNoteStar(staredUid, uid, noteId);

        sqlSession.commit();

        sqlSession.close();
    }

    // 获取所有的笔记收藏
    public static List<NoteStar> selectNotesStar(String uid) {
        return null;
    }

    // 获取笔记点赞消息
    public static List<NoteMarkLikeMessage> selectLikeMessage(String uid) {
        return null;
    }

    // 删除回复我的消息
    public static void deleteReplyMessage(String rootMarkId, String uid, String noteId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);

        messageMapper.deleteReplyMessage(uid, rootMarkId, noteId);

        sqlSession.commit();

        sqlSession.close();
    }

    // 删除点赞消息提醒
    public static void deleteMarkLikeMessage(String rootMarkId, String subMarkId, String noteId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);

        messageMapper.deleteMarkLikeMessage(noteId, rootMarkId, subMarkId);

        sqlSession.commit();

        sqlSession.close();
    }

    // 新增用户设置
    public static void insertUserSettings(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 公开我的收藏
    public static void updatePublicMyStar(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 公开我的生日
    public static void updatePublicMyBorn(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 公开我的番剧
    public static void updatePublicFanju(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 公开我的学校
    public static void updatePublicSchool(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 公开我的关注
    public static void updatePublicFollow(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 公开我的介绍
    public static void updatePublicCanvases(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 公开我的个人主页
    public static void updatePublicHomepage(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 获取所有设置信息
    public static UserSettingsInfer selectUserSettings(String userId) {
        return null;
    }

    // 打开或关闭消息提醒
    public static void updateMessageTip(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 打开或关闭回复我的消息提醒
    public static void updateReplyMessageTip(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }

    // 打开或关闭点赞提醒
    public static void updateLikeTip(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserSettingsMapper userSettingsMapper = sqlSession.getMapper(UserSettingsMapper.class);


        sqlSession.commit();

        sqlSession.close();
    }


    // 建立两人的关系，如果关系已经建立，则将建立一人关注另一人的关系, 并创建私聊对象
    public static void insertPrivacyUser(String uid, String userId, String otherUid, String sessionId, String gratefulMessage) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);

        FollowFansMapper followFansMapper = sqlSession.getMapper(FollowFansMapper.class);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        String followedId = userMapper.selectUserIdByUid(otherUid);

        followFansMapper.insertRelationByBothUserIdAndUid(uid, userId, otherUid, followedId);

        messageMapper.insertPrivacyUser(uid, otherUid, sessionId, gratefulMessage);

        sqlSession.commit();

        sqlSession.close();
    }

    // 新增笔记浏览量
    public static void insertViewNumber(String authorUid, String noteId, String viewUid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);

        noteMapper.insertNoteViewNumber(authorUid, noteId, viewUid);

        sqlSession.commit();

        sqlSession.close();
    }

    // 获取近期消息对象
    public static List<LatestPrivacyUser> selectLatestPrivacyUser(String uid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);


        sqlSession.close();

        return null;
    }

    // 取消点赞笔记
    public static void updateNoteLike(String noteId, String authorUid, String likeUid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);

        noteMapper.updateNoteLike(authorUid, noteId, likeUid);

        sqlSession.commit();

        sqlSession.close();
    }

    // 取消不喜欢笔记
    public static void updateNoteUnlike(String noteId, String authorUid, String unlikeUid) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        NoteMapper noteMapper = sqlSession.getMapper(NoteMapper.class);

        noteMapper.updateNoteUnlike(authorUid, noteId, unlikeUid);

        sqlSession.commit();

        sqlSession.close();
    }

    // 新增私信消息
    public static void insertSessionMessage(String sendUid, String receiveUid, String sessionId, String content) {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        MessageMapper messageMapper = sqlSession.getMapper(MessageMapper.class);

        messageMapper.insertSessionMessage(sendUid, receiveUid, sessionId, content);

        sqlSession.commit();

        sqlSession.close();
    }

    // 获取所有用户的基本信息
    public static List<UserAllInfos> selectUsersAllInfos() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OperateUserMapper operateUserMapper = sqlSession.getMapper(OperateUserMapper.class);

        List<UserAllInfos> userAllInfos = operateUserMapper.selectUsersAllInfos();

        sqlSession.close();
        return userAllInfos;
    }

    // 更新用户信息——非用户本人操作
    public static void updateUserInfos(String uid, String userId, String username, String beforeUsername, String updateUsernameDate,
                                       String loginTime, String registerTime, String logOff, String logOffDate) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OperateUserMapper operateUserMapper = sqlSession.getMapper(OperateUserMapper.class);

        operateUserMapper.updateUserInfos(uid, userId, username, beforeUsername, updateUsernameDate, loginTime, registerTime, logOff, logOffDate);

        sqlSession.commit();
        sqlSession.close();
    }

    // 删除用户
    public static void deleteUser(String uid, String userId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OperateUserMapper operateUserMapper = sqlSession.getMapper(OperateUserMapper.class);

        operateUserMapper.deleteUser(uid, userId);

        sqlSession.commit();
        sqlSession.close();
    }

    // 检查管理员用户名密码是否正确
    public static Integer checkAdministrator(String account, String passwd) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OperateAdministratorMapper operateAdministratorMapper = sqlSession.getMapper(OperateAdministratorMapper.class);

        Integer integer = operateAdministratorMapper.checkAdministrator(account, passwd);

        sqlSession.close();
        return integer;
    }

    // 获取所有的节日信息
    public static List<FestivalInfo> getAllFestivalInfos() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SystemLoginMapper systemLoginMapper = sqlSession.getMapper(SystemLoginMapper.class);

        List<FestivalInfo> festivalInfos = systemLoginMapper.getAllFestivalInfos();

        sqlSession.close();
        return festivalInfos;
    }


}
