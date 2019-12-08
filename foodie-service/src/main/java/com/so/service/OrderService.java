package com.so.service;

import com.so.bo.SubmitOrderBO;
import com.so.vo.OrderVO;

/**
 * 应用模块名称：订单业务层
 * 
 * @author show
 * @since 2019/11/27 12:39
 */
public interface OrderService {
    /**
     * 用于创建订单
     * 
     * @author show
     * @date 2019/12/3 22:23
     * @param bo
     * @return void
     */
    OrderVO createOrder(SubmitOrderBO bo);

    /**
     * 修改订单状态
     * 
     * @author show
     * @date 2019/12/8 20:46
     * @param orderId
     * @param orderStatus
     * @return void
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

}
