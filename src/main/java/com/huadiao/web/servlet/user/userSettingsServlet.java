package com.huadiao.web.servlet.user; /**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 09 01 16:41
 */

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.UserSettingsInfer;
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

@WebServlet(urlPatterns = "/userSettingsServlet")
public class userSettingsServlet extends HttpServlet {
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
                        case "getSettings" : {
                            UserSettingsInfer userSettingsInfer = mysqlProcess.selectUserSettings(userId);
                            response.getWriter().write(JSON.toJSONString(userSettingsInfer));
                            return;
                        }
                        case "modifyStarSettings" : {
                            mysqlProcess.updatePublicMyStar(myUid, userId);
                            return;
                        }
                        case "modifyBornSettings" : {
                            mysqlProcess.updatePublicMyBorn(myUid, userId);
                            return;
                        }
                        case "modifyFanjuSettings" : {
                            mysqlProcess.updatePublicFanju(myUid, userId);
                            return;
                        }
                        case "modifySchoolSettings" : {
                            mysqlProcess.updatePublicSchool(myUid, userId);
                            return;
                        }
                        case "modifyFollowSettings" : {
                            mysqlProcess.updatePublicFollow(myUid, userId);
                            return;
                        }
                        case "modifyCanvasesSettings" : {
                            mysqlProcess.updatePublicCanvases(myUid, userId);
                            return;
                        }
                        case "modifyHomepageSettings" : {
                            mysqlProcess.updatePublicHomepage(myUid, userId);
                            return;
                        }
                        case "modifyMessageTip" : {
                            mysqlProcess.updateMessageTip(myUid, userId);
                            return;
                        }
                        case "modifyReplyTip" : {
                            mysqlProcess.updateReplyMessageTip(myUid, userId);
                            return;
                        }
                        case "modifyLikeTip" : {
                            mysqlProcess.updateLikeTip(myUid, userId);
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
