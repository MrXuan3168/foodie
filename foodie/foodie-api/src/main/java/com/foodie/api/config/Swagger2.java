package com.foodie.api.config;

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

/**
 * 应用模块名称：Swagger2配置类
 * 
 * @author jamie
 * @since 2019/11/25 13:48
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    /**
     * 配置 swagger2 核心配置 docket </br>
     * http://localhost:8088/swagger-ui.html </br>
     * http://localhost:8088/doc.html </br>
     * 
     * @author jamie
     * @date 2019/11/25 13:59
     */
    @Bean
    public Docket createRestApi() {
        // 指定API类型为 swagger2
        return new Docket(DocumentationType.SWAGGER_2)
            // 去除默认的状态码
            .useDefaultResponseMessages(false)
            // 定义API文档汇总信息
            .apiInfo(apiInfo())
            // 设置扫描包(controller)的信息
            .select().apis(RequestHandlerSelectors.basePackage("com.foodie.api.controller"))
            // 所有controller
            .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            // 文档页标题
            .title("jamie 吃货 电商平台接口api")
            // 联系人信息
            .contact(new Contact("jamie", "https://foodie.xuanweiyao.com", "1004108488@qq.com"))
            // 详细信息
            .description("jamie 吃货 电商平台接口api文档")
            // 文档版本号
            .version("1.1.0")
            // 网站信息
            .termsOfServiceUrl("https://foodie.xuanweiyao.com").build();

    }
}
