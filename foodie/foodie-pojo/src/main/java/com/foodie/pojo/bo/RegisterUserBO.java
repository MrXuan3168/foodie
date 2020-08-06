package com.foodie.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 应用模块名称：用户注册对象BO
 *
 * @author jamie
 * @since 2019/11/25 12:25
 */
@ApiModel(value = "用户注册对象BO")
@Data
public class RegisterUserBO {

    @ApiModelProperty(value = "用户名", name = "username", example = "1004108488@qq.com", required = true)
    @NotBlank(message = "用户名 username 不能为空")
    private String username;

    @ApiModelProperty(value = "密码 长度大于6", name = "password", example = "123456", required = true)
    @NotBlank(message = "密码 password 不能为空")
    @Size(min = 6, message = "密码 password 长度不能少于6位")
    private String password;

    @ApiModelProperty(value = "确认密码 长度大于6", name = "confirmPassword", example = "123456", required = true)
    @NotBlank(message = "确认密码 confirmPassword 不能为空")
    @Size(min = 6, message = "密码 confirmPassword 长度不能少于6位")
    private String confirmPassword;

}
