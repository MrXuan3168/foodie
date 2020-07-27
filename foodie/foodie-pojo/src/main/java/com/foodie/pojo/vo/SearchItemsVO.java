package com.foodie.pojo.vo;

import lombok.Data;

/**
 * 应用模块名称： 用于展示商品搜索列表的VO
 * 
 * @author show
 * @since 2019/11/30 22:05
 */
@Data
public class SearchItemsVO {
    private String itemId;
    private String itemName;
    private Integer sellCounts;
    private String imgUrl;
    /** 分为单位 */
    private Integer price;

}
