package com.foodie.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 应用模块名称：用户展示商品评价数据的Vo
 *
 * @author jamie
 * @since 2019/11/30 21:30
 */
@Data
@ApiModel(value = "用户展示商品评价数据的VO", description = "从数据库返回的对象")
public class CommentLevelCountsVO {

    @ApiModelProperty(value = "总评价数量", name = "totalCounts", example = "10", required = true)
    public Integer totalCounts;

    @ApiModelProperty(value = "好评数量", name = "goodCounts", example = "1", required = true)
    public Integer goodCounts;

    @ApiModelProperty(value = "中评数量", name = "normalCounts", example = "7", required = true)
    public Integer normalCounts;

    @ApiModelProperty(value = "差评数量", name = "badCounts", example = "2", required = true)
    public Integer badCounts;

}
