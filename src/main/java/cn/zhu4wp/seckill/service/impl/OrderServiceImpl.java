package cn.zhu4wp.seckill.service.impl;

import cn.zhu4wp.seckill.bean.OrderInfo;
import cn.zhu4wp.seckill.bean.SeckillOrder;
import cn.zhu4wp.seckill.bean.User;
import cn.zhu4wp.seckill.mapper.OrderMapper;
import cn.zhu4wp.seckill.service.OrderService;
import cn.zhu4wp.seckill.vo.GoodsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author zjm
 * @Date 2020/5/25
 * @Description TODO
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderMapper orderMapper;
    @Override
    public SeckillOrder selectByUserIdAndGoodsId(long id, long goodsId) {
        return orderMapper.selectByUserIdAndGoodsId(id,goodsId);
    }

    @Transactional
    @Override
    public OrderInfo createOrder(User user, GoodsVO goodsVO) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsId(goodsVO.getId());
        orderInfo.setGoodsName(goodsVO.getGoodsName());
        orderInfo.setGoodsPrice(goodsVO.getGoodsPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        long orderId = orderMapper.add(orderInfo);
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setGoodsId(goodsVO.getId());
        seckillOrder.setOrderId(orderId);
        seckillOrder.setUserId(user.getId());
        orderMapper.addSeckillOrder(seckillOrder);
        return orderInfo;


    }
}
