package com.foodie.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用来返回分页Grid的数据格式
 * @author jamie
 */
@Data
@ApiModel(value = "分页返回对象", description = "从客户端返回的对象，为json格式")
public class PageR<T> {

    /** 当前页数 */
    @ApiModelProperty(value = "当前页数", example = "1", required = true)
    private int page;

    /** 总页数 */
    @ApiModelProperty(value = "总页数", example = "20", required = true)
    private int total;

    /** 总记录数 */
    @ApiModelProperty(value = "总记录数", example = "45", required = true)
    private long records;

    /** 每行显示的内容 */
    @ApiModelProperty(value = "每行显示的内容", required = true)
    private List<T> rows;

}
