package com.so.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户表
 * 
 * @author show
 * @date 2019/11/25 11:30
 */
@ApiModel(value = "数据库用户对象", description = "从数据库返回的对象")
@Data
public class Users {
    /**
     * 主键id 用户id
     */
    @Id
    @ApiModelProperty(value = "主键", name = "id", example = "show", required = true)
    private String id;

    /**
     * 用户名 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username", example = "show", required = true)
    private String username;

    /**
     * 密码 密码
     */
    @ApiModelProperty(value = "密码", name = "password", example = "show")
    private String password;

    /**
     * 昵称 昵称
     */
    @ApiModelProperty(value = "昵称", name = "nickname", example = "show", required = true)
    private String nickname;

    /**
     * 真实姓名 真实姓名
     */
    @ApiModelProperty(value = "真实姓名", name = "realname", example = "show")
    private String realname;

    /**
     * 头像 头像
     */
    @ApiModelProperty(value = "头像", name = "face", example = "show", required = true)
    private String face;

    /**
     * 手机号 手机号
     */
    @ApiModelProperty(value = "手机号", name = "mobile", example = "show")
    private String mobile;

    /**
     * 邮箱地址 邮箱地址
     */
    @ApiModelProperty(value = "邮箱地址", name = "email", example = "show")
    private String email;

    /**
     * 性别 性别 1:男 0:女 2:保密
     */
    @ApiModelProperty(value = "性别 1:男 0:女 2:保密", name = "sex", example = "show", required = true)
    private Integer sex;

    /**
     * 生日 生日
     */
    @ApiModelProperty(value = "生日", name = "birthday", example = "show")
    private Date birthday;

    /**
     * 创建时间 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createdTime", example = "show")
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间 更新时间
     */
    @ApiModelProperty(value = "更新时间", name = "updatedTime", example = "show")
    @Column(name = "updated_time")
    private Date updatedTime;

}