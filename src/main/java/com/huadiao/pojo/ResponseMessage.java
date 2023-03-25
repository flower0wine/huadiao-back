package com.huadiao.pojo;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description 响应消息
 */
public class ResponseMessage {
    public static final Integer ERROR_MESSAGE = 0;
    public static final Integer NORMAL_MESSAGE = 1;

    private String message;
    /**
     * 为 0 是错误消息
     * 为 1 是正常消息
     */
    private Integer code;
    private Object data;

    public ResponseMessage() {
    }

    public ResponseMessage(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
