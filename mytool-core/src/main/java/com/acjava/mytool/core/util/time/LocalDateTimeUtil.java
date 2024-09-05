package com.acjava.mytool.core.util.time;

import com.acjava.mytool.core.util.StringUtil;
import com.acjava.mytool.core.util.time.constant.Formatter;
import com.acjava.mytool.core.util.time.constant.Pattern;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Description: LocalDateTimeUtil的工具类
 * @author: loujm
 * @date: 2023-7-10
 */
public class LocalDateTimeUtil {

    /**
     * @return 返回当前时间的字符串，精确到秒：“yyyy-MM-dd HH:mm:ss”
     */
    public static String now() {
        return format(LocalDateTime.now());
    }

    /**
     *
     * @return 返回当前时间的字符串，精确到毫秒：“yyyy-MM-dd HH:mm:ss.SSS”
     */
    public static String nowMs() {
        return format(LocalDateTime.now(), Pattern.DATETIME_MS);
    }


    /**
     * 默认转为 yyyy-MM-dd HH:mm:ss 格式
     * @param dateTime
     * @return
     * @see #format(LocalDateTime)
     */
    public static String toString(LocalDateTime dateTime) {
        return format(dateTime);
    }


    /**
     * 默认转为 yyyy-MM-dd HH:mm:ss 格式
     * @param dateTime
     * @return
     */
    public static String format(LocalDateTime dateTime) {
        return format(dateTime, Formatter.DATETIME);
    }

    /**
     * 转为指定的格式
     * @param dateTime
     * @param pattern 指定格式的模版
     * @return
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        return format(dateTime, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 转为指定的格式
     * @param dateTime
     * @param formatter 指定格式的格式化器
     * @return
     */
    public static String format(LocalDateTime dateTime, DateTimeFormatter formatter) {
        return dateTime == null ? null : dateTime.format(formatter);
    }

    /**
     * 将时间字符串转为LocalDateTime类型
     * @param timeStr 时间字符串
     * @param pattern 格式字符串
     * @return
     */
    public static LocalDateTime parse(String timeStr, String pattern) {
        return parse(timeStr, DateTimeFormatter.ofPattern(pattern));

    }

    /**
     * 将时间字符串转为LocalDateTime类型
     * @param timeStr 时间字符串
     * @param formatter 格式化器
     * @return
     */
    public static LocalDateTime parse(String timeStr, DateTimeFormatter formatter) {
        if (StringUtil.isEmpty(timeStr)) {
            return null;
        }
        return LocalDateTime.parse(timeStr, formatter);
    }

    /**
     * Date类型转为LocalDateTime类型
     * @param date Date对象
     * @return 对应的LocalDateTime对象
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Date类型转为LocalDate类型
     * @param date Date对象
     * @return 对应的LocalDate对象
     */
    public static LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date类型转为LocalTime类型
     * @param date Date对象
     * @return 对应的LocalTime对象
     */
    public static LocalTime toLocalTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }

    /**
     * LocalDateTime类型转为Date类型
     * @param dateTime LocalDateTime对象
     * @return 对应的Date对象
     */
    public static Date toDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取对应的时间戳
     * @param dateTime LocalDateTime对象
     * @return 对应的时间戳
     */
    public static long getTimestamp(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 将时间戳转为LocalDateTime类型
     * @param timestamp 时间戳
     * @return 对应的LocalDateTime类型
     */
    public static LocalDateTime ofMilli(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
}
