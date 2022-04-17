# Seata + Spring Boot + Spring Data JPA

使用RestTemplate请求RM相关的服务，需要实现拦截器
```
org.springframework.http.client.ClientHttpRequestInterceptor
```
并且把拦截器加入 restTemplate
```java
@Configuration
public class RestTemplateConfig {

    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new SeataRestTemplateInterceptor());
        return restTemplate;
    }
}

```