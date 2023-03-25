package com.huadiao.utils.microspring.requestprocess.fileupload;

import org.apache.commons.fileupload.FileItem;

public interface FormDataFileUpload {

    /**
     * 文件数据
     * @param fileItem 文件单项
     * @throws Exception 可能抛出异常
     */
    default void fileData(FileItem fileItem) throws Exception {}

    /**
     * 表单字段
     * @param fileItem 文件单项
     * @throws Exception 可能抛出异常
     */
    default void formField(FileItem fileItem) throws Exception {}

}
