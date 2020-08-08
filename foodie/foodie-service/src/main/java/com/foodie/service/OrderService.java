package com.foodie.service;

import com.foodie.pojo.bo.SubmitOrderBO;
import com.foodie.pojo.vo.OrderVO;

/**
 * 应用模块名称：订单业务层
 * @author jamie
 * @since 2019/11/27 12:39
 */
public interface OrderService {

    /**
     * 用于创建订单
     * @param bo
     * @return void
     */
    OrderVO createOrder(SubmitOrderBO bo);

    /**
     * 修改订单状态
     * @param orderId
     * @param orderStatus
     * @return void
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

}
