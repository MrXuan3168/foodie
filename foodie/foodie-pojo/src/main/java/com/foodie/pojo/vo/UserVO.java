package com.foodie.pojo.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户表
 * @author jamie
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

    @ApiModelProperty(value = "昵称", name = "nickname", example = "情歌", required = true)
    private String nickname;

    @ApiModelProperty(value = "头像", name = "face", example = "show", required = true)
    private String face;

    @JsonIgnore
    @ApiModelProperty(value = "邮箱地址", name = "email", example = "show")
    private String email;

    @ApiModelProperty(value = "性别 1:男 0:女 2:保密", name = "sex", example = "show", required = true)
    private Integer sex;

    @ApiModelProperty(value = "token", name = "uniqueToken", example = "uuid", required = true)
    private String uniqueToken;

    @ApiModelProperty(value = "用户token", name = "userUniqueToken", example = "uuid", required = true)
    private String userUniqueToken;

}