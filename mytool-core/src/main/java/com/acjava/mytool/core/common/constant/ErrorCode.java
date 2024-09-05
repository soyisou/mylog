package com.acjava.mytool.core.common.constant;

/**
 * @Description: 待补充
 * @author: loujm
 * @date: 2023/6/6 17:29
 */
public enum ErrorCode {
    USER_NOT_EXIST(1001, "用户不存在"),
    PASSWORD_ERROR(1002, "密码错误")
    ;

    /**
     * 错误码
     */
    private final int code;

    /**
     * 错误信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
