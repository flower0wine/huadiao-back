package com.huadiao.web.servlet.user; /**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 27 19:01
 */

import com.huadiao.utils.GeneratorCookie;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

//@WebServlet(urlPatterns = "/loginOutServlet")
public class loginOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginOut = request.getParameter("loginOut");
        if ("loginOut".equals(loginOut)) {
            // 删除 cookie
            response.addCookie(GeneratorCookie.newMoreProCookie("User_ID", "", 0, true, "/"));
            HttpSession session = request.getSession();
            // 退出登录销毁 session
            session.invalidate();
        }
    }
}
