package cn.zhu4wp.seckill.controller;

import cn.zhu4wp.seckill.base.result.CodeMsg;
import cn.zhu4wp.seckill.base.result.Result;
import cn.zhu4wp.seckill.service.UserService;
import cn.zhu4wp.seckill.vo.LoginVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author zjm
 * @Date 2020/5/23
 * @Description TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
public class LoginController {
    @Resource
    UserService userService;
    @Resource
    ThymeleafViewResolver thymeleafViewResolver;

    @GetMapping("/v1/visitor/login")
    public String toLogin(HttpServletRequest request, HttpServletResponse response, Model model){
        String html;
        WebContext ctx = new WebContext(request,response,request.getServletContext(),response.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("login",ctx);
        return html;
    }

    @PostMapping("/v1/visitor/login")
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVO loginVO){
        String token = userService.login(response,loginVO);
        return Result.success(token);
    }
}
