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

}
