package com.huadiao.web.servlet.system; /**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Function
 * @Start_Time 2023 01 28 10:34
 */

import com.alibaba.fastjson.JSON;
import com.huadiao.utils.FestivalHandler;
import com.huadiao.utils.ParameterHandle;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.exceptions.PersistenceException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/systemLoginServlet")
public class systemLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 这里传入 webapp 下的保存文件目录名，可以是未存在的
        Map map = ParameterHandle.createFile(request, this, "systemLogin");

        String tip = uploadParasRequest((ServletFileUpload) map.get("upload"), request, (String) map.get("uploadPath"), response);

    }

    // 这个是核心方法
    public String uploadParasRequest(ServletFileUpload upload, HttpServletRequest request, String uploadPath, HttpServletResponse response) throws UnsupportedEncodingException {
        String tip = "fail";
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            //使用文件解析对象的parseRequest()(解析request)，这个方法就会将req中的表单项按照一个<input>一个FileItem对象来进行封装
            //parseRequest(HttpServletRequest) 方法可以将通过表单中每一个HTML标签提交的数据封装成一个FileItem对象，然后以List列表的形式返回
            System.out.println(fileItems);
            // 获取 userId
            // 系统消息
            for (FileItem fileItem : fileItems) {   //遍历，找到表单中每一个文件对应的<input>上传的文件数据
                if (fileItem.isFormField()) {   //这个<input>中的数据不是文件

                    String name = fileItem.getFieldName();//获取非文件<input>的name属性
                    String value = fileItem.getString("utf-8");//获取非文件<input>的value属性
                    switch (name) {
                        case "getFestivalInfo":
                            response.getWriter().write(JSON.toJSONString(FestivalHandler.festivalInfo));
                    }
                    System.out.println(name + "::" + value);
                }
            }
        }catch (PersistenceException e){
            tip = "hasSameUsername";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getClass());
            tip = "databaseException";
        }
        return tip;
    }
}
