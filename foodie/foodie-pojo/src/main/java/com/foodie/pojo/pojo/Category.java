package com.foodie.pojo.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分类
 * 
 * @author jamie
 * @date 2019/11/28 22:51
 */
@ApiModel(value = "分类对象", description = "从数据库返回的对象")
@Data
public class Category {
    @Id
    @ApiModelProperty(value = "主键", name = "id", example = "1", required = true)
    private Integer id;

    @ApiModelProperty(value = "分类名称", name = "name", example = "甜糕点/蛋", required = true)
    private String name;

    @ApiModelProperty(value = "分类类型： 1:一级大分类 2:二级分类 3:三级小分类", name = "type", example = "1", required = true)
    private Integer type;

    @ApiModelProperty(value = "父id 上一级依赖的id，1级分类则为0，二级三级分别依赖上一级", name = "fatherId", example = "0", required = true)
    @Column(name = "father_id")
    private Integer fatherId;

    @ApiModelProperty(value = "图标 logo", name = "logo", example = "img/cake.png", required = true)
    private String logo;

    @ApiModelProperty(value = "口号", name = "slogan", example = "每一道甜品都能打开你的味蕾", required = true)
    private String slogan;

    @ApiModelProperty(value = "分类图", name = "catImage", example = "http://122.152.205.72:88/foodie/category/cake.png",
        required = true)
    @Column(name = "cat_image")
    private String catImage;

    @ApiModelProperty(value = "背景颜色", name = "bgColor", example = "#fe7a65", required = true)
    @Column(name = "bg_color")
    private String bgColor;

}