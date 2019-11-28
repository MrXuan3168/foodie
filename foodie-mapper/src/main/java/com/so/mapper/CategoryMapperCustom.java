package com.so.mapper;

import java.util.List;

import com.so.vo.CategoryVO;
/**
 * 自定义
 * @author xuanweiyao
 * @date 2019/11/28 23:14
 */
public interface CategoryMapperCustom {
    /**
     * 根据一级分类id获取子分类
     * 
     * @author xuanweiyao
     * @date 2019/11/28 23:04
     * @param rootCatId
     *            一级分类ID
     * @return java.util.List
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);
}