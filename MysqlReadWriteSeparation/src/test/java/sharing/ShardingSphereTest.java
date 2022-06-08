package sharing;

import com.amber.ReadWriteSeparationApplication;
import com.amber.entify.ProductOrder;
import com.amber.mapper.ProductOrderMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)  //底层用junit  SpringJUnit4ClassRunner
@SpringBootTest(classes = ReadWriteSeparationApplication.class)
@Slf4j
public class ShardingSphereTest {

    @Autowired
    private ProductOrderMapper productOrderMapper;

    /**
     * Actual SQL: salve ::: SELECT  id,out_trade_no,state,create_time,pay_amount,nickname,user_id  FROM product_order
     *
     *  WHERE (id = ?) ::: [1534235771957608450]
     *  可以看到查询走的是从库
     */
    @Test
    public void testQuery() {
        productOrderMapper.selectList(new QueryWrapper<ProductOrder>().eq("id", 1534235771957608450L));
    }

    /**
     * Actual SQL: master ::: INSERT INTO product_order  ( id,
     * out_trade_no,
     * state,
     * create_time,
     * pay_amount,
     * nickname,
     * user_id )  VALUES  ( ?,
     * ?,
     * ?,
     * ?,
     * ?,
     * ?,
     * ? )
     */
    @Test
    public void save() {
        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setNickname("amber");
        productOrder.setOutTradeNo(UUID.randomUUID().toString().substring(0, 32));
        productOrder.setPayAmount(100.00);
        productOrder.setState("PAY");
        productOrder.setUserId(1L);
        productOrderMapper.insert(productOrder);
    }

}