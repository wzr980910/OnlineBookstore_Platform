package com.platform.config;

import com.platform.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with Intellij IDEA.
 *
 * @Author: wzr
 * @Date: 2024/01/12/19:39
 * @Description:登录拦截器
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

//    @Autowired
//    private AuthenticationInterceptor authenticationInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //注册AuthenticationInterceptor拦截器
//        InterceptorRegistration authRegistration=registry.addInterceptor(new AuthenticationInterceptor());
//        authRegistration.addPathPatterns("/**");    //所有路径都被拦截
//        authRegistration.excludePathPatterns(       //添加不拦截路径
//                "/user/login",
//                "/**/*.html",
//                "/**/*.js",
//                "/**/*.css",
//                "/**/*.jpg",
//                "/swagger-ui.html",
//                "/swagger-resources/**",
//                "/webjars/**",
//                "/alipay/**"
//        );
//    }
}
