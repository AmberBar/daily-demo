package com.amber.seata.order.service.impl;

import com.amber.seata.order.entity.Order;
import com.amber.seata.order.repository.OrderDAO;
import com.amber.seata.order.service.OrderService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Author : Amber
 * @create 2022-04-16 18:49
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
//    @Autowired
//    private AccountFeignClient accountFeignClient;

    @Autowired
    private OrderDAO orderDAO;


    @Transactional
    @Override
    public Order create(String userId, String commodityCode, int orderCount) {
        log.info("xid={}", RootContext.getXID());
        log.info("userId={} commodityCode={} orderCount={}", userId, commodityCode, orderCount);
        BigDecimal orderMoney = new BigDecimal(orderCount).multiply(new BigDecimal(5));

        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityCode(commodityCode);
        order.setCount(orderCount);
        order.setMoney(orderMoney);

        orderDAO.save(order);
//        accountFeignClient.debit(userId, orderMoney);
        return order;
    }
}
