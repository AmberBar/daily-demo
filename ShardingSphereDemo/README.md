
## 广播表
```sql
CREATE TABLE `ad_config` (
  `id` bigint unsigned NOT NULL COMMENT '主键id',
  `config_key` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置key',
  `config_value` varchar(1024) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '配置value',
  `type` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
```

```yaml
#配置广播表
spring.shardingsphere.sharding.broadcast-tables=ad_config
spring.shardingsphere.sharding.tables.ad_config.key-generator.column=id
spring.shardingsphere.sharding.tables.ad_config.key-generator.type=SNOWFLAKE
```

## 绑定表
product_order表和product_order_item表，均按照order_id分片
```yaml

```