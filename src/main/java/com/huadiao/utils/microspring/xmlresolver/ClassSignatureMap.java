package com.huadiao.utils.microspring.xmlresolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法签名映射, 通过反射获取方法映射别名
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClassSignatureMap {
    /**
     * 定义一个类签名, 也可以叫类别名
     * @return 返回类别名
     */
    String classSignature() default "classSignature";
}
