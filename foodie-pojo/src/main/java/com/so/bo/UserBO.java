package com.so.bo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 应用模块名称：用户前端对象
 *
 * @author show
 * @since 2019/11/25 12:25
 */
@ApiModel(value = "用户对象BO", description = "从客户端，由用户传入的数据封装在此entity中")
@Data
public class UserBO {
    @ApiModelProperty(value = "用户名", name = "username", example = "show", required = true)
    @NotBlank(message = "用户名 username 不能为空")
    private String username;

    @ApiModelProperty(value = "密码", name = "password", example = "show", required = true)
    @NotBlank(message = "密码 password 不能为空")
    @Size(min = 6, message = "密码 password 长度不能少于6位")
    private String password;

    @ApiModelProperty(value = "确认密码", name = "confirmPassword", example = "show", required = true)
    @NotBlank(message = "确认密码 confirmPassword 不能为空")
    @Size(min = 6, message = "密码 confirmPassword 长度不能少于6位")
    private String confirmPassword;

}
