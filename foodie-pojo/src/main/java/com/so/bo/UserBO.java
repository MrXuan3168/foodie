package com.so.bo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 应用模块名称：用户前端对象
 *
 * @author show
 * @since 2019/11/25 12:25
 */
@Data
public class UserBO {
    @NotBlank(message = "用户名 username 不能为空")
    private String username;
    @NotBlank(message = "密码 password 不能为空")
    @Size(min = 6,message = "密码 password 长度不能少于6位")
    private String password;
    @NotBlank(message = "确认密码 confirmPassword 不能为空")
    @Size(min = 6,message = "密码 confirmPassword 长度不能少于6位")
    private String confirmPassword;

}
