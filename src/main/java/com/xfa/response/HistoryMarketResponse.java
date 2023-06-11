package com.xfa.response;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author 北冥有鱼
 */
@Data
public class HistoryMarketResponse<T> {
    @JSONField(name = "thscode")
    private String thsCode;

    private String[] time;

    private T table;
}
