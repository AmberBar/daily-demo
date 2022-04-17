package com.amber.seata.storage.repository;

import com.amber.seata.storage.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Description：
 *
 * @author fangliangsheng
 * @date 2019-04-04
 */
@Repository
public interface StockDao extends JpaRepository<Stock, Long> {
    @Modifying
    @Query("update Stock s set s.count = (s.count - :count) where s.commodityCode = :commodityCode")
    void update(@Param("count") Integer count, @Param("commodityCode") String commodityCode);
}
