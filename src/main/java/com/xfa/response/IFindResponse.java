package com.xfa.response;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author 北冥有鱼
 */
@ToString
@Data
public class IFindResponse<T> {
    private Integer errorCode;
    private String errMsg;
    @JSONField(alternateNames = {"data","tables"})
    private T data;
}
