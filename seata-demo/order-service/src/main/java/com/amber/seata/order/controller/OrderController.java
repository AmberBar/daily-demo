package com.amber.seata.order.controller;

import com.amber.seata.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description：
 *
 * @author fangliangsheng
 * @date 2019-04-04
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/debit")
    public Boolean create(String userId, String commodityCode, Integer count) {

        orderService.create(userId, commodityCode, count);
        return true;
    }

}
