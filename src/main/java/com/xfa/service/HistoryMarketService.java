package com.xfa.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.xfa.entity.HistoryMarketEntity;
import com.xfa.entity.IFindApiUrlEntity;
import com.xfa.entity.RedisKeyEntity;
import com.xfa.response.HistoryMarketResponse;
import com.xfa.response.HistoryTableResponse;
import com.xfa.response.IFindResponse;
import com.xfa.response.TokenResponse;
import com.xfa.util.OkHttpUtils;
import com.xfa.util.RedisUtil;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author 北冥有鱼
 */
@Service
@Log4j2
public class HistoryMarketService {
    @Resource
    private RedisUtil redisUtil;

    public void getHistoryMarket(String codes, String startdate, String enddate) {
        String accessToken = (String) redisUtil.get(RedisKeyEntity.THS_ACCESS_TOKEN);
        String response = OkHttpUtils.builder().url(IFindApiUrlEntity.HISTORY_MARKET_URL)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("access_token", accessToken)
                .addParam("codes", codes)
                .addParam("enddate", enddate)
                .addParam("indicators", IFindApiUrlEntity.INDICATORS_ONE)
                .addParam("startdate", startdate)
                .post(true)
                .sync();
        IFindResponse<ArrayList<HistoryMarketResponse<HistoryTableResponse>>> iFindResponse = JSON.parseObject(response, new TypeReference<IFindResponse<ArrayList<HistoryMarketResponse<HistoryTableResponse>>>>() {
        });
        if (iFindResponse.getErrorCode() == 0) {
            iFindResponse.getData().stream().map(this::formatHistoryMarketResponse);
        } else {
            log.error("历史行情查询失败：" + iFindResponse.getErrMsg());
        }
    }

    public boolean formatHistoryMarketResponse(HistoryMarketResponse<HistoryTableResponse> source) {
        String code = source.getThsCode();
        String[] timeList = source.getTime();
        HistoryTableResponse table = source.getTable();

        Double[] preCloses = table.getPreClose();
        Double[] opens = table.getOpen();
        Double[] highs = table.getHigh();
        Double[] lows = table.getLow();
        Double[] closes = table.getClose();
        Double[] avgPrices = table.getAvgPrice();
        Double[] changes = table.getChange();
        Double[] changeRatios = table.getChangeRatio();
        Long[] volumes = table.getVolume();
        Double[] amounts = table.getAmount();
        Double[] turnoverRatios = table.getTurnoverRatio();
        Long[] transactionAmounts = table.getTransactionAmount();
        Long[] totalShares = table.getTotalShares();
        Double[] totalCapitals = table.getTotalCapital();
        Long[] floatSharesOfAShares = table.getFloatSharesOfAShares();
        Long[] floatSharesOfBShares = table.getFloatSharesOfBShares();
        Double[] floatCapitalsOfAShares = table.getFloatCapitalOfAShares();
        Double[] floatCapitalsOfBShares = table.getFloatCapitalOfBShares();
        Double[] peTtms = table.getPeTtm();
        Double[] pes = table.getPe();
        Double[] pbs = table.getPb();
        Double[] pss = table.getPs();
        Double[] pcfs = table.getPcf();
        String[] thsTradingStatusStocks = table.getThsTradingStatusStock();
        String[] thsUpAndDownStatusStocks = table.getThsUpAndDownStatusStock();
        Double[] thsAfStocks = table.getThsAfStock();
        Long[] thsVolAfterTradingStocks = table.getThsVolAfterTradingStock();
        Long[] thsTransNumAfterTradingStocks = table.getThsTransNumAfterTradingStock();
        Double[] thsAmtAfterTradingStocks = table.getThsAmtAfterTradingStock();
        Double[] thsValidTurnoverStocks = table.getThsValidTurnoverStock();

        for (int i = 0; i < timeList.length - 1; i++){
            try {
                HistoryMarketEntity target = new HistoryMarketEntity();
                Double preClose = preCloses[i];
                Double high = highs[i];
                Double open = opens[i];
                Double low = lows[i];
                Double close = closes[i];
                Double avgPrice = avgPrices[i];
                Double change = changes[i];
                Double changeRatio = changeRatios[i];
                Long volume = volumes[i];
                Double amount = amounts[i];
                Double turnoverRatio = turnoverRatios[i];
                Long transactionAmount = transactionAmounts[i];
                Long totalShare = totalShares[i];
                Double totalCapital = totalCapitals[i];
                Long floatSharesOfAShare = floatSharesOfAShares[i];
                Long floatSharesOfBShare = floatSharesOfBShares[i];
                Double floatCapitalOfAShares = floatCapitalsOfAShares[i];
                Double floatCapitalOfBShares = floatCapitalsOfBShares[i];
                Double peTtm = peTtms[i];
                Double pe = pes[i];
                Double pb = pbs[i];
                Double ps = pss[i];
                Double pcf = pcfs[i];
                String thsTradingStatusStock = thsTradingStatusStocks[i];
                String thsUpAndDownStatusStock = thsUpAndDownStatusStocks[i];
                Double thsAfStock = thsAfStocks[i];
                Long thsVolAfterTradingStock = thsVolAfterTradingStocks[i];
                Long thsTransNumAfterTradingStock = thsTransNumAfterTradingStocks[i];
                Double thsAmtAfterTradingStock = thsAmtAfterTradingStocks[i];
                Double thsValidTurnoverStock = thsValidTurnoverStocks[i];


            }catch (Exception e){
                log.error("历史行情格式化数据异常：");
            }
        }
        return true;
    }


}
