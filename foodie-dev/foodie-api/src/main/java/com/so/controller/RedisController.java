package com.so.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 应用模块名称：测试类</br>
 * //@ApiIgnore 忽略该接口,不显示在swagger界面
 *
 * @author show
 * @since 2019/11/12 16:13
 */
@ApiIgnore
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/set")
    public String set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
        return "ok";
    }

    @GetMapping("/get")
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/delete")
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

}
