package com.foodie.api.controller;

import com.foodie.common.utils.RedisUtils;
import com.foodie.pojo.Orders;
import com.foodie.pojo.Users;
import com.foodie.pojo.vo.UserVO;
import com.foodie.service.center.MyOrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 * 应用模块名称：通用controller
 * @author jamie
 * @since 2019/11/30 22:32
 */
@Controller
public class BaseController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private MyOrdersService myOrdersService;

    public static final String FOODIE_SHOP_CART = "shopcart";

    public static final Integer COMMON_PAGE_SIZE = 10;

    public static final Integer PAGE_SIZE = 20;

    public static final Integer COMMENT_PAGE = 1;

    public static final String REDIS_USER_TOKEN = "redis_user_token";

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

    /**
     * 用于验证用户和订单是否有关联关系，避免非法用户调用
     * @return com.foodie.common.utils.R<com.foodie.pojo.Orders>
     */
    public Orders checkUserOrder(String userId, String orderId) {
        return myOrdersService.queryMyOrder(userId, orderId);
    }

    /**
     * 判断图片后缀名
     * @param suffix 后缀名，不带.
     * @return boolean
     */
    public boolean checkImgSuffix(String suffix) {
        return "png".equalsIgnoreCase(suffix) || "jpg".equalsIgnoreCase(suffix) || "jpeg".equalsIgnoreCase(suffix);
    }

    /**
     * 转换对象，并生成token插入
     * @param user 用户对象
     * @return com.foodie.pojo.vo.UserVO
     * @author jamie
     * @date 2020/9/7 22:38
     */
    protected UserVO conventUserVo(Users user) {
        UserVO userVO = UserVO.builder().build();
        BeanUtils.copyProperties(user, userVO);
        // 实现用的的redis会话
        String uniqueToken = UUID.randomUUID().toString().trim();
        redisUtils.set(REDIS_USER_TOKEN + ":" + user.getId(), uniqueToken);
        userVO.setUniqueToken(uniqueToken);
        return userVO;
    }

}
