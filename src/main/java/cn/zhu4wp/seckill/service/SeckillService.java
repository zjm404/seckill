package cn.zhu4wp.seckill.service;

import cn.zhu4wp.seckill.entity.OrderInfo;
import cn.zhu4wp.seckill.entity.SeckillOrder;
import cn.zhu4wp.seckill.entity.User;
import cn.zhu4wp.seckill.redis.RedisService;
import cn.zhu4wp.seckill.redis.SeckillKey;
import cn.zhu4wp.seckill.vo.GoodsVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@Service
public class SeckillService {
    @Resource
    GoodsService goodsService;
    @Resource
    OrderService orderService;
    @Resource
    RedisService redisService;

    @Transactional
    public OrderInfo seckill(User user, GoodsVO goodsVO){
        boolean success = goodsService.reduceStock(goodsVO);
        if(success){
            return orderService.createOrder(user,goodsVO);
        }else{
            setGoodsOver(goodsVO.getId());
            return null;
        }
    }

    /**
     * 获取秒杀结果
     * @param userId
     * @param goodsId
     * @return
     */
    public long getSeckillResult(long userId,long goodsId){
        SeckillOrder order = orderService.getOrderByUserIdAndGoodsId(userId,goodsId);
        if(order != null){
            return order.getOrderId();
        }else{
            boolean isOver = getGoodsOver(goodsId);
            //解决 bug：用户已秒杀到商品，秒杀结果却返回秒杀失败
            //原因：避免秒杀过快，redis中已经预减库存至0，而已秒杀到的用户，其订单还未生成
            //解决方式：当 redis 中缓存库存为 0时，再查询下数据库中的缓存
            if(isOver && seckillGoodsStockNone(goodsId)){
                return -1;
            }else{
                return 0;
            }
        }
    }

    private boolean seckillGoodsStockNone(long goodsId){
        return goodsService.getSeckillStockNum(goodsId) <= 0L;
    }
    private void setGoodsOver(Long goodsId){
        redisService.set(SeckillKey.isGoodsOver(),""+goodsId,true);
    }
    private boolean getGoodsOver(long goodsId){
        return redisService.exists(SeckillKey.isGoodsOver(),""+goodsId);
    }
}
