package com.huadiao.pojo;

import java.util.Arrays;

/**
 * @Project_Name huadiao
 * @Author 李志
 * @Version 1.0
 * @Start_Time 2022 09 24 18:54
 */
public class test {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3 ,4 ,5};
        int[] a = Arrays.stream(arr).filter((ele) -> {
            return false;
        }).toArray();
        System.out.println(Arrays.toString(a));
    }
}
