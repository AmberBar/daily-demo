package com.amber.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 自定义分片策略
 *
 * PreciseShardingAlgorithm 精准分片 用于处理=和IN的分片 是必选的
 */
@Component
public class CustomPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for (String ds : collection) {
            // ID 对 数据库数量进行取模
            String key  = preciseShardingValue.getValue() % collection.size() + "";
            if (ds.endsWith(key)) {
                return ds;
            }
        }
        throw new IllegalArgumentException();
    }
}
