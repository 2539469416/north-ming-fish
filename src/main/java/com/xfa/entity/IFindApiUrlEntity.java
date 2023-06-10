package com.xfa.entity;

import lombok.Data;

/**
 * @author 北冥有鱼
 */
@Data
public class IFindApiUrlEntity {
    public static final String GET_ACCESS_TOKEN = "https://ft.10jqka.com.cn/api/v1/get_access_token";
    public static final String UPDATE_ACCESS_TOKEN = "https://ft.10jqka.com.cn/api/v1/update_access_token";

    public static final String HISTORY_MARKET_URL = "https://ft.10jqka.com.cn/api/v1/cmd_history_quotation";

    public static final String INDICATORS_ONE = "preClose,open,high,low,close,avgPrice,change,changeRatio,volume,amount,turnoverRatio,transactionAmount,totalShares,totalCapital";
}
