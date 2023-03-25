package com.huadiao.web.servlet.user; /**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 25 13:40
 */

import com.huadiao.utils.CreateUserId;
import com.huadiao.web.mysqlProcess;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//@WebServlet(urlPatterns = "/registerServlet")
public class registerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        获取客户端传来的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        System.out.println(username + "\n" + password + "\n" + checkCode);

//        如果存在相同的用户名
        if (mysqlProcess.checkSameUsername(username)) {
//            0 代表用户名存在，1 代表用户名不存在
            response.getWriter().write("0");
        } else {

            // 提交表单后
            if (password != null && checkCode != null) {
                // 获取 checkCodeServlet 传来的 checkCode
                HttpSession session = request.getSession();
                String checkCodeSession = session.getAttribute("checkCode").toString();
                // 如果验证码正确
                if (checkCodeSession.equals(checkCode.toUpperCase())) {
                    // 分配用户 ID
                    String userId = CreateUserId.createUserId();
                    try {
                        // 加入新用户
                        mysqlProcess.insertNewUser(userId, username, password);

                    } catch (Exception e) {
                        // 生成相同的 userId 重新生成
                        System.out.println(e.getMessage());
                        userId = CreateUserId.createUserId();
                        // 加入新用户
                        mysqlProcess.insertNewUser(userId, username, password);
                    }
                    // 查找用户 uid
                    String uid = mysqlProcess.selectUidByUserIdFromUser(userId);
                    // 加入用户信息
                    mysqlProcess.insertUserInfer(uid, userId, userId, "我是新来的小伙伴，请多多关照！", "0", null, null);
                    // 加入新的番剧页面配置
                    mysqlProcess.insertPanoperaSettings(uid, userId);
                    // 新增个人主页部分信息
                    mysqlProcess.insertAllByUserId(uid, userId);
                    // 新增用户设置
                    mysqlProcess.insertUserSettings(uid, userId);
                    // 向客户端发送注册成功
                    response.getWriter().write("succeedRegister");


                } else {
                    // 验证码错误
                    response.getWriter().write("wrongCode");
                }
            }
        }

    }
}
