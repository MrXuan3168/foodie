package com.foodie.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodie.common.utils.RedisUtils;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 应用模块名称：测试类</br>
 * //@ApiIgnore 忽略该接口,不显示在swagger界面
 *
 * @author jamie
 * @since 2019/11/12 16:13
 */
@ApiIgnore
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/set")
    public String set(String key, String value) {
        redisUtils.set(key, value);
        return "ok";
    }

    @GetMapping("/get")
    public String get(String key) {
        return redisUtils.get(key);
    }

    @GetMapping("/delete")
    public String delete(String key) {
        redisUtils.del(key);
        return "ok";
    }

}
