package com.amber.seata.order.service;

import com.amber.seata.order.entity.Order;

/**
 * @Author : Amber
 * @create 2022-04-16 18:47
 */
public interface OrderService {
    /**
     * 创建订单
     */
    Order create(String userId, String commodityCode, int orderCount);
}
