package com.huadiao.utils.microspring.requestprocess.fileupload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description FormDataFileUpload 的抽象实现类
 */
public abstract class FormDataProcessor implements FormDataFileUpload {

    public FormDataProcessor(HttpServletRequest request) throws Exception {
        fileUpload(request);
    }

    /**
     * 文件上传
     * @param request 请求对象
     * @throws FileUploadException 可能抛出的异常
     */
    public final void fileUpload(HttpServletRequest request) throws Exception {
        if(ServletFileUpload.isMultipartContent(request)) {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : items) {
                // 过滤掉表单中非文件域
                if (!item.isFormField()) {
                    fileData(item);
                } else {
                    formField(item);
                }
            }
        }
    }

    /**
     * 保存图片
     * @param fileItem 图片文件
     * @param myUid 我的 uid
     * @param directoryPath 要保存的目录名
     * @throws IOException 可能抛出异常
     * @return 图片文件部分链接, 保存到数据库
     */
    public static String saveFile(FileItem fileItem, String myUid, String directoryPath) throws IOException {
        // 获取文件类型
        String fileType = fileItem.getName().substring(fileItem.getName().lastIndexOf(".") + 1).toLowerCase();
        // 随机的文件名
        String fileName = String.format("%s/%s.%s", myUid, UUID.randomUUID(), fileType);
        // 文件保存路径
        String filePath = String.format("%s/%s", directoryPath, fileName);
        // 若该用户的图片路径不存在则创建
        File file = new File(String.format("%s/%s", directoryPath, myUid));
        if(!file.exists()) {
            file.mkdirs();
        }
        // 保存图片
        BufferedInputStream in = new BufferedInputStream(fileItem.getInputStream());
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filePath));
        Streams.copy(in, out, true);
        in.close();
        out.close();
        return fileName;
    }
}
