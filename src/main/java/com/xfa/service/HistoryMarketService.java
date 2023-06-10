package com.xfa.service;

import com.alibaba.fastjson2.JSON;
import com.xfa.entity.IFindApiUrlEntity;
import com.xfa.entity.RedisKeyEntity;
import com.xfa.response.IFindResponse;
import com.xfa.response.TokenResponse;
import com.xfa.util.OkHttpUtils;
import com.xfa.util.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author 北冥有鱼
 */
@Service
public class HistoryMarketService {
    @Resource
    private RedisUtil redisUtil;

    public void getHistoryMarket(String codes,String startdate,String enddate){
        String accessToken = (String) redisUtil.get(RedisKeyEntity.THS_ACCESS_TOKEN);
        String resp = OkHttpUtils.builder().url(IFindApiUrlEntity.HISTORY_MARKET_URL)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("access_token",accessToken)
                .addParam("codes",codes)
                .addParam("enddate",enddate)
                .addParam("indicators",IFindApiUrlEntity.INDICATORS_ONE)
                .addParam("startdate",startdate)
                .post(true)
                .sync();

    }
}
