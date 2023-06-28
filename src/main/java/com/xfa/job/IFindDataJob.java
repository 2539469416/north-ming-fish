package com.xfa.job;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.xfa.entity.IFindApiUrlEntity;
import com.xfa.entity.RedisKeyEntity;
import com.xfa.response.IFindResponse;
import com.xfa.response.TokenResponse;
import com.xfa.util.OkHttpUtils;
import com.xfa.util.RedisUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 北冥有鱼
 */
@Component
@Log4j2
@EnableScheduling
public class IFindDataJob {
    @Value("${ths.refresh_token}")
    private String refreshToken;

    @Resource
    private RedisUtil redisUtil;



    /**
     * 初始化Token
     */
    @Bean
    public void init() {
        log.info("初始化Token");
        String response = OkHttpUtils.builder().url(IFindApiUrlEntity.GET_ACCESS_TOKEN)
                .addParam("refresh_token", refreshToken)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .post(true)
                .sync();
        IFindResponse<TokenResponse> iFindResponse = JSON.parseObject(response, new TypeReference<IFindResponse<TokenResponse>>(){});
        redisUtil.set(RedisKeyEntity.THS_ACCESS_TOKEN, iFindResponse.getData().getAccessToken());
    }

    /**
     * 更新Token
     */
    @Scheduled(cron = "0 0 0 ? * 6")
    public void continuationToken() {
        log.info("更新Token");
        String response = OkHttpUtils.builder().url(IFindApiUrlEntity.UPDATE_ACCESS_TOKEN)
                .addParam("refresh_token", refreshToken)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .post(true)
                .sync();
        IFindResponse<TokenResponse> iFindResponse = JSON.parseObject(response, new TypeReference<IFindResponse<TokenResponse>>(){});
        redisUtil.set(RedisKeyEntity.THS_ACCESS_TOKEN, iFindResponse.getData().getAccessToken());
    }
}
