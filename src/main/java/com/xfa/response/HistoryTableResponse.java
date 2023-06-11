package com.xfa.response;

import lombok.Data;

/**
 * @author 北冥有鱼
 */
@Data
public class HistoryTableResponse {
    /**
     * 前收盘价
     */
    private Double[] preClose;

    /**
     * 开盘价
     */
    private Double[] open;

    /**
     * 最高价
     */
    private Double[] high;

    /**
     * 最低价
     */
    private Double[] low;

    /**
     * 收盘价
     */
    private Double[] close;

    /**
     * 均价
     */
    private Double[] avgPrice;

    /**
     * 涨跌
     */
    private Double[] change;

    /**
     * 涨跌幅
     */
    private Double[] changeRatio;

    /**
     * 成交量
     */
    private Long[] volume;

    /**
     * 成交额
     */
    private Double[] amount;

    /**
     * 换手率
     */
    private Double[] turnoverRatio;

    /**
     * 成交笔数
     */
    private Long[] transactionAmount;

    /**
     * 总股本
     */
    private Long[] totalShares;

    /**
     * 总市值
     */
    private Double[] totalCapital;

    /**
     * A股流通股本
     */
    private Long[] floatSharesOfAShares;

    /**
     * B股流通股本
     */
    private Long[] floatSharesOfBShares;

    /**
     * A股流通市值
     */
    private Double[] floatCapitalOfAShares;

    /**
     * B股流通市值
     */
    private Double[] floatCapitalOfBShares;

    /**
     * 市盈率（TTM）
     */
    private Double[] peTtm;

    /**
     * PE市盈率
     */
    private Double[] pe;

    /**
     * PB市净率
     */
    private Double[] pb;

    /**
     * PS市销率
     */
    private Double[] ps;

    /**
     * PCF市现率
     */
    private Double[] pcf;

    /**
     * 交易状态
     */
    private String[] thsTradingStatusStock;

    /**
     * 涨跌停状态
     */
    private String[] thsUpAndDownStatusStock;

    /**
     * 复权因子
     */
    private Double[] thsAfStock;

    /**
     * 盘后成交量
     */
    private Long[] thsVolAfterTradingStock;

    /**
     * 盘后成交笔数
     */
    private Long[] thsTransNumAfterTradingStock;

    /**
     * 盘后成交额
     */
    private Double[] thsAmtAfterTradingStock;

    /**
     * 有效换手率
     */
    private Double[] thsValidTurnoverStock;
}
