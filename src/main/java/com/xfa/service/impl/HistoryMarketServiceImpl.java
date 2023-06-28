package com.xfa.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xfa.entity.HistoryMarketEntity;
import com.xfa.entity.IFindApiUrlEntity;
import com.xfa.entity.RedisKeyEntity;
import com.xfa.mapper.HistoryTableMapper;
import com.xfa.response.HistoryMarketResponse;
import com.xfa.response.HistoryTableResponse;
import com.xfa.response.IFindResponse;
import com.xfa.service.HistoryMarketService;
import com.xfa.util.OkHttpUtils;
import com.xfa.util.RedisUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 北冥有鱼
 */
@Service
@Log4j2
public class HistoryMarketServiceImpl extends ServiceImpl<HistoryTableMapper, HistoryMarketEntity> implements HistoryMarketService {
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
            List<Boolean> result = iFindResponse.getData().stream().map(this::formatHistoryMarketResponse).collect(Collectors.toList());
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

        for (int i = 0; i < timeList.length - 1; i++) {
            try {
                HistoryMarketEntity target = new HistoryMarketEntity();
                Double preClose = preCloses != null && i < preCloses.length ? preCloses[i] : null;
                Double high = highs != null && i < highs.length ? highs[i] : null;
                Double open = opens != null && i < opens.length ? opens[i] : null;
                Double low = lows != null && i < lows.length ? lows[i] : null;
                Double close = closes != null && i < closes.length ? closes[i] : null;
                Double avgPrice = avgPrices != null && i < avgPrices.length ? avgPrices[i] : null;
                Double change = changes != null && i < changes.length ? changes[i] : null;
                Double changeRatio = changeRatios != null && i < changeRatios.length ? changeRatios[i] : null;
                Long volume = volumes != null && i < volumes.length ? volumes[i] : null;
                Double amount = amounts != null && i < amounts.length ? amounts[i] : null;
                Double turnoverRatio = turnoverRatios != null && i < turnoverRatios.length ? turnoverRatios[i] : null;
                Long transactionAmount = transactionAmounts != null && i < transactionAmounts.length ? transactionAmounts[i] : null;
                Long totalShare = totalShares != null && i < totalShares.length ? totalShares[i] : null;
                Double totalCapital = totalCapitals != null && i < totalCapitals.length ? totalCapitals[i] : null;
                Long floatSharesOfAShare = floatSharesOfAShares != null && i < floatSharesOfAShares.length ? floatSharesOfAShares[i] : null;
                Long floatSharesOfBShare = floatSharesOfBShares != null && i < floatSharesOfBShares.length ? floatSharesOfBShares[i] : null;
                Double floatCapitalOfAShares = floatCapitalsOfAShares != null && i < floatCapitalsOfAShares.length ? floatCapitalsOfAShares[i] : null;
                Double floatCapitalOfBShares = floatCapitalsOfBShares != null && i < floatCapitalsOfBShares.length ? floatCapitalsOfBShares[i] : null;
                Double peTtm = peTtms != null && i < peTtms.length ? peTtms[i] : null;
                Double pe = pes != null && i < pes.length ? pes[i] : null;
                Double pb = pbs != null && i < pbs.length ? pbs[i] : null;
                Double ps = pss != null && i < pss.length ? pss[i] : null;
                Double pcf = pcfs != null && i < pcfs.length ? pcfs[i] : null;
                String thsTradingStatusStock = thsTradingStatusStocks != null && i < thsTradingStatusStocks.length ? thsTradingStatusStocks[i] : null;
                String thsUpAndDownStatusStock = thsUpAndDownStatusStocks != null && i < thsUpAndDownStatusStocks.length ? thsUpAndDownStatusStocks[i] : null;
                Double thsAfStock = thsAfStocks != null && i < thsAfStocks.length ? thsAfStocks[i] : null;
                Long thsVolAfterTradingStock = thsVolAfterTradingStocks != null && i < thsVolAfterTradingStocks.length ? thsVolAfterTradingStocks[i] : null;
                Long thsTransNumAfterTradingStock = thsTransNumAfterTradingStocks != null && i < thsTransNumAfterTradingStocks.length ? thsTransNumAfterTradingStocks[i] : null;
                Double thsAmtAfterTradingStock = thsAmtAfterTradingStocks != null && i < thsAmtAfterTradingStocks.length ? thsAmtAfterTradingStocks[i] : null;
                Double thsValidTurnoverStock = thsValidTurnoverStocks != null && i < thsValidTurnoverStocks.length ? thsValidTurnoverStocks[i] : null;


                target.setCode(code);
                target.setTime(timeList[i]);
                target.setPreClose(preClose);
                target.setOpen(open);
                target.setHigh(high);
                target.setLow(low);
                target.setClose(close);
                target.setAvgPrice(avgPrice);
                target.setChanges(change);
                target.setChangeRatio(changeRatio);
                target.setVolume(volume);
                target.setAmount(amount);
                target.setTurnoverRatio(turnoverRatio);
                target.setTransactionAmount(transactionAmount);
                target.setTotalShares(totalShare);
                target.setTotalCapital(totalCapital);
                target.setFloatSharesOfAShares(floatSharesOfAShare);
                target.setFloatSharesOfBShares(floatSharesOfBShare);
                target.setFloatCapitalOfAShares(floatCapitalOfAShares);
                target.setFloatCapitalOfBShares(floatCapitalOfBShares);
                target.setPeTtm(peTtm);
                target.setPe(pe);
                target.setPb(pb);
                target.setPs(ps);
                target.setPcf(pcf);
                target.setThsTradingStatusStock(thsTradingStatusStock);
                target.setThsUpAndDownStatusStock(thsUpAndDownStatusStock);
                target.setThsAfStock(thsAfStock);
                target.setThsVolAfterTradingStock(thsVolAfterTradingStock);
                target.setThsTransNumAfterTradingStock(thsTransNumAfterTradingStock);
                target.setThsAmtAfterTradingStock(thsAmtAfterTradingStock);
                target.setThsValidTurnoverStock(thsValidTurnoverStock);
                this.save(target);
                log.info(timeList[i] + " 历史行情存储成功");
            } catch (NullPointerException e) {
                log.error("历史行情格式化数据异常：" + e);
                StackTraceElement[] stackTrace = e.getStackTrace();
                if (stackTrace.length > 0) {
                    StackTraceElement errorElement = stackTrace[0];
                    String className = errorElement.getClassName();
                    String methodName = errorElement.getMethodName();
                    int lineNumber = errorElement.getLineNumber();
                    log.error("错误位置：类名={}, 方法名={}, 行号={}", className, methodName, lineNumber);
                }
            }
        }
        return true;
    }


}
