package cn.zhu4wp.seckill.service.impl;

import cn.zhu4wp.seckill.bean.Goods;
import cn.zhu4wp.seckill.service.GoodsService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * GoodsServiceImpl Tester.
 *
 * @author zjm
 * @version 1.0
 * @since <pre>5月 23, 2020</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceImplTest {
    @Autowired
    GoodsService goodsService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: selectById(long id)
     */
    @Test
    public void testSelectById() throws Exception {
//TODO: Test goes here...
        Goods goods = goodsService.selectById(1);
        System.out.println(goods);
    }


} 
