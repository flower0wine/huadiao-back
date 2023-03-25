package com.huadiao.web.servlet.system; /**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2023 01 09 16:59
 */

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
import java.util.Map;

@WebServlet(urlPatterns = "/operateAdministratorServlet")
public class operateAdministratorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 这里传入 webapp 下的保存文件目录名，可以是未存在的
        Map map = ParameterHandle.createFile(request, this, "operateAdministrator");

        String tip = uploadParasRequest((ServletFileUpload) map.get("upload"), request, (String) map.get("uploadPath"), response);

        // 发送提示给客户端
        response.getWriter().write(tip);
    }

    // 这个是核心方法
    public String uploadParasRequest(ServletFileUpload upload, HttpServletRequest request, String uploadPath, HttpServletResponse response) throws UnsupportedEncodingException {
        String tip = "fail";
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            //使用文件解析对象的parseRequest()(解析request)，这个方法就会将req中的表单项按照一个<input>一个FileItem对象来进行封装
            //parseRequest(HttpServletRequest) 方法可以将通过表单中每一个HTML标签提交的数据封装成一个FileItem对象，然后以List列表的形式返回
            System.out.println(fileItems);

            String account = null;
            String passwd = null;

            // 系统消息
            for (FileItem fileItem : fileItems) {   //遍历，找到表单中每一个文件对应的<input>上传的文件数据
                if (fileItem.isFormField()) {   //这个<input>中的数据不是文件

                    String name = fileItem.getFieldName();//获取非文件<input>的name属性
                    String value = fileItem.getString("utf-8");//获取非文件<input>的value属性
                    switch (name) {
                        case "account": {
                            account = value;
                            break;
                        }
                        case "passwd": {
                            passwd = value;
                            break;
                        }
                        case "searchAdministrator": {
                            Integer integer = mysqlProcess.checkAdministrator(account, passwd);
                            if(integer == null) {
                                tip = "dontHasAdministrator";
                            } else if(integer == 1) {
                                // 设置 session
                                HttpSession session = request.getSession();
                                session.setAttribute("administratorID", account);
                                System.out.println(session.getAttribute("administratorID"));
                                tip = "hasAdministrator";
                            }
                            return tip;
                        }
                    }
                    System.out.println(name + "::" + value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass());
            tip = "exception";
        }
        return tip;
    }
}
