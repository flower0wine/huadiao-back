package com.huadiao.mapper;

import com.huadiao.pojo.NoteStar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 29 20:04
 */
public interface MyStarMapper {

    /**
     * 新增收藏
     * @param authorUid 笔记被收藏的用户的 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     */
    void insertNoteStar(@Param("authorUid") String authorUid, @Param("myUid") String myUid, @Param("noteId") String noteId);

    /**
     * 取消笔记收藏
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     */
    void cancelNoteStar(@Param("authorUid") String authorUid, @Param("myUid") String myUid, @Param("noteId") String noteId);

    /**
     * 获取该用户收藏的所有笔记
     * @param myUid 作者 uid
     * @return 返回用户收藏的所有笔记
     */
    List<NoteStar> selectUserAllNoteStars(@Param("myUid") String myUid);
}
