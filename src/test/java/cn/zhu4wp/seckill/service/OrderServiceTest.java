package cn.zhu4wp.seckill.service;

import cn.zhu4wp.seckill.entity.User;
import cn.zhu4wp.seckill.mybatis.dao.OrderMapper;
import cn.zhu4wp.seckill.vo.GoodsVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * OrderService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>10/07/2020</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
    @Resource
    OrderService orderService;
    @Resource
    OrderMapper orderMapper;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getOrderByUserIdAndGoodsId(long userId, long goodsId)
     */
    @Test
    public void testGetOrderByUserIdAndGoodsId() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getOrderById(long orderId)
     */
    @Test
    public void testGetOrderById() throws Exception {
//TODO: Test goes here...
        orderService.getOrderById(1);
    }

    /**
     * Method: createOrder(User user, GoodsVO goodsVO)
     */
    @Test
    public void testCreateOrder() throws Exception {
//TODO: Test goes here...
        GoodsVO goodsVO = new GoodsVO();
        goodsVO.setId(2L);
        goodsVO.setGoodsName("iphoneX");
        goodsVO.setSeckillPrice(1.0);
        User user = new User();
        user.setId(1L);
        orderService.createOrder(user,goodsVO);
    }


} 
