package com.foodie.pojo.vo;

import com.foodie.pojo.bo.ShopCartBO;
import lombok.Data;

import java.util.List;

/**
 * 应用模块名称：
 * @author jamie
 * @since 2019/12/8 21:12
 */
@Data
public class OrderVO {

    /** 订单ID */
    private String orderId;

    /** 商户订单数据 */
    private MerchantOrdersVO merchantOrdersVO;

    private List<ShopCartBO> toBeRemovedShopCartList;

}
