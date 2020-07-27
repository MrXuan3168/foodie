package com.foodie.api.controller;

import com.foodie.pojo.bo.SubmitOrderBO;
import com.foodie.service.OrderService;
import com.foodie.pojo.vo.MerchantOrdersVO;
import com.foodie.pojo.vo.OrderVO;
import com.foodie.common.enums.OrderStatusEnum;
import com.foodie.common.enums.PayMethod;
import com.foodie.common.utils.CookieUtils;
import com.foodie.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 应用模块名称：订单</br>
 *
 * @author jamie
 * @since 2019/11/12 16:13
 */
@RestController
@Slf4j
@CrossOrigin
@Api(value = "订单相关接口Controller", tags = {"订单相关接口相关的api"})
@RequestMapping("orders")
public class OrdersController extends BaseController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public R<String> create(@RequestBody SubmitOrderBO bo) {
        Integer payMethod = bo.getPayMethod();
        if (!PayMethod.WE_CHAT.type.equals(payMethod) && !PayMethod.ALI_PAY.type.equals(payMethod)) {
            return R.errorMsg("支付方式不支持");
        }
        // 1.创建订单
        OrderVO order = orderService.createOrder(bo);
        String orderId = order.getOrderId();
        // 2.创建订单以后，移除购物车中已结算(已提交)的商品
        // TODO: 2019/12/8 整合redis之后， 完善购物车中的已结算商品清除，并且同步到前端的cookie
        CookieUtils.setCookie(request, response, FOODIE_SHOP_CART, "", true);
        // 3.向支付中心发送当前订单，用于保存支付中心的订单数据
        MerchantOrdersVO merchantOrdersVO = order.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(payReturnUrl);

        return R.ok(orderId);
    }

    /**
     * 订单回调地址
     * 
     * @author xuanweiyao
     * @date 2019/12/8 20:54
     * @param merchantOrderId
     * @return java.lang.Integer
     */
    @PostMapping("notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId) {
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }
}
