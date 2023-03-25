package com.huadiao.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.NoteStar;
import com.huadiao.utils.log.Loggers;
import com.huadiao.web.dao.StarDAO;
import com.huadiao.web.service.StarService;

import java.util.List;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description
 */
public class StarServiceImpl implements StarService, Loggers {
    private StarDAO starDAO;

    /**
     * 新增笔记收藏, 也可能是取消收藏后再次收藏
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     * @return 收藏成功返回 addNewNoteStarSucceed
     */
    public String addNewNoteStar(String authorUid, String myUid, String noteId) {
        starDAO.insertNoteStar(authorUid, myUid, noteId);
        LOGGER.debug("uid 为 {} 的用户新增了对 uid 为 {} 的用户的一个笔记 noteId 为 {} 的收藏", myUid, authorUid, noteId);
        return ADD_NEW_NOTE_STAR_SUCCEED;
    }

    /**
     * 取消笔记收藏
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     * @return 取消收藏成功返回 cancelNoteStarSucceed
     */
    public String cancelNewNoteStar(String authorUid, String myUid, String noteId) {
        starDAO.updateNoteStar(authorUid, myUid, noteId);
        LOGGER.debug( "uid 为 {} 的用户取消了对 uid 为 {} 的用户的一个笔记 noteId 为 {} 的收藏", myUid, authorUid, noteId);
        return CANCEL_NOTE_STAR_SUCCEED;
    }

    /**
     * 获取我收藏的所有笔记
     * @param myUid 我的 uid
     * @return 返回所有收藏的笔记
     */
    public String getUserAllNoteStars(String myUid) {
        List<NoteStar> noteStars = starDAO.selectNotesStar(myUid);
        LOGGER.debug("uid 为 {} 的用户获取了自己收藏的所有笔记", myUid);
        return JSON.toJSONString(noteStars);
    }
}
