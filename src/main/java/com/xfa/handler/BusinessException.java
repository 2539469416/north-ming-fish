package com.xfa.handler;

import lombok.Data;

/**
 * @author liwen
 * 业务异常类
 */
@Data
public class BusinessException extends RuntimeException {

    private Integer code;
    private String message;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
@Data
class ResultCode{
    private Integer code;
    private String message;
}