package com.so.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 * 
 * @author show
 * @date 2019/11/25 11:30
 */
@ApiModel(value = "登录成功返回对象", description = "登录成功返回对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {

    @ApiModelProperty(value = "主键", name = "id", example = "1", required = true)
    private String id;

    @ApiModelProperty(value = "用户名", name = "username", example = "show", required = true)
    private String username;

    // @JsonIgnore
    // @ApiModelProperty(value = "密码", name = "password", example = "123456")
    // private String password;

    @ApiModelProperty(value = "昵称", name = "nickname", example = "情歌", required = true)
    private String nickname;

    // @JsonIgnore
    // @ApiModelProperty(value = "真实姓名", name = "不知道", example = "show")
    // private String realname;

    @ApiModelProperty(value = "头像", name = "face", example = "show", required = true)
    private String face;

    // @JsonIgnore
    // @ApiModelProperty(value = "手机号", name = "mobile", example = "show")
    // private String mobile;

    @JsonIgnore
    @ApiModelProperty(value = "邮箱地址", name = "email", example = "show")
    private String email;

    @ApiModelProperty(value = "性别 1:男 0:女 2:保密", name = "sex", example = "show", required = true)
    private Integer sex;

    // @JsonIgnore
    // @ApiModelProperty(value = "生日", name = "birthday", example = "show")
    // private Date birthday;

    // @JsonIgnore
    // @ApiModelProperty(value = "创建时间", name = "createdTime", example = "show")
    // private Date createdTime;

    // @JsonIgnore
    // @ApiModelProperty(value = "更新时间", name = "updatedTime", example = "show")
    // private Date updatedTime;

}