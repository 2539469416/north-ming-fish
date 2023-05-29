package com.xfa.job;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 北冥有鱼
 */
@Component
@EnableScheduling
public class GetIFindDataJob {
    @Scheduled(cron = "0/5 * * * * ?")
    public void task(){
        System.out.println("i");
    }
}
