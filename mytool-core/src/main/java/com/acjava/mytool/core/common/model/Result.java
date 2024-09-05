package com.acjava.mytool.core.common.model;

import com.acjava.mytool.core.common.constant.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 待补充
 * @author: loujm
 * @date: 2023/6/6 17:05
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 状态码 失败0 成功1
     */
    private Integer code;

    /**
     * 失败：Error封装了错误的信息
     */
    private Error error;

    /**
     * 成功：查询到的结果数据
     */
    private T data;


    public Result(Integer errorCode, String errorMsg) {
        this.code = ResultCode.FAIL;
        this.error = new Error(errorCode, errorMsg);
    }

    public Result(T data) {
        this.code = ResultCode.SUCCESS;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    public static Result fail(Integer errorCode, String errorMsg) {
        return new Result(errorCode, errorMsg);
    }

    public boolean isSuccess() {
        return this.code == 1;
    }

}
