package com.huadiao.utils.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author flowerwine
 * @version 1.1
 * @projectName huadiao
 * @description 获取单例 Logger 对象
 */
public class SingleLogger {

    private SingleLogger(){}

    /**
     * 日志对象, volatile 防止指令重排序
     * 如：初始化对象步骤为（1）为新对象分配内存空间。（2）初始化对象。（3）将 logger 变量指向内存空间
     * 指令重排序后（3）可能会在（2）之前执行, 导致其他线程获取 logger 为 null
     */
    private volatile static Logger logger;

    /**
     * 懒汉式单例模式
     * 多线程获取 logger, 初始化 logger 时需要同步
     * @return logger 对象
     */
    public static Logger getLogger() {
        // 如果 logger 为 null 需要初始化
        if(logger == null) {
            // 只有一个线程能进行初始化, 其他线程阻塞
            synchronized (SingleLogger.class) {
                // logger 后, 初始化线程释放锁, 阻塞线程进入代码块不能再次初始化
                // 故需再次判断, 另外保证初始化线程对 logger 的更改对其他线程立即可见,
                // 即将 logger 存放在主内存中, 不使用缓存,
                // 需要将 logger 声明为 volatile
                if(logger == null) {
                    logger = LogManager.getLogger();
                }
            }
        }
        return logger;
    }
}
