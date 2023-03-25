package com.huadiao.web.servlet.user;

import com.huadiao.utils.GeneratorCookie;
import com.huadiao.web.mysqlProcess;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 25 17:08
 */
//@WebServlet(urlPatterns = "/loginServlet")
public class loginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username + "\n" + password);

        if (mysqlProcess.checkUserSurvive(username, password)) {

            // 设置 cookie 保持用户登录状态，这里用用户 ID 作为识别的依据
            String userId = mysqlProcess.selectUserIdByUsername(username);
            resp.addCookie(GeneratorCookie.newMoreProCookie("User_ID", userId, 31536000, true, "/"));
            // 更新用户登录时间
            mysqlProcess.updateUserLoginTime(userId);
            // 查找用户 uid
            String uid = mysqlProcess.selectUidByUserIdFromUserInfer(userId);

            // 存入 session 以便后续使用
            HttpSession session = req.getSession();
            System.out.println(session.getId());
            session.setAttribute("username", username);
            session.setAttribute("userId", userId);
            session.setAttribute("uid", uid);
            resp.getWriter().write("hasUser");
        } else {
            resp.getWriter().write("noUser");
        }
    }
}
