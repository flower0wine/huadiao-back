package com.huadiao.web.service;

import java.util.List;

/**
 * @projectName 花凋
 * @author flowerwine
 * @description 扩展方法
 * @version 1.1
 */
public abstract class AbstractFollowsFansService implements FollowsFansService {
    /**
     * 根据传入的我和别人的关系列表来设置关系数组, 主要是将数字转化为 布尔值
     * @param relationList 关系列表
     * @return 返回关系数组
     */
    public static boolean[] getRelation(List<Integer> relationList) {
        boolean[] relationArray = new boolean[2];
        if(relationList.isEmpty()) {
            return relationArray;
        } else if(relationList.size() == FOLLOW_AND_FAN) {
            relationArray[0] = relationArray[1] = true;
        } else if(relationList.get(0) == FOLLOWING_UNFOLLOWED) {
            relationArray[0] = true;
        } else if(relationList.get(0) == UNFOLLOWING_FOLLOWED) {
            relationArray[1] = true;
        }
        return relationArray;
    }
}
