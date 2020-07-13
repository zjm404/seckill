package cn.zhu4wp.seckill.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author zjm
 * @Date 2020/5/25
 * @Description TODO
 * @Version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        //定义转换消息的对象
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        //添加FastJson配置信息
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        //在convert中添加配置信息
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        //将convert添加到converters中
//        converters.add(fastJsonHttpMessageConverter);
//    }

    @Autowired
    UserArgumentResolver userArgumentResolver;

    /**
     * SpringMVC框架回调addArgumentResolvers，然后给Controller的参数赋值
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);
    }
}
