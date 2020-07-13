package cn.zhu4wp.seckill.vo;

import cn.zhu4wp.seckill.bean.OrderInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author zjm
 * @Date 2020/5/24
 * @Description TODO
 * @Version 1.0
 */
@Setter
@Getter
public class OrderDetailVO {
    private GoodsVO goods;
    private OrderInfo orderInfo;

}
