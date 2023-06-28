package com.xfa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 北冥有鱼
 */
@Data
@TableName("history_market")
public class HistoryMarketEntity {

    @TableId(value="id",type= IdType.AUTO)
    private String id;

    @TableField
    private String code;

    @TableField
    private String time;

    @TableField
    private Double preClose;

    @TableField
    private Double open;

    @TableField
    private Double high;

    @TableField
    private Double low;

    @TableField
    private Double close;

    @TableField
    private Double avgPrice;

    @TableField
    private Double changes;

    @TableField
    private Double changeRatio;

    @TableField
    private Long volume;

    @TableField
    private Double amount;

    @TableField
    private Double turnoverRatio;

    @TableField
    private Long transactionAmount;

    @TableField
    private Long totalShares;

    @TableField
    private Double totalCapital;

    @TableField
    private Long floatSharesOfAShares;

    @TableField
    private Long floatSharesOfBShares;

    @TableField
    private Double floatCapitalOfAShares;

    @TableField
    private Double floatCapitalOfBShares;

    @TableField
    private Double peTtm;

    @TableField
    private Double pe;

    @TableField
    private Double pb;

    @TableField
    private Double ps;

    @TableField
    private Double pcf;

    @TableField
    private String thsTradingStatusStock;

    @TableField
    private String thsUpAndDownStatusStock;

    @TableField
    private Double thsAfStock;

    @TableField
    private Long thsVolAfterTradingStock;

    @TableField
    private Long thsTransNumAfterTradingStock;

    @TableField
    private Double thsAmtAfterTradingStock;

    @TableField
    private Double thsValidTurnoverStock;

}