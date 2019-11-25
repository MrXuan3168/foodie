package com.so.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

/**
 * 应用模块名称：测试类</br>
 * //@ApiIgnore 忽略该接口,不显示在swagger界面
 * 
 * @author show
 * @since 2019/11/12 16:13
 */
@ApiIgnore
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
