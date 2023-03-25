package com.huadiao.web.servlet.user;
/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 29 20:03
 */

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.NoteStar;
import com.huadiao.utils.ParameterHandle;
import com.huadiao.web.mysqlProcess;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet(urlPatterns = "/myStarServlet")
public class myStarServlet extends HttpServlet {
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
            System.out.println(fileItems);
            // 获取 userId
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");
            String myUid = (String) session.getAttribute("uid");

            for (FileItem fileItem : fileItems) {   //遍历，找到表单中每一个文件对应的<input>上传的文件数据
                if (fileItem.isFormField()) {   //这个<input>中的数据不是文件

                    String name = fileItem.getFieldName();//获取非文件<input>的name属性
                    String value = fileItem.getString("utf-8");//获取非文件<input>的value属性

                    switch (name) {
                        case "getNoteStars" : {
                            List<NoteStar> noteStars = mysqlProcess.selectNotesStar(myUid);
                            response.getWriter().write(JSON.toJSONString(noteStars));
                            return;
                        }
                    }
                    System.out.println(name + "::" + value);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
