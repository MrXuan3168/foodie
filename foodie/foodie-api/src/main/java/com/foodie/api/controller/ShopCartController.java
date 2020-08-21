package com.foodie.api.controller;

import com.foodie.common.utils.JacksonUtils;
import com.foodie.common.utils.R;
import com.foodie.common.utils.RedisUtils;
import com.foodie.pojo.bo.ShopCartBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车接口
 * @author jamie
 * @since 2019/11/12 16:13
 */
@RestController
@Slf4j
@CrossOrigin
@Api(value = "购物车接口Controller", tags = {"购物车接口相关的api"})
@RequestMapping("shopcart")
public class ShopCartController extends BaseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public R<Object> add(@RequestParam String userId, @RequestBody ShopCartBO shopCartBo) {
        if(StringUtils.isBlank(userId)){
            return R.errorMsg("用户ID不能为空");
        }
        // 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存
        // 需要判断当前购物车中包含已经存在的商品，如果存在则累加购买数量
        String shopCartJson = redisUtils.get(FOODIE_SHOP_CART + ":" + userId);
        List<ShopCartBO> shopCartList;
        if(StringUtils.isNotBlank(shopCartJson)){
            // redis中已经有购物车了
            shopCartList = JacksonUtils.jsonToList(shopCartJson, ShopCartBO.class);
            // 判断购物车中是否存在已有商品，如果有的话counts累加
            boolean isHaving = false;
            for(ShopCartBO sc: shopCartList){
                String tmpSpecId = sc.getSpecId();
                if(tmpSpecId.equals(shopCartBo.getSpecId())){
                    sc.setBuyCounts(sc.getBuyCounts() + shopCartBo.getBuyCounts());
                    isHaving = true;
                }
            }
            if(!isHaving){
                shopCartList.add(shopCartBo);
            }
        }else{
            // redis中没有购物车
            shopCartList = new ArrayList<>();
            // 直接添加到购物车中
            shopCartList.add(shopCartBo);
        }

        // 覆盖现有redis中的购物车
        redisUtils.set(FOODIE_SHOP_CART + ":" + userId, JacksonUtils.objectToJson(shopCartList));
        return R.ok();
    }

    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("/del")
    public R<Object> del(@RequestParam String userId, @RequestParam String itemSpecId) {
        if(StringUtils.isBlank(userId)){
            return R.errorMsg("用户ID不能为空");
        }
        if(StringUtils.isBlank(itemSpecId)){
            return R.errorMsg("规格ID不能为空");
        }
        // 用户在页面删除购物车中的商品数据，如果此时用户已经登录，则需要同步删除redis购物车中的商品
        String shopCartJson = redisUtils.get(FOODIE_SHOP_CART + ":" + userId);
        if(StringUtils.isNotBlank(shopCartJson)){
            // redis中已经有购物车了
            List<ShopCartBO> shopCartList = JacksonUtils.jsonToList(shopCartJson, ShopCartBO.class);
            // 判断购物车中是否存在已有商品，如果有的话则删除
            for(ShopCartBO sc: shopCartList){
                String tmpSpecId = sc.getSpecId();
                if(tmpSpecId.equals(itemSpecId)){
                    shopCartList.remove(sc);
                    break;
                }
            }
            // 覆盖现有redis中的购物车
            redisUtils.set(FOODIE_SHOP_CART + ":" + userId, JacksonUtils.objectToJson(shopCartList));
        }
        return R.ok();
    }

}
