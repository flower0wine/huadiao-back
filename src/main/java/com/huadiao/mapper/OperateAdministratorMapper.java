package com.huadiao.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2023 01 09 15:18
 */
public interface OperateAdministratorMapper {

    // 检查传入的管理员账号和密码是否正确
    Integer checkAdministrator(@Param("account") String account, @Param("passwd") String passwd);
}
