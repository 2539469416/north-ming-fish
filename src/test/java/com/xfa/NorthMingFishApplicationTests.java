package com.xfa;

import com.xfa.service.impl.HistoryMarketServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class NorthMingFishApplicationTests {

    @Resource
    private HistoryMarketServiceImpl historyMarketServiceImpl;
    @Test
    void contextLoads() {
        historyMarketServiceImpl.getHistoryMarket("601633.SH","2023-01-01","2023-06-28");
    }

}
