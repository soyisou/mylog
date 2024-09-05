package com.acjava.mytool.core.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 待补充
 * @author: loujm
 * @date: 2023/6/6 17:21
 */
@Data
@AllArgsConstructor
public class Error implements Serializable {

    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 提示信息，有错误时，前端可以获取该字段进行提示
     */
    private String errorMsg;
}
