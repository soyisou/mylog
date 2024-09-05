package com.acjava.mytool.core.util.regex;

import java.util.regex.Pattern;


/**
 * @Description: 正则表达式工具
 * @author: loujm
 * @date: 2023/7/11
 */
public class RegexUtils {

    /**
     * 给定内容是否匹配正则
     *
     * @param pattern 模式
     * @param content 内容
     * @return 正则为null或者""则不检查，false，内容为null返回false
     */
    public static boolean isMatch(Pattern pattern, String content) {
        if (content == null || pattern == null) {
            // 提供null的字符串为不匹配
            return false;
        }
        return pattern.matcher(content).matches();
    }
}
