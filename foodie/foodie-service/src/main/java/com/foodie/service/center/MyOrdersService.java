package com.foodie.service.center;

import com.foodie.common.utils.PageR;
import com.foodie.pojo.OrderStatus;
import com.foodie.pojo.Orders;
import com.foodie.pojo.vo.MyOrdersVO;
import com.foodie.pojo.vo.OrderStatusCountsVO;

/**
 * 我的订单
 * @author jamie
 * @date 2020/8/13 14:20
 */
public interface MyOrdersService {

    /**
     * 查询我的订单列表
     * @param userId      用户Id
     * @param orderStatus 订单状态
     * @param page        页码
     * @param pageSize    页数
     * @return PageR<MyOrdersVO>
     */
    PageR<MyOrdersVO> queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize);

    /**
     * 订单状态 --> 商家发货
     * @param orderId 订单Id
     */
    void updateDeliverOrderStatus(String orderId);

    /**
     * 查询我的订单
     * @param userId  用户Id
     * @param orderId 订单Id
     * @return Orders
     */
    Orders queryMyOrder(String userId, String orderId);

    /**
     * 更新订单状态 —> 确认收货
     * @param orderId 订单Id
     * @return boolean
     */
    boolean updateReceiveOrderStatus(String orderId);

    /**
     * 删除订单（逻辑删除）
     * @param userId  用户Id
     * @param orderId 订单Id
     * @return boolean
     */
    boolean deleteOrder(String userId, String orderId);

    /**
     * 查询用户订单数
     * @param userId 用户Id
     * @return OrderStatusCountsVO
     */
    OrderStatusCountsVO getOrderStatusCounts(String userId);

    /**
     * 获得分页的订单动向
     * @param userId   用户Id
     * @param page     页码
     * @param pageSize 页数
     * @return PageR<OrderStatus>
     */
    PageR<OrderStatus> getOrdersTrend(String userId, Integer page, Integer pageSize);

}