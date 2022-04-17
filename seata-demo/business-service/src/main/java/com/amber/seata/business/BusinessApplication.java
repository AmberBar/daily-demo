package com.amber.seata.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author : Amber
 * @create 2022-04-16 20:04
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class, args);
    }
}
