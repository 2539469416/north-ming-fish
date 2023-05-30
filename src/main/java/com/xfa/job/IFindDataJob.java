package com.xfa.job;

import com.alibaba.fastjson2.JSON;
import com.xfa.entity.IFindApiUrlEntity;
import com.xfa.response.TokenResponse;
import com.xfa.response.IFindResponse;
import com.xfa.util.OkHttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 北冥有鱼
 */
@Component
@EnableScheduling
public class IFindDataJob {
    @Value("${ths.refresh_token}")
    private String refreshToken;

    /**
     * 初始化Token
     */
    @Bean
    public void init(){
        String response = OkHttpUtils.builder().url(IFindApiUrlEntity.GETACCESSTOKEN)
                .addParam("refresh_token",refreshToken)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .get()
                .sync();
        IFindResponse<TokenResponse> iFindResponse = JSON.parseObject(response, IFindResponse.class);

    }

    /**
     * 更新Token
     */
    @Scheduled(cron = "0 0 0 ? * 6")
    public void continuationToken(){
        String resp = OkHttpUtils.builder().url(IFindApiUrlEntity.UPDATEACCESSTOKEN)
                .addParam("refresh_token",refreshToken)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .get()
                .sync();
        IFindResponse<TokenResponse> iFindResponse = JSON.parseObject(resp, IFindResponse.class);
    }


}
