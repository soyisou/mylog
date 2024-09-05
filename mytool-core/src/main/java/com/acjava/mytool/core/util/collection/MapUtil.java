package com.acjava.mytool.core.util.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Map的工具类
 * @author: loujm
 * @date: 2023-7-10
 */
public class MapUtil {
    /**
     * Map是否为空
     *
     * @param map 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Map是否为非空
     *
     * @param map 集合
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 新建一个HashMap
     *
     * @param <K> Key类型
     * @param <V> Value类型
     * @return HashMap对象
     */
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>();
    }


    /**
     * 新建一个HashMap
     *
     * @param <K>  Key类型
     * @param <V>  Value类型
     * @param size 初始大小
     * @return HashMap对象
     */
    public static <K, V> HashMap<K, V> newHashMap(int size) {
        return new HashMap<>(size);
    }

    /**
     * 返回一个空Map
     *
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 空Map
     * @see Collections#emptyMap()
     */
    public static <K, V> Map<K, V> empty() {
        return Collections.emptyMap();
    }
}
