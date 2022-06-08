# 读写分离
1. 搭建主从复制mysql集群
可以参考这个文章 https://blog.csdn.net/u010797364/article/details/121762621
2. 项目引入shardingsphere依赖，并配置主从表
```yaml
spring.shardingsphere.masterslave.name=ms
spring.shardingsphere.masterslave.master-data-source-name=master
spring.shardingsphere.masterslave.slave-data-source-names[0]=salve
```

