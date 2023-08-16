package com.xfa;

import com.xfa.service.impl.FoFaAPIServiceImpl;
import com.xfa.service.impl.ThreatbookAPIServiceImpl;
import com.xfa.service.impl.WhoisAPIServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class NorthMingFishApplicationTests {
    @Resource
    private ThreatbookAPIServiceImpl threatbookAPIServiceImpl;

    @Resource
    private FoFaAPIServiceImpl foFaAPIService;
    @Test
    void test1(){
//        threatbookAPLServiceImpl.getThreatbookIPIOC("49.232.153.55");
        foFaAPIService.getDomainInfo("hiwj.top");
    }
}
