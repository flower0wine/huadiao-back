package com.huadiao.mapper;

import com.huadiao.pojo.FestivalInfo;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Function 后台管理页面登录页面
 * @Start_Time 2023 01 27 18:59
 */
public interface SystemLoginMapper {

    // 获取节日信息
    public List<FestivalInfo> getAllFestivalInfos();
}
