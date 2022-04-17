package com.amber.cloud.alibaba.util;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author : Amber
 * @create 2022-04-14 9:15
 */
@Slf4j
public class ExceptionUtil {
    public static void handlerException(String name, BlockException ex) {
        log.info("handler exception..被流控了");
        ex.printStackTrace();
    }



}
