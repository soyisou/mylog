package com.acjava.mytool.core.util.time;

import com.acjava.mytool.core.util.ObjectUtil;
import com.acjava.mytool.core.util.StringUtil;
import com.acjava.mytool.core.util.time.constant.Pattern;
import com.acjava.mytool.core.util.time.enums.WeekEnum;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: Date的工具类
 * @author: loujm
 * @date: 2023-7-10
 */
public class DateUtil {

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将Date类型转为String类型<br>
     *
     * @param date 转换的Date对象
     * @return 转换后的时间字符串，如果原Date为{@code null}，则返回{@code null}
     */
    public static String toString(Date date) {
        return ObjectUtil.isNull(date) ? null : new SimpleDateFormat(DEFAULT_PATTERN).format(date);
    }

    /**
     * 将Date类型转为String类型<br>
     *
     * @param date 转换的Date对象
     * @param pattern 模版 如果为空则用默认模版， 如果非法则解析抛出异常
     * @return 转换后的时间字符串，如果原Date为{@code null}，则返回{@code null}
     */
    public static String toString(Date date, String pattern) {
        if (ObjectUtil.isNull(date)) {
            return null;
        }
        if (StringUtil.isBlank(pattern) || pattern.equals(DEFAULT_PATTERN)) {
            return DateUtil.toString(date);
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * Date转为timestamp
     * @param date
     * @return
     */
    public static long toTimestamp(Date date) {
        return date.getTime();
    }

    /**
     * timestamp转Date
     * @param timestamp
     * @return
     */
    public static Date date(long timestamp) {
        return new Date(timestamp);
    }

    /**
     * 获取给定Date是周几
     * @param date
     * @return
     */
    public static WeekEnum getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return WeekEnum.values()[dayOfWeek - 1];
    }

    /**
     * 当前时间，格式 yyyy-MM-dd HH:mm:ss
     *
     * @return 当前时间的标准形式字符串
     */
    public static String now() {
        return DateUtil.toString(new Date());
    }

    /**
     * 当前日期，格式 yyyy-MM-dd
     *
     * @return 当前日期的标准形式字符串
     */
    public static String today() {
        return DateUtil.toString(new Date(), Pattern.DATE);
    }

    // 运算
    //比较
}
