package com.huadiao.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 08 04 19:39
 */
public class ParameterHandle {

    public static DiskFileItemFactory getDiskFileItemFactory(File file) {
        // 获取磁盘对象
        return new DiskFileItemFactory(1024 * 1024, file);
    }

    public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
        //创建ServletFileUpload对象，获取上传文件的解析对象
        return new ServletFileUpload(factory);
    }

    public static Map createFile(HttpServletRequest request, HttpServlet this_,  String dir) throws IOException {
        String path = "A:/huadiao_images";
        HttpSession session = request.getSession();
        String uid = (String) session.getAttribute("uid");

//        String uploadPath = this_.getServletContext().getRealPath("/" + dir + "/upload/" + uid);//upload路径
        String uploadPath = path + "/" + dir + "/upload/" + uid;
        File uploadFile = new File(uploadPath);//创建一个文件对象
        System.out.println(uploadFile.getAbsolutePath());
        if (!uploadFile.exists()) {
            System.out.println(uploadFile.mkdirs());//如果这个路径不存在，就创建这份路径
        }

        //缓存空间创建：临时路径，假如文件超过了预期的大小，我们就把他放在一个临时文件中，过几天自动删除，或者提醒用户转为永久
//        String tmpPath = this_.getServletContext().getRealPath("/" + dir + "/tmp/" + uid);
        String tmpPath = path + "/" + dir + "/tmp/" + uid;
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        DiskFileItemFactory factory = getDiskFileItemFactory(file);
        ServletFileUpload upload = getServletFileUpload(factory);
        Map map = new HashMap();
        map.put("upload", upload);
        map.put("uploadPath", uploadPath);
        return map;
    }

    public static ServletFileUpload createFile(){
        DiskFileItemFactory factory = getDiskFileItemFactory(new File(""));
        return getServletFileUpload(factory);
    }

    public static void fileHandle(FileItem fileItem, String uploadPath, String uuidName, String fileExtName) throws IOException {

        //文件真正要存储在服务器上的存在的路径realPath = /WEB-INF/upload + 文件上传时生成的唯一的UUID
        InputStream in = null;//每次遍历到的都是一个独立的、完整的文件对应的fileItem对象，所以我们只需要从它里面获取数据流再存储下来即可
        try {
            in = fileItem.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取文件输入流 ，路径基本格式为 /panoperaHouse/upload/1/7616f2cd-a4fd-43ce-a100-64cc8178bcff.jpeg
        System.out.println(uploadPath + "/" + uuidName + "." + fileExtName);
        FileOutputStream fos = new FileOutputStream(uploadPath + "/" + uuidName + "." + fileExtName);
        byte[] buffer = new byte[1024];//创建一个缓冲区
        int len = 0;//定义一个变量存储一次读到的实际数据量
        while (in != null && (len = in.read(buffer)) > 0) {
            fos.write(buffer, 0, len);//将文件流写到这个文件中 uploadPath + "/" + uuidName + "." + fileExtName
        }
        //关闭流
        if (in != null) {
            in.close();
        }
        fos.close();

        //上传成功，清除临时文件
        fileItem.delete();
    }


}
