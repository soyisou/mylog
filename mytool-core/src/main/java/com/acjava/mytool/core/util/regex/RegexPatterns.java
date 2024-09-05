package com.acjava.mytool.core.util.regex;

import java.util.regex.Pattern;

/**
 * @Description: 正则表达式模版库
 * @author: loujm
 * @date: 2023/7/11
 */
public abstract class RegexPatterns {
    /**
     * 移动电话
     */
    public final static Pattern MOBILE = Pattern.compile("(?:0|86|\\+86)?1[3-9]\\d{9}");

    /**
     * 座机号码
     */
    public final static Pattern TEL = Pattern.compile("0\\d{2,3}-[1-9]\\d{6,7}");

    /**
     * 18位身份证号码
     */
    public final static Pattern CITIZEN_ID = Pattern.compile("[1-9]\\d{5}[1-2]\\d{3}((0\\d)|(1[0-2]))(([012]\\d)|3[0-1])\\d{3}(\\d|X|x)");

    /**
     * 验证码正则, 6位数字或字母
     */
    public static final String VERIFY_CODE_REGEX = "^[a-zA-Z\\d]{6}$";
}
