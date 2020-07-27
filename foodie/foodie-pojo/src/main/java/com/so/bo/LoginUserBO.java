package com.so.bo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 应用模块名称： 用户登录对象BO
 * 
 * @author show
 * @since 2019/11/25 23:11
 */
@ApiModel(value = "用户登录对象BO", description = "从客户端，由用户传入的数据封装在此entity中")
@Data
public class LoginUserBO {
    @ApiModelProperty(value = "用户名 username 不能为空", name = "username", example = "1004108488@qq.com", required = true)
    @NotBlank(message = "用户名 username 不能为空")
    private String username;

    @ApiModelProperty(value = "密码 password 长度不能少于6位", name = "password", example = "123456", required = true)
    @NotBlank(message = "密码 password 不能为空")
    @Size(min = 6, message = "密码 password 长度不能少于6位")
    private String password;
}
