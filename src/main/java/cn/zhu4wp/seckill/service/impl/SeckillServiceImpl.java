package cn.zhu4wp.seckill.service.impl;

import cn.zhu4wp.seckill.bean.OrderInfo;
import cn.zhu4wp.seckill.bean.User;
import cn.zhu4wp.seckill.service.GoodsService;
import cn.zhu4wp.seckill.service.OrderService;
import cn.zhu4wp.seckill.service.SeckillService;
import cn.zhu4wp.seckill.vo.GoodsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author zjm
 * @Date 2020/5/25
 * @Description TODO
 * @Version 1.0
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    @Resource
    GoodsService goodsService;
    @Resource
    OrderService orderService;

    @Transactional
    @Override
    public OrderInfo seckill(User user, GoodsVO goodsVO) {
        goodsService.reduceStock(goodsVO);
        return orderService.createOrder(user,goodsVO);
    }
}
