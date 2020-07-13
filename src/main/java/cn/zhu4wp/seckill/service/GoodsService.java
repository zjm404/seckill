package cn.zhu4wp.seckill.service;

import cn.zhu4wp.seckill.bean.Goods;
import cn.zhu4wp.seckill.vo.GoodsVO;

import java.util.List;

/**
 * @Author zjm
 * @Date 2020/5/23
 * @Description TODO
 * @Version 1.0
 */

public interface GoodsService {
    public Goods selectById(long id);

    GoodsVO getGoodsVOByGoodsId(long goodsId);

    List<GoodsVO> listGoodsVO();

    void reduceStock(GoodsVO goodsVO);
}
