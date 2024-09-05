package com.acjava.mytool.core.util.time.constant;

import java.time.format.DateTimeFormatter;

/**
 * @Description: DateTimeFormatter的常量
 * @author: loujm
 * @date: 2023-7-10
 * @see DateTimeFormatter
 * @see Pattern
 */
public class Formatter {

    /**
     * 年月格式：yyyy-MM
     */
    public static final DateTimeFormatter MONTH = DateTimeFormatter.ofPattern(Pattern.MONTH);

    /**
     * 标准日期格式：yyyy-MM-dd
     */
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern(Pattern.DATE);

    /**
     * 标准时间格式：HH:mm:ss
     */
    public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern(Pattern.TIME);

    /**
     * 标准日期时间格式，精确到分：yyyy-MM-dd HH:mm
     */
    public static final DateTimeFormatter DATE_MINUTE = DateTimeFormatter.ofPattern(Pattern.DATE_MINUTE);

    /**
     * 标准日期时间格式，精确到秒：yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter DATETIME = DateTimeFormatter.ofPattern(Pattern.DATETIME);

    /**
     * 标准日期格式：yyyy年MM月dd日
     */
    public static final DateTimeFormatter CHINESE_DATE = DateTimeFormatter.ofPattern(Pattern.CHINESE_DATE);

    /**
     * 标准日期格式：yyyy年MM月dd日HH时mm分ss秒
     */
    public static final DateTimeFormatter CHINESE_DATETIME = DateTimeFormatter.ofPattern(Pattern.CHINESE_DATETIME);

}
