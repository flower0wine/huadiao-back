package com.huadiao.web.dao;

import com.huadiao.mapper.MyStarMapper;
import com.huadiao.pojo.NoteStar;
import com.huadiao.utils.microspring.requestprocess.DispatcherInterface;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description
 */
public class StarDAO implements DispatcherInterface {

    /**
     * 新增笔记收藏, 自己可以收藏自己的笔记
     * @param authorUid 笔记被收藏的用户的 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     */
    public void insertNoteStar(String authorUid, String myUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        MyStarMapper starMapper = sqlSession.getMapper(MyStarMapper.class);
        starMapper.insertNoteStar(authorUid, myUid, noteId);
    }

    /**
     * 取消笔记收藏, 也可以取消收藏自己的笔记
     * @param authorUid 作者 uid
     * @param myUid 我的 uid
     * @param noteId 笔记 id
     */
    public void updateNoteStar(String authorUid, String myUid, String noteId) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        MyStarMapper starMapper = sqlSession.getMapper(MyStarMapper.class);
        starMapper.cancelNoteStar(authorUid, myUid, noteId);
    }

    /**
     * 获取用户收藏的所有笔记
     * @param myUid 我的 uid
     * @return 返回所有收藏的笔记
     */
    public List<NoteStar> selectNotesStar(String myUid) {
        SqlSession sqlSession = THREAD_LOCAL.get();
        MyStarMapper starMapper = sqlSession.getMapper(MyStarMapper.class);
        return starMapper.selectUserAllNoteStars(myUid);
    }
}
