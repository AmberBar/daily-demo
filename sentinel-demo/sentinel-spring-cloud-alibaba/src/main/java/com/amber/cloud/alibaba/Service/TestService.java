package com.amber.cloud.alibaba.Service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.amber.cloud.alibaba.util.ExceptionUtil;
import org.springframework.stereotype.Service;

/**
 * @Author : Amber
 * @create 2022-04-14 8:39
 */
@Service
public class TestService {

    @SentinelResource(value = "sayHello")
    public String sayHello(String name) {
        return "Hello, " + name;
    }

    /**
     * blockHandler 和 fallback同时存在时，BlockException 时只会进入 blockHandler 处理逻辑
     *
     *
     * @param name
     * @return
     */
    @SentinelResource(value = "sayHello1", blockHandler = "exceptionHandler", fallback = "sayHelloFallback")
    public String sayHello1(String name) {
        return "Hello, sayHello1" + name;
    }

    /**
     * blockHandler 和 fallback同时存在时，BlockException 时只会进入 blockHandler 处理逻辑
     *
     *
     * @param name
     * @return
     */
    @SentinelResource(value = "sayHello1", blockHandler = "handlerException", blockHandlerClass = ExceptionUtil.class)
    public String sayHelloBlockHandlerClass(String name) {
        return "Hello, sayHello1" + name;
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String exceptionHandler(String name, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return "Oops, error occurred at ...";
    }

    public String sayHelloFallback(String name) {
        return "这是fallback";
    }
}