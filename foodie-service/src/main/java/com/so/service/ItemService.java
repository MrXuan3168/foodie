package com.so.service;

import java.util.List;

import com.so.pojo.Items;
import com.so.pojo.ItemsImg;
import com.so.pojo.ItemsParam;
import com.so.pojo.ItemsSpec;
import com.so.utils.PagedGridResult;
import com.so.vo.CommentLevelCountsVO;

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
     * @author show
     * @date 2019/11/30 20:54
     * @param itemId
     * @return com.so.pojo.Items
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品Id查询商品图片列表
     *
     * @author show
     * @date 2019/11/30 20:56
     * @param itemId
     * @return java.util.List<com.so.pojo.ItemsImg>
     */
    List<ItemsImg> queryItemImgList(String itemId);

    /**
     * 根据商品Id查询商品规格列表
     *
     * @author show
     * @date 2019/11/30 20:56
     * @param itemId
     * @return java.util.List<com.so.pojo.ItemsSpec>
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品Id查询商品参数
     *
     * @author show
     * @date 2019/11/30 20:57
     * @param itemId
     * @return com.so.pojo.ItemsParam
     */
    ItemsParam queryItemParam(String itemId);

    /**
     * 根据商品Id查询商品的评价等级数据
     *
     * @author show
     * @date 2019/11/30 21:32
     * @param itemId
     * @return com.so.vo.CommentLevelCountsVO
     */
    CommentLevelCountsVO queryCommentCounts(String itemId);

    /**
     * 根据商品Id查询商品的评价列表
     * 
     * @author show
     * @date 2019/11/30 22:11
     * @param itemId
     * @param level
     * @return java.util.List<com.so.vo.ItemCommentVO>
     */
    PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);
}
