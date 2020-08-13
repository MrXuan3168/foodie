package com.foodie.service.center;

import com.foodie.common.utils.PageR;
import com.foodie.pojo.OrderItems;
import com.foodie.pojo.bo.center.OrderItemsCommentBO;
import com.foodie.pojo.vo.MyCommentVO;

import java.util.List;

public interface MyCommentsService {

    /**
     * 根据订单id查询关联的商品
     * @param orderId 订单Id
     * @return java.util.List<com.foodie.pojo.OrderItems>
     */
    List<OrderItems> queryPendingComment(String orderId);

    /**
     * 保存用户的评论
     * @param orderId     订单Id
     * @param userId      用户Id
     * @param commentList 评论集合
     */
    void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList);

    /**
     * 我的评价查询 分页
     * @param userId   用户Id
     * @param page
     * @param pageSize
     * @return
     */
    PageR<MyCommentVO> queryMyComments(String userId, Integer page, Integer pageSize);

}
