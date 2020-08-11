package com.foodie.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 应用模块名称：用于创建订单的bo对象
 * @author jamie
 * @since 2019/12/2 22:46
 */
@Data
@ApiModel(value = "用于创建订单的bo对象", description = "调用接口发送的对象")
public class SubmitOrderBO {

    @ApiModelProperty(name = "userId", value = "用户id", required = true, example = "1908189H7TNWDTXP")
    private String userId;

    @ApiModelProperty(name = "itemSpecIds", value = "拼接的规格ids", required = true, example = "1001,1002,1003")
    private String itemSpecIds;

    @ApiModelProperty(name = "addressId", value = "地址Id", required = true, example = "190825CG3AA14Y3C")
    private String addressId;

    @ApiModelProperty(name = "payMethod", value = "支付方式 1:微信 2:支付宝", required = true, example = "2")
    private Integer payMethod;

    @ApiModelProperty(name = "leftMsg", value = "买家留言")
    private String leftMsg;

}
