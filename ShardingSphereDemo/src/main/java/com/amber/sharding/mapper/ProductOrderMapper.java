package com.amber.sharding.mapper;

import com.amber.sharding.entity.ProductOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ProductOrderMapper extends BaseMapper<ProductOrder> {

    @Select("select * from product_order t1 left join product_order_item t2 on t1.id = t2.product_order_id")
    public List<ProductOrder> selectWithDetail();
}
