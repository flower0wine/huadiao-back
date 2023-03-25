package com.huadiao.web.servlet.user; /**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 27 13:14
 */

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.IndexInfer;
import com.huadiao.utils.ParameterHandle;
import com.huadiao.web.mysqlProcess;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

//@WebServlet(urlPatterns = "/indexServlet")
public class indexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletFileUpload upload = ParameterHandle.createFile();

        uploadParasRequest(upload, request, response);

    }

    // 这个是核心方法
    public void uploadParasRequest(ServletFileUpload upload, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            //使用文件解析对象的parseRequest()(解析request)，这个方法就会将req中的表单项按照一个<input>一个FileItem对象来进行封装
            //parseRequest(HttpServletRequest) 方法可以将通过表单中每一个HTML标签提交的数据封装成一个FileItem对象，然后以List列表的形式返回
            System.out.println(fileItems);
            // 获取 userId
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");
            String myUid = (String) session.getAttribute("uid");
            System.out.println(request.getParameter("requestType"));
            System.out.println(request.getParameter("operation"));
            for (FileItem fileItem : fileItems) {   //遍历，找到表单中每一个文件对应的<input>上传的文件数据
                if (fileItem.isFormField()) {   //这个<input>中的数据不是文件

                    String name = fileItem.getFieldName();//获取非文件<input>的name属性
                    String value = fileItem.getString("utf-8");//获取非文件<input>的value属性

                    System.out.println(name);
                    switch (name) {
                        // 客户端请求登录状态，如果客户端与服务端userId匹配，则让用户登录
                        case "status" : {
                            // 判断用户是否登录过，以维持登录状态
                            Cookie[] cookies = request.getCookies();
                            for (Cookie cookie : cookies) {
                                String str = cookie.getName();
                                if (str.equals("User_ID")) {
                                    if (userId != null && userId.equals(cookie.getValue())) {
                                        // 获取信息
                                        String nickname = mysqlProcess.selectNicknameByUserId(userId);
                                        List<Integer> integers = mysqlProcess.countFollowAndFansByUserId(userId);
                                        String userAvatar = mysqlProcess.selectUserAvatarByUserId(userId);
                                        System.out.println(integers);
                                        // 填充信息
                                        IndexInfer indexInfer = new IndexInfer();
                                        indexInfer.setFollow(integers.get(0));
                                        indexInfer.setFans(integers.get(1));
                                        indexInfer.setNickname(nickname);
                                        indexInfer.setUid(myUid);
                                        indexInfer.setUser_avatar(userAvatar);
                                        // 响应信息
                                        response.getWriter().write(JSON.toJSONString(indexInfer));
                                        System.out.println(indexInfer);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
