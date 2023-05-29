package com.xfa.job;

import com.xfa.entity.IFindApiUrlEntity;
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

    @Bean
    public void init(){
        String response = OkHttpUtils.builder().url(IFindApiUrlEntity.GETACCESSTOKEN)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addParam("refresh_token",refreshToken)
                .get()
                .sync();
        System.out.println(response);
    }

    /**
     * 测试
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void task(){
        System.out.println(refreshToken);
    }

    public void continuationToken(){

    }
}
