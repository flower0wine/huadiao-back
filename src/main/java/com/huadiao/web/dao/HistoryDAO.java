package com.huadiao.web.dao;

import com.huadiao.mapper.HistoryMapper;
import com.huadiao.pojo.FanjuHistory;
import com.huadiao.pojo.NotesHistory;
import com.huadiao.utils.microspring.requestprocess.DispatcherInterface;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description
 */
public class HistoryDAO implements DispatcherInterface {

    /**
     * 通过 uid 获取笔记历史记录
     * @param myUid 我的 uid
     * @return 返回笔记历史记录信息
     */
    public List<NotesHistory> selectNotesHistoriesByUid(String myUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HistoryMapper historyMapper = sqlSession.getMapper(HistoryMapper.class);
        return historyMapper.selectNotesHistoriesByUid(myUid);
    }

    /**
     * 通过 uid 来获取番剧馆历史记录
     * @param myUid 我的 uid
     * @return 返回番剧馆历史记录信息
     */
    public List<FanjuHistory> selectFanjuHistoryByUid(String myUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HistoryMapper historyMapper = sqlSession.getMapper(HistoryMapper.class);
        return historyMapper.selectFanjuHistoryByUid(myUid);
    }

    /**
     * 更新笔记状态为删除
     * @param authorUid 作者 uid
     * @param noteId 笔记 id
     * @param myUid 我的 uid
     */
    public void updateNoteHistory(String authorUid, String noteId, String myUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HistoryMapper historyMapper = sqlSession.getMapper(HistoryMapper.class);
        historyMapper.deleteNoteHistory(authorUid, myUid, noteId);
    }

    /**
     * 更新番剧馆状态为 删除
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     */
    public void updateHuadiaoHouseHistory(String authorUid, String myUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HistoryMapper historyMapper = sqlSession.getMapper(HistoryMapper.class);
        historyMapper.deleteFanjuHistory(authorUid, myUid);
    }

    /**
     * 添加笔记浏览历史
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     */
    public void insertViewedNoteHistoryByUid(String authorUid, String myUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HistoryMapper historyMapper = sqlSession.getMapper(HistoryMapper.class);
        historyMapper.insertViewedNoteHistoryByUid(authorUid, myUid, noteId);
    }

    /**
     * 添加番剧浏览记录
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     */
    public void insertViewedFanjuHistoryByUid(String authorUid, String myUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        HistoryMapper historyMapper = sqlSession.getMapper(HistoryMapper.class);
        historyMapper.insertViewedFanjuHistoryByUid(authorUid, myUid);
    }

}
