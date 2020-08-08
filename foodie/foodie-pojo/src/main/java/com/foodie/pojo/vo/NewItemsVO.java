package com.foodie.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 应用模块名称：最新商品
 * @author jamie
 * @since 2019/11/28 23:05
 */
@Data
@ApiModel(value = "最新商品VO", description = "从数据库返回的对象")
public class NewItemsVO {

    @ApiModelProperty(name = "rootCatId", value = "一级分类id", example = "1", required = true)
    private Integer rootCatId;

    @ApiModelProperty(name = "rootCatName", value = "一级分类名称", example = "甜糕点/蛋", required = true)
    private String rootCatName;

    @ApiModelProperty(name = "slogan", value = "口号", example = "每一道甜品都能打开你的味蕾", required = true)
    private String slogan;

    @ApiModelProperty(name = "catImage", value = "一级分类图片", example = "http://122.152.205.72:88/foodie/category/cake"
            + ".png", required = true)
    private String catImage;

    @ApiModelProperty(name = "bgColor", value = "背景颜色", example = "#fe7a65", required = true)
    private String bgColor;

    @ApiModelProperty(name = "simpleItemList", value = "最新商品集合")
    private List<SimpleItemVO> simpleItemList;

}
