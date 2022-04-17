package com.amber.seata.storage.service;

/**
 * @Author : Amber
 * @create 2022-04-16 19:31
 */
public interface StockService {
    void deduct(String commodityCode, int count);
}
