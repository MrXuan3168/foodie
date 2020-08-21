package com.foodie.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.foodie.common.enums.OrderStatusEnum;
import com.foodie.common.enums.PayMethod;
import com.foodie.common.utils.CookieUtils;
import com.foodie.common.utils.JacksonUtils;
import com.foodie.common.utils.R;
import com.foodie.common.utils.RedisUtils;
import com.foodie.pojo.OrderStatus;
import com.foodie.pojo.bo.ShopCartBO;
import com.foodie.pojo.bo.SubmitOrderBO;
import com.foodie.pojo.vo.MerchantOrdersVO;
import com.foodie.pojo.vo.OrderVO;
import com.foodie.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 应用模块名称：订单
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
    private RestTemplate restTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public R<String> create(@RequestBody SubmitOrderBO bo, HttpServletRequest request, HttpServletResponse response) {
        Integer payMethod = bo.getPayMethod();
        if(!PayMethod.WE_CHAT.type.equals(payMethod) && !PayMethod.ALI_PAY.type.equals(payMethod)){
            return R.errorMsg("支付方式不支持");
        }
        String shopCartJson = redisUtils.get(FOODIE_SHOP_CART + ":" + bo.getUserId());
        if(StringUtils.isBlank(shopCartJson)){
            return R.errorMsg("购物数据不正确");
        }

        List<ShopCartBO> shopCartList = JacksonUtils.jsonToList(shopCartJson, ShopCartBO.class);
        // 1.创建订单
        OrderVO order = orderService.createOrder(shopCartList, bo);
        String orderId = order.getOrderId();
        // 2.创建订单以后，移除购物车中已结算(已提交)的商品
        // 清理覆盖现有的redis汇总的购物数据
        shopCartList.removeAll(order.getToBeRemovedShopCartList());
        String shopCartsJson = JacksonUtils.objectToJson(shopCartList);
        redisUtils.set(FOODIE_SHOP_CART + ":" + bo.getUserId(), shopCartsJson);
        CookieUtils.setCookie(request, response, FOODIE_SHOP_CART, shopCartsJson, true);
        // 3.向支付中心发送当前订单，用于保存支付中心的订单数据
        MerchantOrdersVO merchantOrdersVO = order.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(payReturnUrl);

        // 为了方便测试购买，所以所有的支付金额都统一改为1分钱
        merchantOrdersVO.setAmount(1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("imoocUserId", "5450049-1004108488");
        headers.add("password", "4t43-09oi-rejo-i309");
        HttpEntity<MerchantOrdersVO> entity = new HttpEntity<>(merchantOrdersVO, headers);

        ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(paymentUrl, entity, JSONObject.class);
        JSONObject paymentResult = responseEntity.getBody();
        assert paymentResult != null;
        if(Integer.parseInt(paymentResult.get("status").toString()) != 200){
            log.error("发送错误：{}", paymentResult.get("msg").toString());
            return R.errorMsg("支付中心订单创建失败，请联系管理员！");
        }
        return R.ok(orderId);

    }

    @ApiOperation(value = "通知商户订单已付款", notes = "通知商户订单已付款", httpMethod = "POST")
    @PostMapping("/notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId) {
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }

    @ApiOperation(value = "通知商户订单已付款", notes = "通知商户订单已付款", httpMethod = "POST")
    @PostMapping("getPaidOrderInfo")
    public R<OrderStatus> getPaidOrderInfo(String orderId) {
        OrderStatus orderStatus = orderService.queryOrderStatusInfo(orderId);
        return R.ok(orderStatus);
    }

}
