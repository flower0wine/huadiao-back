package com.huadiao.utils.microspring.xmlresolver;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @projectName 花凋
 * @author  flowerwine
 * @version  1.1
 * @description beans 注解解析, 注解定义在类上
 */
public class BeansAnnotationResolver {
    private static String resolvePath = "./";

    private static Map<String, Object> map;

    private BeansAnnotationResolver() {}
    /**
     * 类注解解析
     * @return 返回解析生成的 bean 集合
     */
    public static Map<String, Object> resolveSrcDirectory() {
        map = new HashMap<>();
        File file = new File(resolvePath);
        recursiveResolve(file, resolvePath);
        return map;
    }

    /**
     * 递归解析文件
     * @param file 要解析的文件
     */
    private static void recursiveResolve(File file, String path) {
        System.out.println(Arrays.toString(file.list()));
    }
}
