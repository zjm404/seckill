package cn.zhu4wp.seckill.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @Author zjm
 * @Date 2020/5/23
 * @Description TODO
 * @Version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeckillGoods {
    private Long id;
    private Long goodsId;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    private int version;
}
