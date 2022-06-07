package com.amber.sharding.algorithm;

import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 表的范围查找，
 * 用于处理BETWEEN AND语法，没配置的话会报错 Cannot find range sharding strategy in sharding rule.
 */
public class CustomRangeShardingAlgorithm implements RangeShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Long> rangeShardingValue) {
        Set<String> result = new HashSet<>();

        Long lower = rangeShardingValue.getValueRange().lowerEndpoint();
        Long upper = rangeShardingValue.getValueRange().upperEndpoint();
        // 对product_order id进行处理
        for (Long i = lower; i <= upper; i++) {
            for (String datasource : collection) {
                if (datasource.endsWith(String.valueOf(i % collection.size()))) {
                     result.add(datasource);
                }
            }
        }
        return result;
    }
}
