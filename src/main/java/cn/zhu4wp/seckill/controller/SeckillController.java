package cn.zhu4wp.seckill.controller;

import cn.zhu4wp.seckill.base.result.CodeMsg;
import cn.zhu4wp.seckill.bean.OrderInfo;
import cn.zhu4wp.seckill.bean.SeckillOrder;
import cn.zhu4wp.seckill.bean.User;
import cn.zhu4wp.seckill.redis.RedisService;
import cn.zhu4wp.seckill.service.GoodsService;
import cn.zhu4wp.seckill.service.OrderService;
import cn.zhu4wp.seckill.service.SeckillService;
import cn.zhu4wp.seckill.service.UserService;
import cn.zhu4wp.seckill.vo.GoodsVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author zjm
 * @Date 2020/5/25
 * @Description TODO
 * @Version 1.0
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {


    @Resource
    SeckillService seckillService;
    @Resource
    OrderService orderService;
    @Resource
    UserService userService;
    @Resource
    GoodsService goodsService;
    @Resource
    RedisService redisService;
    @Resource
    ThymeleafViewResolver thymeleafViewResolver;

    @GetMapping("/v1/seckill")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, User user, @RequestParam("goodsId")long goodsId){
        model.addAttribute("user",user);
        if(user == null){
            return "login";
        }
        //判断库存
        GoodsVO goodsVO = goodsService.getGoodsVOByGoodsId(goodsId);
        int stock = goodsVO.getStockCount();
        if(stock <= 0){
            model.addAttribute("errmg", CodeMsg.SECKILL_OVER.getMsg());
            return "seckill_fail";
        }
        //判断是否已经秒杀到了
        SeckillOrder seckillOrder = orderService.selectByUserIdAndGoodsId(user.getId(),goodsId);
        if(seckillOrder != null){
            model.addAttribute("errmsg",CodeMsg.REPEAT_SECKILL.getMsg());
            return "seckill_fail";
        }
        //减库存，下订单，写入秒杀订单
        OrderInfo orderInfo = seckillService.seckill(user,goodsVO);
        model.addAttribute("orderInfo",orderInfo);
        model.addAttribute("goods",goodsVO);
        return "order_detail";
    }
}
