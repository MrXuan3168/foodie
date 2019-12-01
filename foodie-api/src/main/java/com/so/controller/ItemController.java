package com.so.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.so.pojo.Items;
import com.so.pojo.ItemsImg;
import com.so.pojo.ItemsParam;
import com.so.pojo.ItemsSpec;
import com.so.service.ItemService;
import com.so.utils.PagedGridResult;
import com.so.utils.Rest;
import com.so.vo.CommentLevelCountsVO;
import com.so.vo.ItemInfoVO;
import com.so.vo.ShopCartVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 应用模块名称：商品接口
 * 
 * @author show
 * @since 2019/11/19 20:16
 */
@Api(value = "商品接口", tags = "商品展示的相关接口")
@RestController
@RequestMapping("items")
@CrossOrigin
public class ItemController extends BaseController {
    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public Rest<ItemInfoVO>
        info(@ApiParam(name = "items", value = "商品Id", required = true) @PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return Rest.errorMsg("商品ID不能为空");
        }
        Items item = itemService.queryItemById(itemId);
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);
        List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);
        ItemInfoVO itemInfoVO = ItemInfoVO.builder().item(item).itemImgList(itemsImgList).itemParams(itemsParam)
            .itemSpecList(itemsSpecList).build();
        return Rest.ok(itemInfoVO);
    }

    @ApiOperation(value = "查询商品评价等级", notes = "查询商品评价等级", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public Rest<CommentLevelCountsVO>
        commentLevel(@ApiParam(name = "items", value = "商品Id", required = true) @RequestParam String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return Rest.errorMsg("商品ID不能为空");
        }
        CommentLevelCountsVO commentLevelCountsVO = itemService.queryCommentCounts(itemId);
        return Rest.ok(commentLevelCountsVO);
    }

    @ApiOperation(value = "查询商品评论", notes = "查询商品评论", httpMethod = "GET")
    @GetMapping("/comments")
    public Rest<PagedGridResult> queryPagedComments(
        @ApiParam(name = "items", value = "商品Id", required = true) @RequestParam String itemId,
        @ApiParam(name = "level", value = "评价等级") @RequestParam Integer level,
        @ApiParam(name = "page", value = "页码") @RequestParam Integer page,
        @ApiParam(name = "pageSize", value = "页数") @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(itemId)) {
            return Rest.errorMsg("商品ID不能为空");
        }
        if (page == null) {
            page = COMMENT_PAGE;
        }
        if (pageSize == null) {
            pageSize = COMMENT_PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = itemService.queryPagedComments(itemId, level, page, pageSize);
        return Rest.ok(pagedGridResult);
    }

    @ApiOperation(value = "搜索商品列表", notes = "搜索商品列表", httpMethod = "GET")
    @GetMapping("/search")
    public Rest<PagedGridResult> searchItems(
        @ApiParam(name = "keywords", value = "关键字", required = true) @RequestParam String keywords,
        @ApiParam(name = "sort", value = "排序") @RequestParam String sort,
        @ApiParam(name = "page", value = "页码") @RequestParam Integer page,
        @ApiParam(name = "pageSize", value = "页数") @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(keywords)) {
            return Rest.errorMsg("搜索关键字不能为空");
        }
        if (page == null) {
            page = COMMENT_PAGE;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = itemService.searchItems(keywords, sort, page, pageSize);
        return Rest.ok(pagedGridResult);
    }

    @ApiOperation(value = "通过分类ID搜索商品列表", notes = "通过分类ID搜索商品列表", httpMethod = "GET")
    @GetMapping("/catItems")
    public Rest<PagedGridResult> catItems(
        @ApiParam(name = "catId", value = "三级分类ID", required = true) @RequestParam Integer catId,
        @ApiParam(name = "sort", value = "排序") @RequestParam String sort,
        @ApiParam(name = "page", value = "页码") @RequestParam Integer page,
        @ApiParam(name = "pageSize", value = "页数") @RequestParam Integer pageSize) {
        if (catId == null) {
            return Rest.errorMsg("搜索关键字不能为空");
        }
        if (page == null) {
            page = COMMENT_PAGE;
        }
        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = itemService.searchItemsByThirdCat(catId, sort, page, pageSize);
        return Rest.ok(pagedGridResult);
    }

    @ApiOperation(value = "根据商品ids查询最新的商品数据", notes = "用于用户长时间未登录用户，刷新购物车中的数据（主要是商品价格）", httpMethod = "GET")
    @GetMapping("/refresh")
    public Rest<List<ShopCartVO>> refresh(@ApiParam(name = "itemSpecIds", value = "拼接的规格ids",
        example = "1001,1002,1003", required = true) @RequestParam String itemSpecIds) {
        if (StringUtils.isBlank(itemSpecIds)) {
            return Rest.ok();
        }
        List<ShopCartVO> list = itemService.queryItemsBySpecIds(itemSpecIds);
        return Rest.ok(list);
    }

}
