package com.huadiao.mapper;

import com.huadiao.pojo.FansInfer;
import com.huadiao.pojo.FollowsInfer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 04 21:26
 */
public interface FollowFansMapper {

    // 建立两人的关系，如果关系已经建立，则将建立一人关注另一人的关系
    void insertRelationByBothUserIdAndUid(@Param("userUid") String userUid, @Param("userId") String userId,
                        @Param("followedUid") String followedUid, @Param("followedId") String followedId);

    // 取消两人中一方对另一方的关注
    void updateRelationByBothUserId(@Param("userId") String userId, @Param("followedId") String followedId);

    // 获取两人的关系
    List<Integer> selectRelationByBothUserId(@Param("userId") String userId, @Param("followedId") String followedId);

    /**
     * 获取指定用户的关注
     * @param viewedUid 用户 uid
     * @return 返回关注信息
     */
    List<FollowsInfer> selectFollowsByUid(@Param("viewedUid") String viewedUid);

    /**
     * 获取指定用户的粉丝
     * @param viewedUid 用户 uid
     * @return 返回粉丝信息
     */
    List<FansInfer> selectFansByUid(@Param("viewedUid") String viewedUid);

    // 获取用户关注的数量
    List<Integer> countFollowAndFansByUserId(@Param("userId") String userId);

}
