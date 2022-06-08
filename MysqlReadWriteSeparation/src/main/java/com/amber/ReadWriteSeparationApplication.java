package com.amber;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.amber.mapper")
@SpringBootApplication
public class ReadWriteSeparationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadWriteSeparationApplication.class, args);
    }
}
