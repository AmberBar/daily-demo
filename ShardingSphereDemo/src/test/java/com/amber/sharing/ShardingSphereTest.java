package com.amber.sharing;

import com.amber.sharding.ShardingApplication;
import com.amber.sharding.entity.CommonConfig;
import com.amber.sharding.entity.ProductOrder;
import com.amber.sharding.mapper.CommonConfigMapper;
import com.amber.sharding.mapper.ProductOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)  //底层用junit  SpringJUnit4ClassRunner
@SpringBootTest(classes = ShardingApplication.class)
@Slf4j
public class ShardingSphereTest {

    @Autowired
    private ProductOrderMapper productOrderMapper;
    @Autowired
    private CommonConfigMapper commonConfigMapper;

    @Test
    public void testSaveProductOrder() {

        for (int i = 0; i < 10; i++) {
            ProductOrder productOrder = new ProductOrder();
            productOrder.setCreateTime(new Date());
            productOrder.setNickname("amber=" + i);
            productOrder.setOutTradeNo(UUID.randomUUID().toString().substring(0, 32));
            productOrder.setPayAmount(100.00);
            productOrder.setState("PAY");
            productOrder.setUserId(Long.valueOf(i + ""));
            productOrderMapper.insert(productOrder);
        }
    }

    /**
     * 添加广播表
     */
    @Test
    public void testSaveCommonConfig() {

        for (int i = 0; i < 10; i++) {
            CommonConfig commonConfig = new CommonConfig();
            commonConfig.setConfigKey(String.valueOf(i));
            commonConfig.setType("amber");
            commonConfig.setConfigValue("i = " + i);

            commonConfigMapper.insert(commonConfig);
        }
    }
}