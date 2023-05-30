package com.xfa.response;

import lombok.Data;
import lombok.ToString;

/**
 * @author 北冥有鱼
 */
@ToString
@Data
public class IFindResponse<T> {
    private Integer errorCode;
    private String errMsg;
    private T data;
}
