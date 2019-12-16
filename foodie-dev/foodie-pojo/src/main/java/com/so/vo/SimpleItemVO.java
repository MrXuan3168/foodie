package com.so.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 应用模块名称：6个最新商品的简单数据类型
 * 
 * @author show
 * @since 2019/11/28 23:52
 */
@Data
@ApiModel(value = "6个最新商品的简单数据类型", description = "从数据库返回的对象")
public class SimpleItemVO {

    @ApiModelProperty(value = "最新商品ID", name = "itemId", example = "cake-1005", required = true)
    private String itemId;

    @ApiModelProperty(value = "最新商品名称", name = "itemName", example = "【天天吃货】进口美食凤梨酥", required = true)
    private String itemName;

    @ApiModelProperty(value = "最新商品图片", name = "itemUrl",
        example = "http://122.152.205.72:88/foodie/cake-1005/img1.png", required = true)
    private String itemUrl;

    @ApiModelProperty(value = "创建实名", name = "createdTime", example = "2019-09-09 14:45:34.0", required = true)
    private String createdTime;

}
