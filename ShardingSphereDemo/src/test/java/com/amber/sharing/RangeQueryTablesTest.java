package com.amber.sharing;

import com.amber.sharding.ShardingApplication;
import com.amber.sharding.entity.ProductOrder;
import com.amber.sharding.mapper.ProductOrderMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)  //底层用junit  SpringJUnit4ClassRunner
@SpringBootTest(classes = ShardingApplication.class)
@Slf4j
public class RangeQueryTablesTest {

    @Autowired
    private ProductOrderMapper productOrderMapper;

    /**
     * 如果不设置范围策略，则会报错
     * spring.shardingsphere.sharding.tables.product_order.table-strategy.standard.rangeAlgorithmClassName=com.amber.sharding.algorithm.CustomRangeShardingAlgorithm
     *  Cannot find range sharding strategy in sharding rule.
     *
     */
    @Test
    public void testQueryProductOrder() {
        productOrderMapper.selectList(new QueryWrapper<ProductOrder>().between("id", 1534235771701755906L, 1534235771957608450L));
    }

}