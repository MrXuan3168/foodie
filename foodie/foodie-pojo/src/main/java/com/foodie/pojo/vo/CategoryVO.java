package com.foodie.pojo.vo;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 应用模块名称：二级分类VO
 * 
 * @author show
 * @since 2019/11/28 23:05
 */
@Data
@ApiModel(value = "二级分类VO", description = "从数据库返回的对象")
public class CategoryVO {

    @ApiModelProperty(value = "主键", name = "id", example = "11", required = true)
    private Integer id;

    @ApiModelProperty(value = "分类名称", name = "name", example = "蛋糕", required = true)
    private String name;

    @ApiModelProperty(value = "分类类型", name = "type", example = "2", required = true)
    private String type;

    @ApiModelProperty(value = "父ID", name = "fatherId", example = "1", required = true)
    private String fatherId;

    @ApiModelProperty(value = "三级分类Vo", name = "subCatList")
    private List<SubCategoryVO> subCatList;

}
