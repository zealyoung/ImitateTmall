/**
 * @Author: ZealYoung
 * @Time: 2020/2/8 8:59 下午
 * @Description:
 */
package com.zeal.tmall.config;

import com.zeal.tmall.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(LoginInterceptor()).addPathPatterns("/**")
                                                   .excludePathPatterns("/js/**","/img/**","/css/**","/webapp/**");
    }

    @Bean
    public LoginInterceptor LoginInterceptor() {
        return new LoginInterceptor();
    }

}
