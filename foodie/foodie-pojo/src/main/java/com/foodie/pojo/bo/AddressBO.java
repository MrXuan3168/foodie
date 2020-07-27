package com.foodie.pojo.bo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.foodie.common.validation.Update;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 应用模块名称： 用户新增地址的BO
 *
 * @author jamie
 * @since 2019/12/1 16:56
 */
@Data
@ApiModel(value = "新增或者修改收货地址BO", description = "调用接口发送的对象")
public class AddressBO {

    @ApiModelProperty(value = "地址ID", name = "addressId", example = "190825CG3AA14Y3C", required = true)
    @NotBlank(message = "地址ID addressId 不能为空", groups = Update.class)
    private String addressId;

    @ApiModelProperty(value = "用户ID", name = "userId", example = "1908189H7TNWDTXP", required = true)
    @NotBlank(message = "用户ID userId 不能为空")
    private String userId;

    @ApiModelProperty(value = "收件人姓名", name = "receiver", example = "show", required = true)
    @NotBlank(message = "收件人姓名 receiver 不能为空")
    @Length(max = 12, message = "收货人姓名 receiver 不能超过12位")
    private String receiver;

    @ApiModelProperty(value = "收件人手机号", name = "mobile", example = "13800138000", required = true)
    @NotBlank(message = "收件人手机号 mobile 不能为空")
    @Pattern(regexp = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$", message = "手机号码格式错误")
    private String mobile;

    @ApiModelProperty(value = "省份", name = "province", example = "广东", required = true)
    @NotBlank(message = "省份 province 不能为空")
    private String province;

    @ApiModelProperty(value = "城市", name = "city", example = "广州", required = true)
    @NotBlank(message = "城市 city 不能为空")
    private String city;

    @ApiModelProperty(value = "区域", name = "district", example = "天河区", required = true)
    @NotBlank(message = "区域 district 不能为空")
    private String district;

    @ApiModelProperty(value = "详细地址", name = "detail", example = "东圃大街1号", required = true)
    @NotBlank(message = "详细地址 detail 不能为空")
    private String detail;
}
