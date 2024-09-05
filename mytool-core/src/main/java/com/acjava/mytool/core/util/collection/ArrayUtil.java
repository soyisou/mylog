package com.acjava.mytool.core.util.collection;

/**
 * @Description: 数组工具类
 * @author: loujm
 * @date: 2023-7-10
 */
public class ArrayUtil {

    public static <T> boolean isEmpty(T[] arr) {
        return arr == null || arr.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] arr) {
        return !isEmpty(arr);
    }
}
