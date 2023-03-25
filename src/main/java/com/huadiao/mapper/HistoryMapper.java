package com.huadiao.mapper;

import com.huadiao.pojo.FanjuHistory;
import com.huadiao.pojo.NotesHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 29 9:27
 */
public interface HistoryMapper {
    /**
     * 添加笔记浏览历史
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     */
    void insertViewedNoteHistoryByUid(@Param("authorUid") String authorUid, @Param("myUid") String myUid, @Param("noteId") String noteId);

    /**
     * 添加番剧浏览记录
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     */
    void insertViewedFanjuHistoryByUid(@Param("authorUid") String authorUid, @Param("myUid") String myUid);

    /**
     * 删除笔记历史记录
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     */
    void deleteNoteHistory(@Param("authorUid") String authorUid, @Param("myUid") String myUid, @Param("noteId") String noteId);

    /**
     * 删除番剧馆历史记录
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     */
    void deleteFanjuHistory(@Param("authorUid") String authorUid, @Param("myUid") String myUid);

    /**
     * 通过 uid 获取笔记历史记录
     * @param myUid 我的 uid
     * @return 返回笔记历史记录全部信息
     */
    List<NotesHistory> selectNotesHistoriesByUid(@Param("myUid") String myUid);

    /**
     * 通过 uid 来获取番剧馆历史记录
     * @param myUid 我的 uid
     * @return 返回番剧馆历史记录信息
     */
    List<FanjuHistory> selectFanjuHistoryByUid(@Param("myUid") String myUid);
}
