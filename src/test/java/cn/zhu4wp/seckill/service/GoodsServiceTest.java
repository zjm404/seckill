package cn.zhu4wp.seckill.service;

import cn.zhu4wp.seckill.vo.GoodsVO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * GoodsService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/12/2020</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTest {
    @Resource(name = "goodsService")
    private GoodsService goodsService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: listGoodsVO()
     */
    @Test
    public void testListGoodsVO() throws Exception {
        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();
        goodsVOList.forEach(System.out::println);
    }

    /**
     * Method: getGoodsVOByGoodsId(long goodsId)
     */
    @Test
    public void testGetGoodsVOByGoodsId() throws Exception {
        GoodsVO goodsVO = goodsService.getGoodsVOByGoodsId(1);
        Date date = goodsVO.getStartDate();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(date));
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2020,9,5,20,30);
//        Date date = calendar.getTime();
//        System.out.println(date.getTime());
    }

    /**
     * Method: reduceStock(GoodsVO goodsVO)
     */
    @Test
    public void testReduceStock() throws Exception {
        GoodsVO goodsVO = goodsService.getGoodsVOByGoodsId(1);
        boolean res = goodsService.reduceStock(goodsVO);
        System.out.println(res);
    }


} 
