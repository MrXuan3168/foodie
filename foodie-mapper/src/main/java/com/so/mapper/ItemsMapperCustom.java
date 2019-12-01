package com.so.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.so.vo.ItemCommentVO;
import com.so.vo.SearchItemsVO;
import com.so.vo.ShopCartVO;

/**
 * 自定义Items
 *
 * @author show
 * @date 2019/11/30 21:59
 */
public interface ItemsMapperCustom {
    /**
     * 获取评价详情
     *
     * @param paramsMap
     * @return java.util.List<com.so.vo.ItemCommentVO>
     * @author show
     * @date 2019/11/30 22:15
     */
    List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> paramsMap);

    /**
     * 搜索商品列表
     * 
     * @param paramsMap
     * @return java.util.List<com.so.vo.SearchItemsVO>
     * @author show
     * @date 2019/12/1 14:22
     */
    List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> paramsMap);

    /**
     * 根据分类搜索商品列表
     *
     * @param paramsMap
     * @return java.util.List<com.so.vo.SearchItemsVO>
     * @author show
     * @date 2019/12/1 14:22
     */
    List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> paramsMap);

    /**
     * 刷新购物车
     * 
     * @author show
     * @date 2019/12/1 15:50
     * @param specIdsList
     * @return java.util.List<com.so.vo.ShopCartVO>
     */
    List<ShopCartVO> queryItemsBySpecIds(@Param("paramsList") List<String> specIdsList);
}