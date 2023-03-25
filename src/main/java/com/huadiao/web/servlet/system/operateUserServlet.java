package com.huadiao.web.servlet.system;
/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 12 22 12:18
 */

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.huadiao.pojo.systemSpecific.UserAllInfos;
import com.huadiao.utils.ParameterHandle;
import com.huadiao.web.mysqlProcess;
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

@WebServlet(urlPatterns = "/operateUserServlet")
public class operateUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 这里传入 webapp 下的保存文件目录名，可以是未存在的
        Map map = ParameterHandle.createFile(request, this, "operateUser");

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
            // 获取 userId
            String uid = null;
            String userId = null;
            String username = null;
            String beforeUsername = null;
            String updateUsernameDate = null;
            String loginTime = null;
            String registerTime = null;
            String logOff = null;
            String logOffDate = null;
            // 系统消息
            for (FileItem fileItem : fileItems) {   //遍历，找到表单中每一个文件对应的<input>上传的文件数据
                if (fileItem.isFormField()) {   //这个<input>中的数据不是文件

                    String name = fileItem.getFieldName();//获取非文件<input>的name属性
                    String value = fileItem.getString("utf-8");//获取非文件<input>的value属性
                    switch (name) {
                        // 获取所有用户基本信息
                        case "getUserAllInfo": {
                            List<UserAllInfos> userAllInfos = mysqlProcess.selectUsersAllInfos();
                            response.getWriter().write(JSONObject.toJSONString(userAllInfos, SerializerFeature.SortField, SerializerFeature.WriteMapNullValue));
                            return "";
                        }
                        case "uid": {
                            uid = value;
                            break;
                        }
                        case "user_id": {
                            userId = value;
                            break;
                        }
                        case "username": {
                            username = value;
                            break;
                        }
                        case "before_username": {
                            beforeUsername = value;
                            break;
                        }
                        case "update_username_date": {
                            updateUsernameDate = value;
                            break;
                        }
                        case "login_time": {
                            loginTime = value;
                            break;
                        }
                        case "register_time": {
                            registerTime = value;
                            break;
                        }
                        case "log_off": {
                            logOff = value;
                            break;
                        }
                        case "log_off_date": {
                            logOffDate = value;
                            break;
                        }
                        case "updateUserInfos": {
                            mysqlProcess.updateUserInfos(uid, userId, username, beforeUsername, updateUsernameDate, loginTime, registerTime, logOff, logOffDate);
                            return "updateUserInfoSucceed";
                        }
                        case "deleteUser": {
                            mysqlProcess.deleteUser(uid, userId);
                            return "deleteUserSucceed";
                        }
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
