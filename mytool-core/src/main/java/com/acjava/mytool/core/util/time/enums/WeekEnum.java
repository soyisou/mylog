package com.acjava.mytool.core.util.time.enums;

/**
 * @Description: 星期几的枚举类
 * @author: loujm
 * @date: 2023-7-10
 */
public enum WeekEnum {

    // 按照国际标准 ISO-8601，星期天被指定为一周的第一天，用数字 1 来表示。
    SUNDAY("Sunday", 1),
    MONDAY("Monday", 2),
    TUESDAY("Tuesday", 3),
    WEDNESDAY("Wednesday", 4),
    THURSDAY("Thursday", 5),
    FRIDAY("Friday", 6),
    SATURDAY("Saturday", 7);

    private final String displayName;
    private final int numericValue;

    WeekEnum(String displayName, int numericValue) {
        this.displayName = displayName;
        this.numericValue = numericValue;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getNumericValue() {
        return numericValue;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
