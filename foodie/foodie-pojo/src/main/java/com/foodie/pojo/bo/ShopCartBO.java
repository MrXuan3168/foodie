package com.foodie.pojo.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 应用模块名称：购物车对象
 * @author jamie
 * @since 2019/12/1 15:18
 */
@Data
public class ShopCartBO {

    @ApiModelProperty(name = "itemId", value = "商品Id", required = true, example = "cake-1001")
    private String itemId;

    private String itemImgUrl;

    @ApiModelProperty(name = "itemName", value = "商品名称", required = true, example = "手撕面包")
    private String itemName;

    @ApiModelProperty(name = "specId", value = "商品规范id", required = true)
    private String specId;

    @ApiModelProperty(name = "specName", value = "商品规范名称", required = true)
    private String specName;

    @ApiModelProperty(name = "buyCounts", value = "购买数量", required = true)
    private Integer buyCounts;

    @ApiModelProperty(name = "priceDiscount", value = "优惠价", required = true)
    private String priceDiscount;

    private String priceNormal;

}
