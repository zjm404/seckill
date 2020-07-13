package cn.zhu4wp.seckill.vo;

import cn.zhu4wp.seckill.bean.User;
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
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDetailVO {
    private int seckillStatus;
    private int remainSeconds;
    private GoodsVO goods ;
    private User user;
}
