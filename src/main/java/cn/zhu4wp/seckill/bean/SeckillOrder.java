package cn.zhu4wp.seckill.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author zjm
 * @Date 2020/5/23
 * @Description TODO
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SeckillOrder {
    private Long id;
    private Long userId;
    private Long  orderId;
    private Long goodsId;
}
