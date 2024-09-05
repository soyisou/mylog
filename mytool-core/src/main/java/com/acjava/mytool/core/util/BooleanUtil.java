package com.acjava.mytool.core.util;

/**
 * @Description: Boolean工具类
 * @author: loujm
 * @date: 2023-7-10
 */
public class BooleanUtil {

    /**
     * 检查 {@code Boolean} 值是否为 {@code true}
     *
     * <pre>
     *   BooleanUtil.isTrue(Boolean.TRUE)  = true
     *   BooleanUtil.isTrue(Boolean.FALSE) = false
     *   BooleanUtil.isTrue(null)          = false
     * </pre>
     *
     * @param bool 被检查的Boolean值
     * @return 当值为true且非null时返回{@code true}
     */
    public static boolean isTrue(Boolean bool) {
        return Boolean.TRUE.equals(bool);
    }

    /**
     * 检查 {@code Boolean} 值是否为 {@code false}
     *
     * <pre>
     *   BooleanUtil.isFalse(Boolean.TRUE)  = false
     *   BooleanUtil.isFalse(Boolean.FALSE) = true
     *   BooleanUtil.isFalse(null)          = false
     * </pre>
     *
     * @param bool 被检查的Boolean值
     * @return 当值为false且非null时返回{@code true}
     */
    public static boolean isFalse(Boolean bool) {
        return Boolean.FALSE.equals(bool);
    }

    /**
     * 将boolean转换为字符串
     *
     * <pre>
     *   BooleanUtil.toString(true, "true", "false")   = "true"
     *   BooleanUtil.toString(false, "true", "false")  = "false"
     * </pre>
     *
     * @param bool Boolean值
     * @param trueString 当值为 {@code true}时返回此字符串, 可能为 {@code null}
     * @param falseString 当值为 {@code false}时返回此字符串, 可能为 {@code null}
     * @return 结果值
     */
    public static String toString(boolean bool, String trueString, String falseString) {
        return bool ? trueString : falseString;
    }

    public static String toString(boolean bool) {
        return toString(bool,"true","false");
    }
}

