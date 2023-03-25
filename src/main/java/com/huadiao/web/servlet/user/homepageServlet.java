package com.huadiao.web.servlet.user; /**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 29 14:58
 */

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.HomepageInfer;
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
import java.util.UUID;

@WebServlet(urlPatterns = "/homepageServlet")
public class homepageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map map = ParameterHandle.createFile(request, this, "homepage");

        String s = uploadParasRequest((ServletFileUpload) map.get("upload"), request, (String) map.get("uploadPath"), response);
    }

    // 这个是核心方法
    public String uploadParasRequest(ServletFileUpload upload, HttpServletRequest request, String uploadPath, HttpServletResponse response) throws UnsupportedEncodingException {
        String tip = "uploadFail";
        try {
            List<FileItem> fileItems = upload.parseRequest(request);
            //使用文件解析对象的parseRequest()(解析request)，这个方法就会将req中的表单项按照一个<input>一个FileItem对象来进行封装
            //parseRequest(HttpServletRequest) 方法可以将通过表单中每一个HTML标签提交的数据封装成一个FileItem对象，然后以List列表的形式返回
            System.out.println(fileItems);
            // 获取 userId
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");
            String myUid = (String) session.getAttribute("uid");
            String otherUid = null;
            String sessionId = null;
            for (FileItem fileItem : fileItems) {   //遍历，找到表单中每一个文件对应的<input>上传的文件数据
                if (fileItem.isFormField()) {   //这个<input>中的数据不是文件

                    String name = fileItem.getFieldName();//获取非文件<input>的name属性
                    String value = fileItem.getString("utf-8");//获取非文件<input>的value属性

                    switch (name) {
                        case "otherUid" : {
                            otherUid = value;
                            break;
                        }
                        case "sessionId" : {
                            sessionId = value;
                            break;
                        }
                        // 关注及取消关注
                        case "followStatus" : {
                            // 点击关注
                            if ("1".equals(value)) {
                                mysqlProcess.insertPrivacyUser(myUid, userId, otherUid, sessionId, "多谢这位小伙伴的关注");
                                return "followedSucceed";

                                // 取消关注
                            } else if ("0".equals(value)) {
                                mysqlProcess.updateRelationByBothId(userId, otherUid);
                                return "cancelFollowedSucceed";
                            }
                        }

                        // 加载 个人主页
                        case "uid" : {
                            // 查询目标 信息
                            HomepageInfer homepageInfer = mysqlProcess.selectHomepageInfer(value, myUid, userId);
                            // 不是浏览自己的主页就算作别人的浏览量
                            if(!myUid.equals(value)){
                                mysqlProcess.insertHomepageVisit(value, myUid);
                            }

                            System.out.println(homepageInfer);
                            String string = JSON.toJSONString(homepageInfer);
                            response.getWriter().write(string);
                            return "";
                        }
                    }
                    System.out.println(name + "::" + value);
                } else { //这个<input>中的数据是文件

                    String uploadFileName = fileItem.getName();//获取这个文件的名称，带后缀

                    if (uploadFileName == null || uploadFileName.trim().equals("")) {//如果文件上传的名字为空
                        continue;
                    }
                    //可以使用UUID(可以唯一识别的通用码)，保证文件名唯一；UUID.randomUUID(),随机生成一个唯一的识别通用码；
                    String uuidName = UUID.randomUUID().toString();

                    //获得上传的文件后缀名 jpg等
                    String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);//最后一个"."后面的字符串，获取结果 = 文件类型

                    ParameterHandle.fileHandle(fileItem, uploadPath, uuidName, fileExtName);

                    switch (fileItem.getFieldName()) {
                        case "pageBackground" : {
                            mysqlProcess.updatePageBackgroundByUserId(userId, myUid + "/" + uuidName + "." + fileExtName);
                            return "pageBackgroundSucceed";
                        }
                        case "userAvatar" : {
                            mysqlProcess.updateUserAvatar(userId, myUid + "/" + uuidName + "." + fileExtName);
                            return "userAvatarSucceed";
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tip;
    }
}
