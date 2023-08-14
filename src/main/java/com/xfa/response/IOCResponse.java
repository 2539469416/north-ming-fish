package com.xfa.response;

import lombok.Data;

/**
 * @author 北冥有鱼
 */
@Data
public class IOCResponse<T> {
    private T data;
    private Integer response_code;
    private String verboseMsg;
}
