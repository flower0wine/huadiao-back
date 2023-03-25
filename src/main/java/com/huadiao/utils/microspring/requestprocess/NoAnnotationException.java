package com.huadiao.utils.microspring.requestprocess;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description 没有使用注解异常
 */
class NoAnnotationException extends RuntimeException {

    public NoAnnotationException(String message) {
        super(message);
    }
}
