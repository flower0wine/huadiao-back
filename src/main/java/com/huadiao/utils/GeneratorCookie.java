package com.huadiao.utils;

import javax.servlet.http.Cookie;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 28 14:48
 *
 * 该类用于生成 cookie
 */
public class GeneratorCookie {

    public static Cookie newBlankCookie(String key, String value){
        return new Cookie(key, value);
    }

    public static Cookie newProCookie(String key, String value, int time, boolean httpOnly){
        Cookie cookie = newBlankCookie(key, value);
        cookie.setMaxAge(time);
        cookie.setHttpOnly(httpOnly);
        return cookie;
    }

    public static Cookie newMoreProCookie(String key, String value, int time, boolean httpOnly, String path){
        Cookie cookie = newProCookie(key, value, time, httpOnly);
        cookie.setPath(path);
        return cookie;
    }

    public static Cookie newDetailCookie(String key, String value, int time, boolean httpOnly, String domain, String path){
        Cookie cookie = newMoreProCookie(key, value, time, httpOnly, path);
        cookie.setDomain(domain);
        return cookie;
    }
}
