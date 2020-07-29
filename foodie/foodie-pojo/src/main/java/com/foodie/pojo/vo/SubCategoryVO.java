package com.foodie.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 应用模块名称：三级分类VO
 * 
 * @author jamie
 * @since 2019/11/28 23:08
 */
@Data
@ApiModel(value = "子分类VO", description = "从数据库返回的对象")
public class SubCategoryVO {

    @ApiModelProperty(value = "子主键", name = "id", example = "37", required = true)
    private Integer subId;

    @ApiModelProperty(value = "子分类名称", name = "name", example = "蛋糕", required = true)
    private String subName;

    @ApiModelProperty(value = "子分类类型", name = "subType", example = "2", required = true)
    private String subType;

    @ApiModelProperty(value = "父ID", name = "subFatherId", example = "11", required = true)
    private Integer subFatherId;

}
