package com.so.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * 应用模块名称：最新商品
 * 
 * @author show
 * @since 2019/11/28 23:05
 */
@Data
public class NewItemsVO {
    @ApiModelProperty(value = "一级分类id", name = "rootCatId", example = "show", required = true)
    private Integer rootCatId;
    @ApiModelProperty(value = "一级分类名称", name = "rootCatName", example = "show", required = true)
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;

    private List<SimpleItemVO> simpleItemList;

}
