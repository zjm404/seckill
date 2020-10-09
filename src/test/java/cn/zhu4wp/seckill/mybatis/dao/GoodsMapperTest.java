package cn.zhu4wp.seckill.mybatis.dao;

import cn.zhu4wp.seckill.entity.SeckillGoods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsMapperTest {
    @Resource
    private GoodsMapper goodsMapper;

    @Test
    public void listGoodsVo() {
    }

    @Test
    public void getGoodsVoByGoodsId() {
    }

    @Test
    public void reduceStockByVersion() {
        SeckillGoods sg = new SeckillGoods();
        sg.setVersion(0);
        int res = goodsMapper.reduceStockByVersion(sg);
    }

    @Test
    public void getVersionByGoodsId() {
    }

    @Test
    public void selectSeckillGoodsStockCountByGoodsId(){
        System.out.println(goodsMapper.selectSeckillGoodsStockCountByGoodsId(1l));
    }
}