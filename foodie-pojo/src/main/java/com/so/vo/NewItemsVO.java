package com.so.vo;

import java.util.List;

import lombok.Data;

/**
 * 应用模块名称：最新商品
 * 
 * @author show
 * @since 2019/11/28 23:05
 */
@Data
public class NewItemsVO {
    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;

    private List<SimpleItemVO> simpleItemList;

}
