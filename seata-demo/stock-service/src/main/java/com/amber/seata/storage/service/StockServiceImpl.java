package com.amber.seata.storage.service;

import com.amber.seata.storage.repository.StockDao;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : Amber
 * @create 2022-04-16 19:32
 */
@Service
@Slf4j
public class StockServiceImpl implements StockService {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";

    @Autowired
    StockDao stockDao;

    @Transactional
    @Override
    public void deduct(String commodityCode, int count) {
        String xid = RootContext.getXID();
        log.info("RootContext.inGlobalTransaction()={}", RootContext.inGlobalTransaction());
        log.info("deduct stock balance in transaction: id={} commodityCode={} count={}", xid, commodityCode, count);
        stockDao.update(count, commodityCode);
    }
}
