package com.acjava.mytool.core.util;

import com.acjava.mytool.core.util.regex.RegexPatterns;
import com.acjava.mytool.core.util.regex.RegexUtils;

/**
 * @Description: 手机号工具类
 * @author: loujm
 * @date: 2023/7/11
 */
public class PhoneUtil {

    /**
     * 验证是否为手机号码（中国）
     *
     * @param value 值
     * @return 是否为手机号码（中国）
     */
    public static boolean isMobile(String value) {
        return RegexUtils.isMatch(RegexPatterns.MOBILE, value);
    }

    /**
     * 验证是否为座机号码（中国）
     *
     * @param value 值
     * @return 是否为座机号码（中国）
     */
    public static boolean isTel(String value) {
        return RegexUtils.isMatch(RegexPatterns.TEL, value);
    }

    /**
     * 验证是否为座机号码+手机号码（中国）
     *
     * @param value 值
     * @return 是否为座机号码+手机号码（中国）
     */
    public static boolean isPhone(String value) {
        return isMobile(value) || isTel(value);
    }


}
