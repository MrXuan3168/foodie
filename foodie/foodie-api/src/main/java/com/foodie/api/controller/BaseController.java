package com.foodie.api.controller;

import org.springframework.stereotype.Controller;

/**
 * 应用模块名称：通用controller
 * @author jamie
 * @since 2019/11/30 22:32
 */
@Controller
public class BaseController {

    public static final String FOODIE_SHOP_CART = "shopcart";

    public static final Integer COMMENT_PAGE_SIZE = 10;

    public static final Integer PAGE_SIZE = 20;

    public static final Integer COMMENT_PAGE = 1;

    /**
     * 支付中心的调用地址
     * produce
     */
    String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

    /**
     * 微信支付成功 -> 支付中心 -> 天天吃货平台
     * ↓回调通知的url
     */
    String payReturnUrl = "http://bwpe6u.natappfree.cc" + "/orders/notifyMerchantOrderPaid";

}
