package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    //访问地址http://localhost:8089/swagger-ui.html 原地址
    //访问地址http://localhost:8089/doc.html        换肤后的地址
    //配置swagger2的核心配置 docket
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2) //指定api类型为swagger2
                    .apiInfo(apiInfo())      //用于定义api文档汇总信息
                    .select()
                    .apis(RequestHandlerSelectors
                            .basePackage("com.imooc.controller")) //指定Controller的包名
                    .paths(PathSelectors.any()).build();          //使用所有的controller

    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("天天吃货电商API")
                .description("专为天天吃货电商提供的API文档")
                .version("1.0.1")
                .termsOfServiceUrl("http://www.imooc.com/")
                .contact(new Contact("lichenc",
                        "http://www.imooc.com",
                        "lichenc@imooc.com")
                ).build();
    }
}
