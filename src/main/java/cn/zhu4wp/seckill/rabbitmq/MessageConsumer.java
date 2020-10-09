package cn.zhu4wp.seckill.rabbitmq;

import cn.zhu4wp.seckill.entity.SeckillOrder;
import cn.zhu4wp.seckill.entity.User;
import cn.zhu4wp.seckill.redis.RedisService;
import cn.zhu4wp.seckill.service.GoodsService;
import cn.zhu4wp.seckill.service.OrderService;
import cn.zhu4wp.seckill.service.SeckillService;
import cn.zhu4wp.seckill.vo.GoodsVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ZJM
 */
@Service
public class MessageConsumer {
    private static Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    @Resource
    RedisService redisService;
    @Resource
    GoodsService goodsService;
    @Resource
    OrderService orderService;
    @Resource
    SeckillService seckillService;

    /**
     * 消费秒杀信息，处理秒杀
     * @param message
     */
    @RabbitListener(queues = ExchangeConfig.DIRECT_QUEUE_SECKILL)
    public void consumeSeckillMessage(String message){
        log.info("receive message:" + message);
        //去除字符串两端的空格再进行判断
        if(!StringUtils.isNotBlank(message)){
            log.info("message is blank");
            return;
        }
        SeckillMessage sm = RedisService.stringToBean(message,SeckillMessage.class);
        User user = sm.getUser();
        long goodsId = sm.getGoodsId();

        GoodsVO goodsVO = goodsService.getGoodsVOByGoodsId(goodsId);
        int stock = goodsVO.getStockCount();
        if(stock <= 0){
            return ;
        }
        //判断重复秒杀
        SeckillOrder order = orderService.getOrderByUserIdAndGoodsId(user.getId(),goodsId);
        if(order != null){
            return ;
        }
        //减少库存，下订单，写入秒杀订单
        seckillService.seckill(user,goodsVO);
    }
}
