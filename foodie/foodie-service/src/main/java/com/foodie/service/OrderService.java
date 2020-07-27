package com.foodie.service;

import com.foodie.pojo.bo.SubmitOrderBO;
import com.foodie.pojo.vo.OrderVO;

/**
 * 应用模块名称：订单业务层
 * 
 * @author jamie
 * @since 2019/11/27 12:39
 */
public interface OrderService {
    /**
     * 用于创建订单
     * 
     * @author jamie
     * @date 2019/12/3 22:23
     * @param bo
     * @return void
     */
    OrderVO createOrder(SubmitOrderBO bo);

    /**
     * 修改订单状态
     * 
     * @author jamie
     * @date 2019/12/8 20:46
     * @param orderId
     * @param orderStatus
     * @return void
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

}
