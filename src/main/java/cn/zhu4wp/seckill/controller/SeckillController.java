package cn.zhu4wp.seckill.controller;

import cn.zhu4wp.seckill.base.result.CodeMsg;
import cn.zhu4wp.seckill.base.result.Result;
import cn.zhu4wp.seckill.entity.SeckillOrder;
import cn.zhu4wp.seckill.entity.User;
import cn.zhu4wp.seckill.interceptor.AccessLimit;
import cn.zhu4wp.seckill.rabbitmq.MessageProducer;
import cn.zhu4wp.seckill.rabbitmq.SeckillMessage;
import cn.zhu4wp.seckill.redis.GoodsKey;
import cn.zhu4wp.seckill.redis.RedisService;
import cn.zhu4wp.seckill.service.GoodsService;
import cn.zhu4wp.seckill.service.OrderService;
import cn.zhu4wp.seckill.service.SeckillService;
import cn.zhu4wp.seckill.vo.GoodsVO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @Author zjm
 * @Date 2020/5/25
 * @Description TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
public class SeckillController implements InitializingBean {
    @Resource
    GoodsService goodsService;
    @Resource
    OrderService orderService;
    @Resource
    SeckillService seckillService;
    @Resource
    RedisService redisService;
    @Resource
    MessageProducer messageProducer;

    private HashMap<Long,Boolean> localOverMap = new HashMap<Long,Boolean>();

    @PostMapping(value = "/v1/visitor/seckill",produces = "application/json")
    @AccessLimit(seconds = 5,maxCount = 6,needLogin = true)
    public Result<Integer> seckill(Model model, User user, @RequestParam("goodsId") long goodsId) throws Exception {
        //判断用户是否是登陆状态
        if(user == null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        model.addAttribute("user",user);
        //判断库存
        boolean over = localOverMap.get(goodsId);
        if(over){
            return Result.error(CodeMsg.SECKILL_OVER);
        }
        //预减库存
        long stock = redisService.decr(GoodsKey.getGoodsStock(),""+goodsId);
        if(stock < 0){
            afterPropertiesSet();
            long stock2 = redisService.decr(GoodsKey.getGoodsStock(),""+goodsId);
            if(stock2 < 0){
                localOverMap.put(goodsId,true);
                return Result.error(CodeMsg.SECKILL_OVER);
            }
        }
        //避免重复秒杀，查看是否已存在该秒杀商品的订单
        SeckillOrder order = orderService.getOrderByUserIdAndGoodsId(user.getId(),goodsId);
        if(order != null){
            return Result.error(CodeMsg.REPEAT_SECKILL);
        }
        //使用消息队列削峰
        //入队进行秒杀（减库存，生成订单）
        SeckillMessage message = new SeckillMessage();
        message.setUser(user);
        message.setGoodsId(goodsId);
        messageProducer.produceSeckillMessage(message);
        //排队中
        return Result.success(0);

    }

    /**
     * 系统初始化，将商品信息加载到redis和本地内存
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();
        if(goodsVOList == null){
            return;
        }
        for(GoodsVO goodsVO : goodsVOList){
            redisService.set(GoodsKey.getGoodsStock(),""+ goodsVO.getId(), goodsVO.getStockCount());
            localOverMap.put(goodsVO.getId(),false);
        }
    }

    /**
     * 获取秒杀结果
     * @param model
     * @param user
     * @param goodsId
     * @return -1：秒杀失败；0：排队中
     */
    @GetMapping("/v1/user/seckill/result")
    public Result<Long> seckillResult(Model model,User user,@RequestParam("goodsId") long goodsId){
        model.addAttribute("user",user);
        if(user == null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        long orderId = seckillService.getSeckillResult(user.getId(),goodsId);
        return Result.success(orderId);
    }
}
