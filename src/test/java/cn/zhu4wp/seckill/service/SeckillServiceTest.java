package cn.zhu4wp.seckill.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * SeckillService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>10/09/2020</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillServiceTest {
    @Resource
    SeckillService seckillService;
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: seckill(User user, GoodsVO goodsVO)
     */
    @Test
    public void testSeckill() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: getSeckillResult(long userId, long goodsId)
     */
    @Test
    public void testGetSeckillResult() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: seckillGoodsStockNone(long goodsId)
     */
    @Test
    public void testSeckillGoodsStockNone() throws Exception {
//TODO: Test goes here...
//        System.out.println(seckillService.seckillGoodsStockNone(1));
    }


    /**
     * Method: setGoodsOver(Long goodsId)
     */
    @Test
    public void testSetGoodsOver() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SeckillService.getClass().getMethod("setGoodsOver", Long.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: getGoodsOver(long goodsId)
     */
    @Test
    public void testGetGoodsOver() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = SeckillService.getClass().getMethod("getGoodsOver", long.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
