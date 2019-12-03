package com.so.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.so.bo.SubmitOrderBO;
import com.so.enums.PayMethod;
import com.so.service.OrderService;
import com.so.utils.Rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 应用模块名称：订单</br>
 *
 * @author show
 * @since 2019/11/12 16:13
 */
@RestController
@Slf4j
@CrossOrigin
@Api(value = "订单相关接口Controller", tags = {"订单相关接口相关的api"})
@RequestMapping("orders")
public class OrdersController {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public Rest create(@RequestBody SubmitOrderBO bo) {
        Integer payMethod = bo.getPayMethod();
        if (!PayMethod.WE_CHAT.type.equals(payMethod) && !PayMethod.ALI_PAY.type.equals(payMethod)) {
            return Rest.errorMsg("支付方式不支持");
        }
        // 1.创建订单
        orderService.createOrder(bo);
        // 2.创建订单以后，移除购物车中已结算(已提交)的商品
        // 3.向支付中心发送当前订单，用于保存支付中心的订单数据
        return Rest.ok();
    }

}
