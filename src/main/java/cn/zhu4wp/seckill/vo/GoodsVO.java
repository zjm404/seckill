package cn.zhu4wp.seckill.vo;

import cn.zhu4wp.seckill.entity.Goods;
import lombok.*;

import java.util.Date;

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
@ToString
public class GoodsVO extends Goods {
    private Double seckillPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    private Integer version;
}
