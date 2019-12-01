package com.so.vo;

import lombok.Data;

/**
 * 应用模块名称：购物车对象
 *
 * @author show
 * @since 2019/12/1 15:18
 */
@Data
public class ShopCartVO {
    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private String priceDiscount;
    private String priceNormal;

}
