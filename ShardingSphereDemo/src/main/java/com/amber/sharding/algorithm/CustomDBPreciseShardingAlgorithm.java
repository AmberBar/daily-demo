package com.amber.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 *
 * 精确分库策略
 */
@Component
public class CustomDBPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for (String ds : collection) {
            // user_id 对 数据库数量进行取模  偶数进入ds0 奇数ds1
            String key  = preciseShardingValue.getValue() % collection.size() + "";
            if (ds.endsWith(key)) {
                return ds;
            }
        }
        throw new IllegalArgumentException();
    }
}
