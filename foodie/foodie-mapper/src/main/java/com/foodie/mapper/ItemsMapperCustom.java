package com.foodie.mapper;

import com.foodie.pojo.vo.ItemCommentVO;
import com.foodie.pojo.vo.SearchItemsVO;
import com.foodie.pojo.vo.ShopCartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 自定义Items
 * @author jamie
 * @date 2019/11/30 21:59
 */
public interface ItemsMapperCustom {

    /**
     * 获取评价详情
     * @param paramsMap
     * @return java.util.List<com.so.vo.ItemCommentVO>
     */
    List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> paramsMap);

    /**
     * 搜索商品列表
     * @param paramsMap
     * @return java.util.List<com.so.vo.SearchItemsVO>
     */
    List<SearchItemsVO> searchItems(@Param("paramsMap") Map<String, Object> paramsMap);

    /**
     * 根据分类搜索商品列表
     * @param paramsMap
     * @return java.util.List<com.so.vo.SearchItemsVO>
     */
    List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap") Map<String, Object> paramsMap);

    /**
     * 刷新购物车
     * @param specIdsList 购物车Ids
     * @return java.util.List<com.so.vo.ShopCartVO>
     */
    List<ShopCartVO> queryItemsBySpecIds(@Param("paramsList") List<String> specIdsList);

    /**
     * 减少库存
     * @param pendingCounts
     * @param specId
     * @return int
     */
    int decreaseItemSpecStock(@Param("pendingCounts") Integer pendingCounts, @Param("specId") String specId);

}