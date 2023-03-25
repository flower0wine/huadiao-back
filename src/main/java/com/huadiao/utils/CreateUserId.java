package com.huadiao.utils;

import java.util.Random;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 07 27 22:21
 *
 * 该类用于生成不同的 userId
 */
public class CreateUserId {

    private static final char[] numberCodeUnit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private final static int length = 12;

    public static String createUserId(){
        StringBuffer origin = new StringBuffer("huadiao_");
        StringBuffer userId = new StringBuffer("");
        Random random = new Random();
        for(int i = 0; i < length; i++){
            userId.append(numberCodeUnit[random.nextInt(numberCodeUnit.length)]);
        }
        return origin.append(userId).toString();
    }
}
