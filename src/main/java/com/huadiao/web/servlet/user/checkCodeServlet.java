package com.huadiao.web.servlet.user; /**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 25 13:32
 */

import com.huadiao.utils.CheckCodeUtils;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/checkCodeServlet")
public class checkCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream outputStream = response.getOutputStream();

        Map<String,Object> map = CheckCodeUtils.generateCodeAndPic();

        HttpSession session = request.getSession();
        session.setAttribute("checkCode", map.get("code"));
        System.out.println(session);

        // 提交响应
        ImageIO.write((RenderedImage) map.get("codePic"), "jpeg", outputStream);	// 将 response 的字节输出流填入即可


    }
}
