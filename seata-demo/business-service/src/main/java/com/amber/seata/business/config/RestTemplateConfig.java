package com.amber.seata.business.config;

import com.amber.seata.business.interceptor.SeataRestTemplateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author : Amber
 * @create 2022-04-16 20:10
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new SeataRestTemplateInterceptor());
        return restTemplate;
    }
}
