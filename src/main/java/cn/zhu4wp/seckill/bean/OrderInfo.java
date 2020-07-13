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
public class OrderInfo {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long  deliveryAddrId;
    private String goodsName;
    private Integer goodsCount;
    private Double goodsPrice;
    private Integer orderChannel;
    private Integer status;
    private Date createDate;
    private Date payDate;
}
