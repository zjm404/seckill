package cn.zhu4wp.seckill.controller;

import cn.zhu4wp.seckill.base.result.Result;
import cn.zhu4wp.seckill.entity.User;
import cn.zhu4wp.seckill.redis.GoodsKey;
import cn.zhu4wp.seckill.redis.RedisService;
import cn.zhu4wp.seckill.service.GoodsService;
import cn.zhu4wp.seckill.service.UserService;
import cn.zhu4wp.seckill.util.CookieUtil;
import cn.zhu4wp.seckill.vo.GoodsDetailVO;
import cn.zhu4wp.seckill.vo.GoodsVO;
import org.springframework.context.ApplicationContext;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
     * 获取商品列表
     * 先查询缓存中是否存有静态资源，如果存在则返回
     * 如果不存在，则查询数据库获取动态信息后，通过解析装入静态界面，存入缓存，之后再返回给请求
     * @param request
     * @param response
     * @param model
     * @param user
     * @return
     */
    @GetMapping(value = "/v2/visitor/goods",produces = "text/html")
    public String listV2(HttpServletRequest request, HttpServletResponse response, Model model, User user){
        //先查询缓存
        String html = redisService.get(GoodsKey.getGoodsList(),"",String.class);
        if(!StringUtils.isEmpty(html)){
            return html;
        }
        //不存在则查询数据库
        List<GoodsVO> goodsVOList = goodsService.listGoodsVO();
        //装配动态信息
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsVOList);
        //装入goods_list.html界面，解析为静态资源
        WebContext ctx = new WebContext(request,response,request.getServletContext(),response.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list",ctx);
        //存入缓存
        if(!StringUtils.isEmpty(html)){
            redisService.set(GoodsKey.getGoodsList(),"",html);
        }
        return html;
    }

    /**
     * 获取商品详细信息
     * @param request
     * @param response
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @GetMapping(value = "/v2/visitor/goods/detail/{goodsId}",produces = "application/json")
    public Result<GoodsDetailVO> detail(HttpServletRequest request, HttpServletResponse response, Model model, User user, @PathVariable("goodsId") long goodsId){
        if(user == null){
            CookieUtil.get(request, CookieUtil.COOKIE_NAME_USER);
        }
        GoodsVO goodsVO = goodsService.getGoodsVOByGoodsId(goodsId);
        model.addAttribute("goods",goodsVO);
        long startTime = goodsVO.getStartDate().getTime();
        long endTime = goodsVO.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int seckillStatus = 0;
        int remainSeconds = 0;

        if(now < startTime){
            seckillStatus = 0;
            remainSeconds = (int) ((startTime-now) / 1000);
        }else if(now > endTime){
            seckillStatus = 2;
            remainSeconds = -1;
        }else{
            seckillStatus = 1;
            remainSeconds = 0;
        }
        GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
        goodsDetailVO.setGoods(goodsVO);
        goodsDetailVO.setUser(user);
        goodsDetailVO.setRemainSeconds(remainSeconds);
        goodsDetailVO.setSeckillStatus(seckillStatus);
        return Result.success(goodsDetailVO);
    }

}
