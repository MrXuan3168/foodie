package com.so.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据库轮播图对象
 * 
 * @author show
 * @date 2019/11/27 14:15
 */
@ApiModel(value = "数据库轮播图对象", description = "从数据库返回的对象")
@Data
public class Carousel {
    /**
     * 主键
     */
    @Id
    @ApiModelProperty(value = "主键", name = "id", example = "c-10013", required = true)
    private String id;

    /**
     * 图片 图片地址
     */
    @Column(name = "image_url")
    @ApiModelProperty(value = "图片地址", name = "imageUrl",
        example = "http://122.152.205.72:88/group1/M00/00/05/CpoxxF0ZmHiAWwR7AAFdqZHw8jU876.png", required = true)
    private String imageUrl;

    /**
     * 背景色 背景颜色
     */
    @Column(name = "background_color")
    @ApiModelProperty(value = "背景颜色", name = "backgroundColor", example = "#000240", required = true)
    private String backgroundColor;

    /**
     * 商品id 商品id
     */
    @Column(name = "item_id")
    @ApiModelProperty(value = "商品id", name = "itemId")
    private String itemId;

    /**
     * 商品分类id 商品分类id
     */
    @Column(name = "cat_id")
    @ApiModelProperty(value = "商品分类id", name = "catId")
    private String catId;

    /**
     * 轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类
     */
    @ApiModelProperty(value = "轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类", name = "type", example = "1", required = true)
    private Integer type;

    /**
     * 轮播图展示顺序 轮播图展示顺序，从小到大
     */
    @ApiModelProperty(value = "轮播图展示顺序 轮播图展示顺序，从小到大", name = "sort", example = "1", required = true)
    private Integer sort;

    /**
     * 是否展示 是否展示，1：展示 0：不展示
     */
    @Column(name = "is_show")
    @ApiModelProperty(value = "是否展示，1：展示    0：不展示", name = "isShow", example = "1", required = true)
    private Integer isShow;

    /**
     * 创建时间 创建时间
     */
    @Column(name = "create_time")
    @JsonIgnore
    private Date createTime;

    /**
     * 更新时间 更新
     */
    @Column(name = "update_time")
    @JsonIgnore
    private Date updateTime;

}