package com.foodie.service;

import java.util.List;

import com.foodie.pojo.vo.CategoryVO;
import com.foodie.pojo.pojo.Category;
import com.foodie.pojo.vo.NewItemsVO;

/**
 * 应用模块名称：分类业务类
 *
 * @author show
 * @since 2019/11/28 22:48
 */
public interface CategoryService {
    /**
     * 查询所有一级分类
     *
     * @author show
     * @date 2019/11/28 22:47
     * @return java.util.List<com.so.pojo.Category>
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类ID查询子分类信息
     * 
     * @author show
     * @date 2019/11/28 23:16
     * @param rootCatId
     *            一级分类ID
     * @return java.util.List<com.so.vo.CategoryVO>
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 根据一级分类ID获取最新6个商品
     * 
     * @author show
     * @date 2019/11/28 23:57
     * @param rootCatId
     *            一级分类ID
     * @return java.util.List<com.so.vo.NewItemsVO>
     */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
