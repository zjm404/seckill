package cn.zhu4wp.seckill.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置 Exchange
 * 只承载两类需求，因此使用 direct
 */
@Configuration
public class ExchangeConfig {
    /**
     * 用于作为令牌桶，降低秒杀流量
     */
    public static final String DIRECT_QUEUE_SECKILL = "direct.queue.seckill";
    /**
     * 秒杀信息 key
     */
    public static final String ROUTING_KEY_SECKILL = "seckill";
    /**
     * 异步生成订单，快速返回秒杀结果
     */
    public static final String DIRECT_QUEUE_ORDER_CREATE = "direct.queue.order.create";
    /**
     * 创建订单信息 key
     */
    public static final String ROUTING_KEY_ORDER_CREATE = "order.create";

    public static final String DIRECT_EXCHANGE = "directExchange";
    /**
     * 传递生产者消息
     */
    public static final String PRODUCER_QUEUE = "PRODUCER_QUEUE";
    @Bean
    public Queue producerQueue(){
        return new Queue(PRODUCER_QUEUE,true);
    }

    @Bean(name = "direct.queue.seckill")
    public Queue seckillQueue(){
        return new Queue(DIRECT_QUEUE_SECKILL,true);
    }
    @Bean(name = "direct.queue.order.create")
    public Queue createOrderQueue(){
        return new Queue(DIRECT_QUEUE_ORDER_CREATE,true);
    }
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }
    @Bean
    public Binding seckillBinding(){
        return BindingBuilder.bind(seckillQueue()).to(directExchange()).with(ROUTING_KEY_SECKILL);
    }
    @Bean
    public Binding createOrderBinding(){
        return BindingBuilder.bind(createOrderQueue()).to(directExchange()).with(ROUTING_KEY_ORDER_CREATE);
    }
}
