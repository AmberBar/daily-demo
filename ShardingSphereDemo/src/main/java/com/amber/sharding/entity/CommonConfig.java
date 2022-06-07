package com.amber.sharding.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ad_config")
public class CommonConfig {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String configKey;
    private String configValue;
    private String type;
}
