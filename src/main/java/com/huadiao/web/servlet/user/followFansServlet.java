package com.huadiao.web.servlet.user; /**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 30 12:24
 */

import com.alibaba.fastjson.JSON;
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

@WebServlet(urlPatterns = "/followFansServlet")
public class followFansServlet extends HttpServlet {

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
            String sessionId = null;
            for (FileItem fileItem : fileItems) {   //遍历，找到表单中每一个文件对应的<input>上传的文件数据
                if (fileItem.isFormField()) {   //这个<input>中的数据不是文件

                    String name = fileItem.getFieldName();//获取非文件<input>的name属性
                    String value = fileItem.getString("utf-8");//获取非文件<input>的value属性

                    switch (name) {
                        case "sessionId" : {
                            sessionId = value;
                            break;
                        }
                        // 点击关注
                        case "followerUid" : {
                            if(myUid.equals(value)){
                                return;
                            }
                            mysqlProcess.insertRelationByBothId(myUid, userId, value, sessionId, "多谢这位小伙伴的关注!");
                            return;
                        }

                        // 取消关注
                        case "cancelFollowUid" : {
                            if(myUid.equals(value)){
                                return;
                            }
                            mysqlProcess.updateRelationByBothId(userId, value);
                            return;
                        }

                        // 这是访问页面的 uid，value 即为 他的 uid
                        case "uid" : {
                            Map<String, Object> map = mysqlProcess.selectFollowFansInfer(value, userId);

                            System.out.println(map);
                            // 转化为 JSON 对象后发送
                            response.getWriter().write(JSON.toJSONString(map));
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
