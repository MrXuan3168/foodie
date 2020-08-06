package com.foodie.pojo.vo;

import lombok.Data;

/**
 * 应用模块名称：
 *
 * @author jamie
 * @since 2019/12/8 21:12
 */
@Data
public class OrderVO {

    private String orderId;

    private MerchantOrdersVO merchantOrdersVO;

}
