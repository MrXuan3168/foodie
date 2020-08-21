package com.foodie.service;

import com.foodie.pojo.OrderStatus;
import com.foodie.pojo.bo.ShopCartBO;
import com.foodie.pojo.bo.SubmitOrderBO;
import com.foodie.pojo.vo.OrderVO;

import java.util.List;

/**
 * 应用模块名称：订单业务层
 * @author jamie
 * @since 2019/11/27 12:39
 */
public interface OrderService {

    /**
     * 用于创建订单
     * @param bo             订单对象
     * @param shopCartList 购物车商品
     * @return com.foodie.pojo.vo.OrderVO
     */
    OrderVO createOrder(List<ShopCartBO> shopCartList, SubmitOrderBO bo);

    /**
     * 修改订单状态
     * @param orderId     订单Id
     * @param orderStatus 订单状态
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 查询工单状态
     * @param orderId 订单Id
     * @return com.foodie.pojo.OrderStatus
     */
    OrderStatus queryOrderStatusInfo(String orderId);
    /**
     * 关闭超时未支付订单
     */
    void closeOrder();

}
