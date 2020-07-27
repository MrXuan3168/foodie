package com.foodie.pojo.bo;

import lombok.Data;

/**
 * 应用模块名称：购物车对象
 *
 * @author jamie
 * @since 2019/12/1 15:18
 */
@Data
public class ShopCartBO {
    private String itemId;
    private String itemImgUrl;
    private String itemName;
    private String specId;
    private String specName;
    private Integer buyCounts;
    private String priceDiscount;
    private String priceNormal;

}
