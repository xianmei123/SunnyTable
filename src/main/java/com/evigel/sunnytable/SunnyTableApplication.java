package com.evigel.sunnytable;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.evigel.sunnytable.mapper")
@EnableTransactionManagement
public class SunnyTableApplication {

    public static void main(String[] args) {
        SpringApplication.run(SunnyTableApplication.class, args);
    }

}
