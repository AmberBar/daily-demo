package com.amber.cloud.alibaba.controller;

import com.amber.cloud.alibaba.Service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Amber
 * @create 2022-04-14 8:39
 */
@RestController
public class TestController {

    @Autowired
    TestService service;

    @GetMapping(value = "/hello/{name}")
    public String apiHello(@PathVariable String name) {
        return service.sayHello(name);
    }

    @GetMapping(value = "/hello/exception/{name}")
    public String apiHelloWithException(@PathVariable String name) {
        return service.sayHello1(name);
    }


}