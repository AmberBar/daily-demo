package com.amber.seata.order.repository;

import com.amber.seata.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Description：
 *
 * @author fangliangsheng
 * @date 2019-04-04
 */
@Repository
public interface OrderDAO extends JpaRepository<Order, Long> {

}
