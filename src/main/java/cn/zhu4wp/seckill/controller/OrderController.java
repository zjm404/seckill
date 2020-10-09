package cn.zhu4wp.seckill.controller;

import cn.zhu4wp.seckill.base.result.CodeMsg;
import cn.zhu4wp.seckill.base.result.Result;
import cn.zhu4wp.seckill.entity.OrderInfo;
import cn.zhu4wp.seckill.entity.User;
import cn.zhu4wp.seckill.redis.RedisService;
import cn.zhu4wp.seckill.service.GoodsService;
import cn.zhu4wp.seckill.service.OrderService;
import cn.zhu4wp.seckill.service.UserService;
import cn.zhu4wp.seckill.vo.GoodsVO;
import cn.zhu4wp.seckill.vo.OrderDetailVO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Resource
    UserService userService;
    @Resource
    RedisService redisService;
    @Resource
    OrderService orderService;
    @Resource
    GoodsService goodsService;

    @GetMapping("/v1/user/order/detail")
    public Result<OrderDetailVO> info(Model model, User user, @RequestParam("orderId") long orderId){
        if(user == null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo orderInfo = orderService.getOrderById(orderId);
        if(orderInfo == null){
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        long goodsId = orderInfo.getGoodsId();
        GoodsVO goodsVO = goodsService.getGoodsVOByGoodsId(goodsId);
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setGoods(goodsVO);
        orderDetailVO.setOrderInfo(orderInfo);
        return Result.success(orderDetailVO);
    }
}
