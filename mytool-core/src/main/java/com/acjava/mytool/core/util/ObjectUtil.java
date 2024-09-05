package com.acjava.mytool.core.util;

import java.util.Objects;

/**
 * @Description: Object工具类
 * @author: loujm
 * @date: 2023-7-10
 */
public class ObjectUtil {

    /**
     * 检查对象是否为null<br>
     *
     * @param obj 对象
     * @return 是否为null
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * 检查对象是否不为null<br>
     *
     * @param obj 对象
     * @return 是否不为null
     */
    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean equals(Object a, Object b) {
        return Objects.equals(a, b);
    }
}
