package cn.zhu4wp.seckill.service;

import cn.zhu4wp.seckill.mybatis.dao.OrderMapper;
import cn.zhu4wp.seckill.entity.OrderInfo;
import cn.zhu4wp.seckill.entity.SeckillOrder;
import cn.zhu4wp.seckill.entity.User;
import cn.zhu4wp.seckill.redis.OrderKey;
import cn.zhu4wp.seckill.redis.RedisService;
import cn.zhu4wp.seckill.vo.GoodsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Resource
    RedisService redisService;

    public SeckillOrder getOrderByUserIdAndGoodsId(long userId,long goodsId){
        return redisService.get(OrderKey.getSeckillOrderByUidAndGid(),""+userId+"_"+goodsId,SeckillOrder.class);
    }
    public OrderInfo getOrderById(long orderId){
        return orderMapper.getOrderById(orderId);
    }

    /**
     * 订单详情表和秒杀订单表同时增加一条数据
     * @param user
     * @param goodsVO
     * @return
     */
    @Transactional
    public OrderInfo createOrder(User user, GoodsVO goodsVO){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVO.getId());
        orderInfo.setGoodsName(goodsVO.getGoodsName());
        orderInfo.setGoodsPrice(goodsVO.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        orderMapper.add(orderInfo);

        long orderId = orderMapper.selectOrderInfoIdByUserIdAndGoodsId(orderInfo.getUserId(),orderInfo.getGoodsId());

        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goodsVO.getId());
        seckillOrder.setOrderId(orderId);
        seckillOrder.setUserId(user.getId());
        orderMapper.addSeckillOrder(seckillOrder);

        redisService.set(OrderKey.getSeckillOrderByUidAndGid(),""+user.getId()+"_"+ goodsVO.getId(),seckillOrder);
        return orderInfo;
    }
}
