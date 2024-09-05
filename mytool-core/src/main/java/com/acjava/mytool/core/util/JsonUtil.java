package com.acjava.mytool.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @Description: JSON工具类
 * @author: loujm
 * @date: 2023-7-10
 */
public class JsonUtil {
    /**
     * 转换为JSON字符串
     *
     * @param obj 被转为JSON的对象
     * @return JSON字符串
     */
    public static String toJsonString(Object obj) {
        if (null == obj) {
            return null;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).toString();
        }
        return JSON.toJSONString(obj, SerializerFeature.IgnoreNonFieldGetter);
    }
}
