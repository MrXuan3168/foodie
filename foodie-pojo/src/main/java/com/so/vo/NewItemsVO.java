package com.so.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 应用模块名称：最新商品
 * 
 * @author show
 * @since 2019/11/28 23:05
 */
@Data
@ApiModel(value = "最新商品VO", description = "从数据库返回的对象")
public class NewItemsVO {

    @ApiModelProperty(value = "一级分类id", name = "rootCatId", example = "1", required = true)
    private Integer rootCatId;

    @ApiModelProperty(value = "一级分类名称", name = "rootCatName", example = "甜糕点/蛋", required = true)
    private String rootCatName;

    @ApiModelProperty(value = "口号", name = "slogan", example = "每一道甜品都能打开你的味蕾", required = true)
    private String slogan;

    @ApiModelProperty(value = "一级分类图片", name = "catImage",
        example = "http://122.152.205.72:88/foodie/category/cake.png", required = true)
    private String catImage;

    @ApiModelProperty(value = "背景颜色", name = "bgColor", example = "#fe7a65", required = true)
    private String bgColor;

    @ApiModelProperty(value = "最新商品集合", name = "simpleItemList")
    private List<SimpleItemVO> simpleItemList;

}
