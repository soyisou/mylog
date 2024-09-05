package com.acjava.mytool.core.util.time.constant;

/**
 * 常量命名规则：
 * <ul>
 *     <li>参考LocalDate：DATE <==> 年月日</li>
 *     <li>参考LocalTIME：TIME <==> 时分秒</li>
 *     <li>参考LocalDATETIME：DATETIME <==> 年月日时分秒</li>
 *     <li>如果在以上三点之外的：DATE_MINUTE <==> 年月日时分</li>
 * </ul>
 *
 *
 * @Description: DateTimeFormatter的Pattern常量
 * @author: loujm
 * @date: 2023-7-10
 * @see java.time.format.DateTimeFormatter
 */
public class Pattern {
    /**
     * 年月格式：yyyy-MM
     */
    public static final String MONTH = "yyyy-MM";

    /**
     * 标准日期格式：yyyy-MM-dd
     */
    public static final String DATE = "yyyy-MM-dd";

    /**
     * 标准时间格式：HH:mm:ss
     */
    public static final String TIME = "HH:mm:ss";

    /**
     * 标准日期时间格式，精确到分：yyyy-MM-dd HH:mm
     */
    public static final String DATE_MINUTE = "yyyy-MM-dd HH:mm";

    /**
     * 标准日期时间格式，精确到秒：yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 标准日期时间格式，精确到毫秒：yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String DATETIME_MS = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 标准日期格式：yyyy年MM月dd日
     */
    public static final String CHINESE_DATE = "yyyy年MM月dd日";

    /**
     * 标准日期格式：yyyy年MM月dd日 HH时mm分ss秒
     */
    public static final String CHINESE_DATETIME = "yyyy年MM月dd日HH时mm分ss秒";

}
