package com.foodie.pojo.bo.center;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单评价对象
 * @author jamie
 * @date 2020/8/13 16:28
 */
@ApiModel(value = "订单评价对象BO", description = "调用接口发送的对象")
@Data
public class OrderItemsCommentBO {

    @ApiModelProperty(name = "commentId", value = "评论Id", example = "1908189H7TNWDTXP")
    private String commentId;

    @ApiModelProperty(name = "itemId", value = "商品Id", required = true, example = "cake-1001")
    private String itemId;

    @ApiModelProperty(name = "itemName", value = "商品名称", required = true, example = "手撕面包")
    private String itemName;

    @ApiModelProperty(name = "itemSpecId", value = "商品规格id", required = true, example = "1003")
    private String itemSpecId;

    @ApiModelProperty(name = "itemSpecName", value = "商品规格名称", required = true, example = "草莓味")
    private String itemSpecName;

    @ApiModelProperty(name = "commentLevel", value = "商品评价等级", required = true, example = "0")
    private Integer commentLevel;

    @ApiModelProperty(name = "content", value = "商品评价内容", required = true, example = "好吃")
    private String content;

}