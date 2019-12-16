package com.so.mapper;

import java.util.List;
import java.util.Map;

import com.so.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import com.so.vo.CategoryVO;

/**
 * 自定义
 * 
 * @author show
 * @date 2019/11/28 23:14
 */
public interface CategoryMapperCustom {
    /**
     * 根据一级分类id获取子分类
     *
     * @author show
     * @date 2019/11/28 23:04
     * @param rootCatId
     *            一级分类ID
     * @return java.util.List
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的6条信息商品信息
     * 
     * @author show
     * @date 2019/11/28 23:43
     * @param map
     *            一级分类ID
     * @return java.util.List
     */
    List<NewItemsVO> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);

}