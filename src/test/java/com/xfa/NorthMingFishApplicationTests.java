package com.xfa;

import com.xfa.service.impl.ThreatbookAPLServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class NorthMingFishApplicationTests {
    @Resource
    private ThreatbookAPLServiceImpl threatbookAPLServiceImpl;
    @Test
    void test1(){
        threatbookAPLServiceImpl.getThreatbookIPIOC("49.232.153.55");
    }
}
