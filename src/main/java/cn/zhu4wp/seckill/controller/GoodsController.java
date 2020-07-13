package cn.zhu4wp.seckill.controller;

import cn.zhu4wp.seckill.base.exception.GlobalException;
import cn.zhu4wp.seckill.base.result.CodeMsg;
import cn.zhu4wp.seckill.bean.User;
import cn.zhu4wp.seckill.redis.RedisService;
import cn.zhu4wp.seckill.service.GoodsService;
import cn.zhu4wp.seckill.service.UserService;
import cn.zhu4wp.seckill.vo.GoodsVO;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author zjm
 * @Date 2020/5/25
 * @Description TODO
 * @Version 1.0
 */
@RequestMapping("/api")
@RestController
public class GoodsController {
    @Resource
    UserService userService;
    @Resource
    RedisService redisService;
    @Resource
    GoodsService goodsService;
    @Resource
    ApplicationContext applicationContext;
    @Resource
    ThymeleafViewResolver thymeleafViewResolver;

    /**
     * 不做缓存
     * @param request
     * @param response
     * @param model
     * @param user
     * @return
     */
    @GetMapping(value = "/v1/visitor/goods",produces = "text/html")
    public String listV1(HttpServletRequest request,HttpServletResponse response,Model model,User user){
        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsVOList);
        //手动渲染
        WebContext ctx = new WebContext(request,response,request.getServletContext(),response.getLocale(),model.asMap());
        return thymeleafViewResolver.getTemplateEngine().process("goods_list",ctx);
    }
    @GetMapping(value = "/v2/list",produces = "text/html")
    public String listV2(HttpServletRequest request,HttpServletResponse response,Model model,User user){
        String html;
        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsVOList);
        //手动渲染
        WebContext ctx = new WebContext(request,response,request.getServletContext(),response.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list",ctx);
        return html;
    }

    @GetMapping(value = "/v1/visitor/goods/detail/{goodsId}",produces = "text/html")
    public String detailV1(HttpServletRequest request,HttpServletResponse response,Model model, User user, @PathVariable("goodsId")long goodsId){
        model.addAttribute("user",user);
        GoodsVO goodsVO = goodsService.getGoodsVOByGoodsId(goodsId);
        if(goodsVO == null){
            throw new GlobalException(CodeMsg.GOODS_NOT_EXIST);
        }
        model.addAttribute("goods",goodsVO);
        long startTime = goodsVO.getStartDate().getTime();
        long endTime = goodsVO.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int seckillStatus = 0;
        int remainSeconds = 0;

        if(now < startTime){
            seckillStatus = 0;
            remainSeconds = (int) ((startTime-now)/1000);
        }else if(now > endTime){
            seckillStatus = 2;
            remainSeconds = -1;
        }else {
            seckillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("seckillStatus",seckillStatus);
        model.addAttribute("remainSeconds",remainSeconds);
        WebContext ctx = new WebContext(request,response,request.getServletContext(),response.getLocale(),model.asMap());
        return thymeleafViewResolver.getTemplateEngine().process("goods_detail",ctx);
    }


}
