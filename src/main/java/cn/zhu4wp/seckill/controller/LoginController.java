package cn.zhu4wp.seckill.controller;

import cn.zhu4wp.seckill.base.result.Result;
import cn.zhu4wp.seckill.service.UserService;
import cn.zhu4wp.seckill.vo.LoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    @Resource
    UserService userService;
    @Resource
    ThymeleafViewResolver thymeleafViewResolver;

    @GetMapping("/v1/visitor/login")
    public String toLogin(HttpServletRequest request, HttpServletResponse response, Model model){
        String html;
        WebContext ctx = new WebContext(request,response,request.getServletContext(),request.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("login",ctx);
        return html;
    }

    @PostMapping("/v1/visitor/login")
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVO loginVO){
        log.info(loginVO.toString());
        String token = userService.login(response,loginVO);
        return Result.success(token);
    }
}
