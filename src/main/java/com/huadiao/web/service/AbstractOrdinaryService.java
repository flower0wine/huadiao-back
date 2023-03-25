package com.huadiao.web.service;

import com.huadiao.mapper.SystemLoginMapper;
import com.huadiao.pojo.FestivalInfo;
import com.huadiao.utils.microspring.requestprocess.DispatcherInterface;
import com.huadiao.utils.microspring.requestprocess.SqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description
 */
public abstract class AbstractOrdinaryService implements OrdinaryService, DispatcherInterface {

    /**
     * 获取所有节日信息
     * @return 返回节日信息
     */
    public static List<FestivalInfo> getAllFestivalInfos() throws IOException {
        SqlSession sqlSession = SqlSessionFactory.generateSqlSession();
        SystemLoginMapper systemLoginMapper = sqlSession.getMapper(SystemLoginMapper.class);
        List<FestivalInfo> allFestivalInfos = systemLoginMapper.getAllFestivalInfos();
        sqlSession.close();
        return allFestivalInfos;
    }
}
