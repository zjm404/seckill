package cn.zhu4wp.seckill.rabbitmq;

import cn.zhu4wp.seckill.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeckillMessage {
    private User user;
    private long goodsId;
}
