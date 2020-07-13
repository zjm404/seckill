package cn.zhu4wp.seckill.service;

import cn.zhu4wp.seckill.bean.OrderInfo;
import cn.zhu4wp.seckill.bean.SeckillOrder;
import cn.zhu4wp.seckill.bean.User;
import cn.zhu4wp.seckill.vo.GoodsVO;

/**
 * @Author zjm
 * @Date 2020/5/25
 * @Description TODO
 * @Version 1.0
 */

public interface OrderService {
    SeckillOrder selectByUserIdAndGoodsId(long id, long goodsId);

    OrderInfo createOrder(User user, GoodsVO goodsVO);
}
