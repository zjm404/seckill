package cn.zhu4wp.seckill.service.impl;

import cn.zhu4wp.seckill.bean.Goods;
import cn.zhu4wp.seckill.bean.SeckillGoods;
import cn.zhu4wp.seckill.mapper.GoodsMapper;
import cn.zhu4wp.seckill.service.GoodsService;
import cn.zhu4wp.seckill.vo.GoodsVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author zjm
 * @Date 2020/5/23
 * @Description TODO
 * @Version 1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    @Override
    public Goods selectById(long id) {
        return goodsMapper.selectGoodsById(id);
    }

    @Override
    public GoodsVO getGoodsVOByGoodsId(long goodsId) {
        return null;
    }

    @Override
    public List<GoodsVO> listGoodsVO() {
        return null;
    }

    @Override
    public void reduceStock(GoodsVO goodsVO) {
        SeckillGoods sg = new SeckillGoods();
        sg.setGoodsId(goodsVO.getId());
        goodsMapper.reduceStock(sg);
    }
}
