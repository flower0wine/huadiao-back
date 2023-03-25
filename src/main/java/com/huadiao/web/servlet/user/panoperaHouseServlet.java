package com.huadiao.web.servlet.user; /**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 31 10:41
 */

import com.alibaba.fastjson.JSON;
import com.huadiao.pojo.HuadiaoHouseInfer;
import com.huadiao.utils.ParameterHandle;
import com.huadiao.web.mysqlProcess;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet(urlPatterns = "/panoperaHouseServlet")
public class panoperaHouseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 这里传入 webapp 下的保存文件目录名，可以是未存在的
        Map map = ParameterHandle.createFile(request, this, "panoperaHouse");

        String tip = uploadParasRequest((ServletFileUpload) map.get("upload"), request, (String) map.get("uploadPath"), response);

        // 发送提示给客户端
        response.getWriter().write(tip);

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
            String uid = (String) session.getAttribute("uid");
            // 番剧名
            String fanjuName = null;
            String targetUid = null;
            String targetUserId = null;
            for (FileItem fileItem : fileItems) {   //遍历，找到表单中每一个文件对应的<input>上传的文件数据
                if (fileItem.isFormField()) {   //这个<input>中的数据不是文件

                    String name = fileItem.getFieldName();//获取非文件<input>的name属性
                    String value = fileItem.getString("utf-8");//获取非文件<input>的value属性

                    switch (name) {
                        case "uid": {
                            targetUid = value;
                            targetUserId = mysqlProcess.selectUserIdByUid(targetUid);
                            break;
                        }
                        // 获取页面除番剧外的信息
                        case "getPageOtherInfer": {
                            return "";
                        }
                        // 获取番剧信息
                        case "getPageFanjuInfer": {
                            List<HuadiaoHouseInfer> panoperaFanjusInfers = mysqlProcess.selectPanoperaFanjusInferByUserId(targetUserId);
                            response.getWriter().write(JSON.toJSONString(panoperaFanjusInfers));
                            if (!uid.equals(targetUid)) {
                                mysqlProcess.insertViewedFanju(targetUid, uid);
                            }
                            return "";
                        }
                        // 这是删除番剧的 index
                        case "deleteIndex": {
                            mysqlProcess.updateFanjuByUserIdAndAddDate(userId, value);
                            return "deleteSucceed";
                        }
                        case "fanjuName":
                            fanjuName = value;
                            break;
                        case "titleColor":
                            mysqlProcess.updateTitleColor(userId, value);
                            return "";
                        case "titleBackground":
                            mysqlProcess.updateTitleBackground(userId, value);
                            return "";
                        case "pageTwoBackground":
                            mysqlProcess.updatePageTwoBackground(userId, value);
                            return "";
                        case "pageTwoForeground":
                            mysqlProcess.updatePageTwoForeground(userId, value);
                            return "";
                            // 记录点击的是第几个边框
                        case "borderImg":
                            mysqlProcess.updateBorderImg(userId, value);
                            return "";
                            // 卡片背景是添加番剧那一张的背景
                        case "cardBackground":
                            mysqlProcess.updateCardColor(userId, value);
                            return "";
                    }
                    System.out.println(name + "::" + value);
                    tip = "uploadSucceed";
                } else { //这个<input>中的数据是文件
                    String uploadFileName = fileItem.getName();//获取这个文件的名称，带后缀

                    if (uploadFileName == null || uploadFileName.trim().equals("")) {//如果文件上传的名字为空
                        continue;
                    }
                    //可以使用UUID(可以唯一识别的通用码)，保证文件名唯一；UUID.randomUUID(),随机生成一个唯一的识别通用码；
                    String uuidName = UUID.randomUUID().toString();

                    //获得上传的文件后缀名 jpg等
                    String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);//最后一个"."后面的字符串，获取结果 = 文件类型

                    // 这里封装重复代码
                    ParameterHandle.fileHandle(fileItem, uploadPath, uuidName, fileExtName);

                    switch (fileItem.getFieldName()) {
                        case "fanjuCover": {
                            mysqlProcess.insertFanjuByUserId(uid, userId, fanjuName, uid + "/" + uuidName + "." + fileExtName);
                            return "uploadFanjuCoverSucceed";

                        }
                        case "pageTwoBackground": {
                            mysqlProcess.updatePageTwoBackground(userId, uid + "/" + uuidName + "." + fileExtName);
                            return "uploadBackgroundSucceed";
                        }
                        case "pageTwoForeground": {
                            mysqlProcess.updatePageTwoForeground(userId, uid + "/" + uuidName + "." + fileExtName);
                            return "uploadForegroundSucceed";
                        }
                        case "cardBackground": {
                            mysqlProcess.updateCardColor(userId, uid + "/" + uuidName + "." + fileExtName);
                            return "uploadCardBackgroundSucceed";
                        }
                    }

                    //上传成功，清除临时文件
                    fileItem.delete();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tip;
    }


}
