package com.so.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.so.bo.ShopCartBO;
import com.so.utils.Rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author show
 * @since 2019/11/12 16:13
 */
@RestController
@Slf4j
@CrossOrigin
@Api(value = "购物车接口Controller", tags = {"购物车接口相关的api"})
@RequestMapping("shopcart")
public class ShopCartController {
    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public Rest add(@RequestParam String userId, @RequestBody ShopCartBO shopCartBo) {
        if (StringUtils.isBlank(userId)) {
            return Rest.errorMsg("用户ID不能为空");
        }
        // TODO: 2019/12/1 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存
        return Rest.ok();
    }
}
