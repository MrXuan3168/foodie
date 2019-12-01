package com.so.service;

import java.util.List;

import com.so.pojo.Items;
import com.so.pojo.ItemsImg;
import com.so.pojo.ItemsParam;
import com.so.pojo.ItemsSpec;
import com.so.utils.PagedGridResult;
import com.so.vo.CommentLevelCountsVO;
import com.so.vo.ShopCartVO;

/**
 * 应用模块名称：
 *
 * @author show
 * @since 2019/11/27 12:39
 */
public interface ItemService {
    /**
     * 根据商品ID查询详情
     *
     * @param itemId
     * @return com.so.pojo.Items
     * @author show
     * @date 2019/11/30 20:54
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品Id查询商品图片列表
     *
     * @param itemId
     * @return java.util.List<com.so.pojo.ItemsImg>
     * @author show
     * @date 2019/11/30 20:56
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品Id查询商品规格列表
     *
     * @param itemId
     * @return java.util.List<com.so.pojo.ItemsSpec>
     * @author show
     * @date 2019/11/30 20:56
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品Id查询商品参数
     *
     * @param itemId
     * @return com.so.pojo.ItemsParam
     * @author show
     * @date 2019/11/30 20:57
     */
    ItemsParam queryItemParam(String itemId);

    /**
     * 根据商品Id查询商品的评价等级数据
     *
     * @param itemId
     * @return com.so.vo.CommentLevelCountsVO
     * @author show
     * @date 2019/11/30 21:32
     */
    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 根据商品Id查询商品的评价列表
     *
     * @param itemId
     * @param level
     * @param page
     * @param pageSize
     * @return java.util.List<com.so.vo.ItemCommentVO>
     * @author show
     * @date 2019/11/30 22:11
     */
    PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

    /**
     * 搜索商品列表
     *
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return com.so.utils.PagedGridResult
     * @author show
     * @date 2019/12/1 14:24
     */
    PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    /**
     * 根据三级分类搜索商品列表
     *
     * @param catId
     * @param sort
     * @param page
     * @param pageSize
     * @return com.so.utils.PagedGridResult
     * @author show
     * @date 2019/12/1 14:24
     */
    PagedGridResult searchItemsByThirdCat(Integer catId, String sort, Integer page, Integer pageSize);
    /**
     * 根据规格IDS查询最新的购物车中商品数据(用于刷新渲染购物车中的商品数据)
     * @author show
     * @date 2019/12/1 15:55 
     * @param specIds
     * @return java.util.List<com.so.vo.ShopCartVO>
     */
    List<ShopCartVO> queryItemsBySpecIds(String specIds);

}
