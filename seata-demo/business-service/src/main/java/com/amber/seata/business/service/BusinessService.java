package com.amber.seata.business.service;

import com.amber.seata.business.client.OrderClient;
import com.amber.seata.business.client.StockClient;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author : Amber
 * @create 2022-04-16 20:05
 */
@Component
public class BusinessService {

    @Autowired
    StockClient stockClient;

    @Autowired
    OrderClient orderClient;

    @GlobalTransactional(timeoutMills = 6000, name = "business")
    public void purchase(String userId, String commodityCode, int orderCount) {
        stockClient.deduct(commodityCode, orderCount);
        orderClient.create(userId, commodityCode, orderCount);
    }

    @GlobalTransactional(timeoutMills = 6000, name = "business")
    public void purchaseRollback(String userId, String commodityCode, int orderCount) {
        stockClient.deduct(commodityCode, orderCount);
        orderClient.create(userId, commodityCode, orderCount);
        int i = 1/0;
    }
}
