package com.acjava.mytool.trace.util;

import java.lang.reflect.Field;

/**
 * @author loujm
 */
public class ReflectUtil {
    public static Field getField(Class<?> clazz, String name) {
        Field result = null;

        try {
            result = clazz.getDeclaredField(name);
            result.setAccessible(true);
        } catch (NoSuchFieldException e) {}

        return result;
    }
}
