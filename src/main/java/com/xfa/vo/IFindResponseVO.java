package com.xfa.vo;

import lombok.ToString;

/**
 * @author 北冥有鱼
 */
@ToString
public class IFindResponseVO<T> {
    private Integer errorCode;
    private String errMsg;
    private T data;
}
