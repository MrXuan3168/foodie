package com.foodie.api.controller;

import com.foodie.pojo.bo.ShopCartBO;
import com.foodie.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jamie
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
    public R<Object> add(@RequestParam String userId, @RequestBody ShopCartBO shopCartBo) {
        if (StringUtils.isBlank(userId)) {
            return R.errorMsg("用户ID不能为空");
        }
        // TODO: 2019/12/1 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存
        return R.ok();
    }

    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public R<Object> del(@RequestParam String userId, @RequestParam String itemSpecId) {
        if (StringUtils.isBlank(userId)) {
            return R.errorMsg("用户ID不能为空");
        }
        if (StringUtils.isBlank(itemSpecId)) {
            return R.errorMsg("规格ID不能为空");
        }
        // TODO: 2019/12/1 用户在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步删除后端购物车中的商品
        return R.ok();
    }
}
