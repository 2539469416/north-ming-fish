package com.xfa;

import com.xfa.service.HistoryMarketService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NorthMingFishApplicationTests {

    @Resource
    private HistoryMarketService historyMarketService;
    @Test
    void contextLoads() {
        historyMarketService.getHistoryMarket("601633.SH","2023-01-01","2023-01-07");
    }

}
