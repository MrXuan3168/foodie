package com.so.vo;

import java.util.Date;

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

    /**
     * 用户名 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username", example = "show", required = true)
    private String username;

//    /**
//     * 密码 密码
//     */
//    @JsonIgnore
//    @ApiModelProperty(value = "密码", name = "password", example = "123456")
//    private String password;

    /**
     * 昵称 昵称
     */
    @ApiModelProperty(value = "昵称", name = "nickname", example = "情歌", required = true)
    private String nickname;

//    /**
//     * 真实姓名 真实姓名
//     */
//    @JsonIgnore
//    @ApiModelProperty(value = "真实姓名", name = "不知道", example = "show")
//    private String realname;

    /**
     * 头像 头像
     */
    @ApiModelProperty(value = "头像", name = "face", example = "show", required = true)
    private String face;

//    /**
//     * 手机号 手机号
//     */
//    @JsonIgnore
//    @ApiModelProperty(value = "手机号", name = "mobile", example = "show")
//    private String mobile;

    /**
     * 邮箱地址 邮箱地址
     */
    @JsonIgnore
    @ApiModelProperty(value = "邮箱地址", name = "email", example = "show")
    private String email;

    /**
     * 性别 性别 1:男 0:女 2:保密
     */
    @ApiModelProperty(value = "性别 1:男 0:女 2:保密", name = "sex", example = "show", required = true)
    private Integer sex;

//    /**
//     * 生日 生日
//     */
//    @JsonIgnore
//    @ApiModelProperty(value = "生日", name = "birthday", example = "show")
//    private Date birthday;

//    /**
//     * 创建时间 创建时间
//     */
//    @JsonIgnore
//    @ApiModelProperty(value = "创建时间", name = "createdTime", example = "show")
//    private Date createdTime;
//
//    /**
//     * 更新时间 更新时间
//     */
//    @JsonIgnore
//    @ApiModelProperty(value = "更新时间", name = "updatedTime", example = "show")
//    private Date updatedTime;

}