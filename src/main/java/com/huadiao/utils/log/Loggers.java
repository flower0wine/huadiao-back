package com.huadiao.utils.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Loggers {

    /**
     * 日志对象
     */
    Logger LOGGER = LogManager.getLogger();
}
