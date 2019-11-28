package com.so.vo;

import java.util.List;

import lombok.Data;

/**
 * 应用模块名称：二级分类VO
 * 
 * @author show
 * @since 2019/11/28 23:05
 */
@Data
public class CategoryVO {
    private Integer id;
    private String name;
    private String type;
    private String fatherId;
    /**
     * 三级分类Vo
     */
    private List<SubCategoryVO> subCatList;

}
