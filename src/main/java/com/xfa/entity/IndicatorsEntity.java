package com.xfa.entity;

import lombok.Data;

/**
 * @author 北冥有鱼
 */
@Data
public class IndicatorsEntity {
    /**
     * 前收盘价
     */
    private double preClose;

    /**
     * 开盘价
     */
    private double open;

    /**
     * 最高价
     */
    private double high;

    /**
     * 最低价
     */
    private double low;

    /**
     * 收盘价
     */
    private double close;

    /**
     * 均价
     */
    private double avgPrice;

    /**
     * 涨跌
     */
    private double change;

    /**
     * 涨跌幅
     */
    private double changeRatio;

    /**
     * 成交量
     */
    private long volume;

    /**
     * 成交额
     */
    private double amount;

    /**
     * 换手率
     */
    private double turnoverRatio;

    /**
     * 成交笔数
     */
    private long transactionAmount;

    /**
     * 总股本
     */
    private long totalShares;

    /**
     * 总市值
     */
    private double totalCapital;

    /**
     * A股流通股本
     */
    private long floatSharesOfAShares;

    /**
     * B股流通股本
     */
    private long floatSharesOfBShares;

    /**
     * A股流通市值
     */
    private double floatCapitalOfAShares;

    /**
     * B股流通市值
     */
    private double floatCapitalOfBShares;

    /**
     * 市盈率（TTM）
     */
    private double peTtm;

    /**
     * PE市盈率
     */
    private double pe;

    /**
     * PB市净率
     */
    private double pb;

    /**
     * PS市销率
     */
    private double ps;

    /**
     * PCF市现率
     */
    private double pcf;

    /**
     * 交易状态
     */
    private String thsTradingStatusStock;

    /**
     * 涨跌停状态
     */
    private String thsUpAndDownStatusStock;

    /**
     * 复权因子
     */
    private double thsAfStock;

    /**
     * 盘后成交量
     */
    private long thsVolAfterTradingStock;

    /**
     * 盘后成交笔数
     */
    private long thsTransNumAfterTradingStock;

    /**
     * 盘后成交额
     */
    private double thsAmtAfterTradingStock;

    /**
     * 有效换手率
     */
    private double thsValidTurnoverStock;

}
