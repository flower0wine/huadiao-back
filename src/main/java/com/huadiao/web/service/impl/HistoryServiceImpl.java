package com.huadiao.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.FanjuHistory;
import com.huadiao.pojo.NotesHistory;
import com.huadiao.utils.log.Loggers;
import com.huadiao.web.dao.HistoryDAO;
import com.huadiao.web.dao.NoteDAO;
import com.huadiao.web.service.HistoryService;
import java.util.List;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description
 */
public class HistoryServiceImpl implements HistoryService, Loggers {
    private HistoryDAO historyDAO;
    private NoteDAO noteDAO;


    /**
     * 获取笔记全部历史记录
     * @param myUid 我的 uid
     * @return 返回笔记历史记录
     */
    public String getUserAllNotesHistories(String myUid) {
        // 获取笔记历史记录信息
        List<NotesHistory> notesHistories = historyDAO.selectNotesHistoriesByUid(myUid);
        LOGGER.debug("uid 为 {} 的用户获取笔记历史记录", myUid);
        return JSON.toJSONString(notesHistories);
    }

    /**
     * 获取番剧馆全部历史记录
     * @param myUid 我的 uid
     * @return 返回番剧馆历史记录
     */
    public String getUserAllFanjuHistories(String myUid) {
        List<FanjuHistory> fanjuHistories = historyDAO.selectFanjuHistoryByUid(myUid);
        // 获取番剧馆历史记录信息
        LOGGER.debug("uid 为 {} 的用户获取番剧馆历史记录", myUid);
        return JSON.toJSONString(fanjuHistories);
    }

    /**
     * 删除笔记历史记录
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     * @return 删除成功返回 noteHistoryDeleteSucceed
     */
    public String deleteNoteHistory(String authorUid, String myUid, String noteId) {
        historyDAO.updateNoteHistory(authorUid, noteId, myUid);
        LOGGER.debug("uid 为 {} 的用户删除了 uid 为 {} 的用户的一个笔记 noteId 为 {} 的浏览记录", myUid, authorUid, noteId);
        return NOTE_HISTORY_DELETE_SUCCEED;
    }

    /**
     * 删除番剧馆历史记录
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @return 删除成功返回 fanjuHistoryDeleteSucceed
     */
    public String deleteFanjuHistory(String authorUid, String myUid) {
        historyDAO.updateHuadiaoHouseHistory(authorUid, myUid);
        LOGGER.debug("uid 为 {} 的用户删除了 uid 为 {} 的用户的番剧馆的浏览记录", myUid, authorUid);
        return HUADIAO_HOUSE_HISTORY_DELETE_SUCCEED;
    }
}
