package cn.zhu4wp.seckill.rabbitmq;

import cn.zhu4wp.seckill.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageProducer {
    private static Logger log = LoggerFactory.getLogger(MessageProducer.class);
    @Resource
    AmqpTemplate amqpTemplate;

    /**
     * 生产秒杀信息
     * @param message
     */
    public void produceSeckillMessage(SeckillMessage message){
        String msg = RedisService.beanToString(message);
        log.info("send message:" + msg);
        //发送秒杀信息
        amqpTemplate.convertAndSend(ExchangeConfig.DIRECT_EXCHANGE,ExchangeConfig.ROUTING_KEY_SECKILL,msg);
    }
}
