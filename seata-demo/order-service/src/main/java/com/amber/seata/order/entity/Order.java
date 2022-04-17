package com.amber.seata.order.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The type Order.
 */
@Entity
@Table(name = "order_tbl")
@DynamicUpdate
@DynamicInsert
@Data
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "commodity_code")
    private String commodityCode;

    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "count")
    private Integer count;
}
