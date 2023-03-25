package com.huadiao.utils.microspring.xmlresolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法签名映射, 通过反射获取方法映射别名
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodSignatureMap {
    /**
     * 定义一个方法签名, 也可以叫方法别名
     * @return 返回方法签名, 即定义的方法别名
     */
    String methodSignature() default "methodSignature";

    /**
     * 标记该方法游客是否能访问
     * @return boolean
     */
    boolean allowTourAccess() default false;
}
