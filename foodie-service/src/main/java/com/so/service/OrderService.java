package com.so.service;

import com.so.bo.SubmitOrderBO;
import org.springframework.stereotype.Service;

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
     * @author xuanweiyao
     * @date 2019/12/3 22:23
     * @param bo
     * @return void
     */
    void createOrder(SubmitOrderBO bo);

}
