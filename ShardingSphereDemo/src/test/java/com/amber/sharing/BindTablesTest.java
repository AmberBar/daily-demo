package com.amber.sharing;

import com.amber.sharding.ShardingApplication;
import com.amber.sharding.entity.CommonConfig;
import com.amber.sharding.entity.ProductOrder;
import com.amber.sharding.mapper.CommonConfigMapper;
import com.amber.sharding.mapper.ProductOrderMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)  //底层用junit  SpringJUnit4ClassRunner
@SpringBootTest(classes = ShardingApplication.class)
@Slf4j
public class BindTablesTest {

    @Autowired
    private ProductOrderMapper productOrderMapper;

    /**
     * 2022-06-08 01:17:22.392  INFO 42992 --- [           main] ShardingSphere-SQL                       : Actual SQL: ds0 ::: select * from product_order_0 t1 left join product_order_item_0 t2 on t1.id = t2.product_order_id
     * 2022-06-08 01:17:22.392  INFO 42992 --- [           main] ShardingSphere-SQL                       : Actual SQL: ds0 ::: select * from product_order_1 t1 left join product_order_item_1 t2 on t1.id = t2.product_order_id
     * 2022-06-08 01:17:22.392  INFO 42992 --- [           main] ShardingSphere-SQL                       : Actual SQL: ds1 ::: select * from product_order_0 t1 left join product_order_item_0 t2 on t1.id = t2.product_order_id
     * 2022-06-08 01:17:22.393  INFO 42992 --- [           main] ShardingSphere-SQL                       : Actual SQL: ds1 ::: select * from product_order_1 t1 left join product_order_item_1 t2 on t1.id = t2.product_order_id
     */
    @Test
    public void testQueryProductOrder() {

        List<ProductOrder> productOrders = productOrderMapper.selectWithDetail();
        log.info("productOrders={}", productOrders);
    }

}