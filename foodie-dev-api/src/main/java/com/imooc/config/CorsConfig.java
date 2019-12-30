package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    public CorsConfig(){}

    @Bean
    public CorsFilter corsFilter(){
        //1、添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");//配置前端代码的url

        //配置是否运行携带cookie信息
        config.setAllowCredentials(true);

        //配置允许请求的方式
        config.addAllowedMethod("*");

        //配置运行请求的header
        config.addAllowedHeader("*");

        //2、为url添加映射路径,/**表示前端请求适用所有的路由
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**",config);

        //3、返回值
        return new CorsFilter(corsSource);
    }
}
