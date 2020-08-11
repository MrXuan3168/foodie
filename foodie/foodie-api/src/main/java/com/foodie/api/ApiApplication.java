package com.foodie.api;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 应用模块名称： api启动类
 * MapperScan 扫描mapper所在包
 * ComponentScan 扫描所有包及相关注解包
 * @author jamie
 * @since 2019/11/12 15:48
 */
@SpringBootApplication
@MapperScan(basePackages = "com.foodie.mapper")
@ComponentScan(basePackages = {"com.foodie", "org.n3r.idworker"})
@EnableKnife4j
public class ApiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    /**
     * 为了打包springboot项目
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

}
