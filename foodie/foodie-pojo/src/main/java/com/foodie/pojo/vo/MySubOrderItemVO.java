package com.foodie.pojo.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户中心，我的订单列表嵌套商品VO
 * @author jamie
 */
@Getter
@Setter
@ToString
public class MySubOrderItemVO {

    private String itemId;

    private String itemImg;

    private String itemName;

    private String itemSpecName;

    private Integer buyCounts;

    private Integer price;

}