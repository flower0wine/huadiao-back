package com.huadiao.web.servlet.user;
/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 28 17:55
 */

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.UserInfer;
import com.huadiao.web.mysqlProcess;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/userInferServlet")
public class userInferServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置解码方式为 utf-8
        request.setCharacterEncoding("utf-8");

        String nickname = request.getParameter("nickname");
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("userId");

        // 点击按钮提交后 nickname 不为空，否则为空
        if(nickname != null){
            String canvases = request.getParameter("canvases");
            String sex = request.getParameter("sex");
            String born_date = request.getParameter("born_date");
            String school = request.getParameter("school");
            mysqlProcess.updateUserInferByUserId(userId, nickname, canvases, sex, born_date, school);
            response.getWriter().write("updateInferSucceed");

        // 初次进入页面，加载数据
        } else {

            // 获取用户信息
            UserInfer userInfer = mysqlProcess.selectUserInferByUserId(userId);
            // 设置编码
            response.setContentType("text/json;charset=utf-8");
            // 转换为 JSON 对象
            String string = JSON.toJSONString(userInfer);
            response.getWriter().write(string);
        }
    }
}
