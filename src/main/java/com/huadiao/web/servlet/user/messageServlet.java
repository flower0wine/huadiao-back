package com.huadiao.web.servlet.user; /**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 13 17:41
 */

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.*;
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

@WebServlet(urlPatterns = "/messageServlet")
public class messageServlet extends HttpServlet {
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
    public String uploadParasRequest(ServletFileUpload upload, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String tip = "";
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            System.out.println(fileItems);
            // 获取 userId
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");
            String myUid = (String) session.getAttribute("uid");
            String systemMessageTitle = null;
            String systemMessageContent = null;
            String rootMarkId = null;
            String subMarkId = null;
            String noteId = null;
            String otherUid = null;
            for (FileItem fileItem : fileItems) {   //遍历，找到表单中每一个文件对应的<input>上传的文件数据
                if (fileItem.isFormField()) {   //这个<input>中的数据不是文件

                    String name = fileItem.getFieldName();//获取非文件<input>的name属性
                    String value = fileItem.getString("utf-8");//获取非文件<input>的value属性

                    switch (name) {
                        case "otherUid" : {
                            otherUid = value;
                            break;
                        }
                        case "getMessage" : {
                            MessageInfer messageInfer = mysqlProcess.selectMessageInfer(myUid, userId);
                            System.out.println(messageInfer);
                            response.getWriter().write(JSON.toJSONString(messageInfer));
                            return "";
                        }
                        // 将回复我的全部设为已读
                        case "replyMessageRead" : {
                            mysqlProcess.updateReplyMessageRead(myUid);
                            return "";
                        }
                        case "systemMessageTitle" : {
                            systemMessageTitle = value;
                            break;
                        }
                        case "systemMessageContent" : {
                            systemMessageContent = value;
                            break;
                        }
                        case "addSystemMessage" : {
                            String id = mysqlProcess.insertSystemMessage(systemMessageTitle, systemMessageContent);
                            return "";
                        }
                        case "noteId" : {
                            noteId = value;
                            break;
                        }
                        case "rootMarkId" : {
                            rootMarkId = value;
                            break;
                        }
                        case "subMarkId" : {
                            subMarkId = value;
                            break;
                        }
                        case "deleteReplyMessage" : {
                            mysqlProcess.deleteReplyMessage(rootMarkId, otherUid, noteId);
                            return "";
                        }
                        case "deletePrivacyUser" : {
                            mysqlProcess.deletePrivacyUser(myUid, otherUid);
                            return "";
                        }
                        case "deleteMarkLikeMessage" : {
                            mysqlProcess.deleteMarkLikeMessage(rootMarkId, subMarkId, noteId);
                            return "";
                        }
                    }
                    System.out.println(name + "::" + value);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return tip;
    }
}
