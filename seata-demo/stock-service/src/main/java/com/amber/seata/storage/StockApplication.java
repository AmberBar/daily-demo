package com.amber.seata.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author : Amber
 * @create 2022-04-16 19:30
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class StockApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }
}
