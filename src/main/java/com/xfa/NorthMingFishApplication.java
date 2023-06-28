package com.xfa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author 北冥有鱼
 */
@SpringBootApplication
@MapperScan(value = "com.xfa.mapper")
public class NorthMingFishApplication {

    public static void main(String[] args) {
        SpringApplication.run(NorthMingFishApplication.class, args);
    }

}
