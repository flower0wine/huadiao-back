package com.huadiao.utils.microspring.requestprocess;

import org.apache.ibatis.session.SqlSession;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Function
 * @Start_Time 2023 02 03 9:45
 */
public interface DispatcherInterface {
    /**
     * 当前线程与数据库通信的 sqlSession
     */
    ThreadLocal<SqlSession> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 处理正常, 但没有数据可以返回时, 返回执行正确提示信息
     */
    String CORRECT_TIP = "correct";

    /**
     * 处理错误, 但没有数据可以返回时, 返回执行错误提示信息
     */
    String ERROR_TIP = "error";

    /**
     * 重定向
     */
    String REDIRECT = "redirect";

    /**
     * 请求内部转发
     */
    String FORWARD = "forward";
}
