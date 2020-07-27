package com.foodie.api.controller;

import java.util.List;

import com.foodie.pojo.pojo.Items;
import com.foodie.pojo.pojo.ItemsImg;
import com.foodie.pojo.pojo.ItemsParam;
import com.foodie.pojo.pojo.ItemsSpec;
import com.foodie.service.ItemService;
import com.foodie.pojo.vo.CommentLevelCountsVO;
import com.foodie.pojo.vo.ItemInfoVO;
import com.foodie.pojo.vo.ShopCartVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foodie.common.utils.PagedGridResult;
import com.foodie.common.utils.R;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 应用模块名称：商品接口
 * 
 * @author jamie
 * @since 2019/11/19 20:16
 */
@Api(value = "商品接口", tags = "商品展示相关API")
@RestController
@RequestMapping("items")
@CrossOrigin
public class ItemController extends BaseController {
    @Autowired
    private ItemService itemService;

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @ApiImplicitParam(name = "itemId", value = "商品Id", required = true, example = "cake-1001")
    @GetMapping("/info/{itemId}")
    public R<ItemInfoVO> info(@PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return R.errorMsg("商品ID不能为空");
        }
        Items item = itemService.queryItemById(itemId);
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);
        List<ItemsImg> itemsImgList = itemService.queryItemImgList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);
        ItemInfoVO itemInfoVO = ItemInfoVO.builder().item(item).itemImgList(itemsImgList).itemParams(itemsParam)
            .itemSpecList(itemsSpecList).build();
        return R.ok(itemInfoVO);
    }

    @ApiOperation(value = "查询商品评价等级", notes = "查询商品评价等级", httpMethod = "GET")
    @ApiImplicitParam(name = "itemId", value = "商品Id", required = true, example = "cake-1001")
    @GetMapping("/commentLevel")
    public R<CommentLevelCountsVO> commentLevel(@RequestParam String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return R.errorMsg("商品ID不能为空");
        }
        CommentLevelCountsVO commentLevelCountsVO = itemService.queryCommentCounts(itemId);
        return R.ok(commentLevelCountsVO);
    }

    @ApiOperation(value = "查询商品评论", notes = "查询商品评论", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "itemId", value = "商品Id", required = true, example = "cake-1001"),
        @ApiImplicitParam(name = "level", value = "评价等级", example = "0"),
        @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1"),
        @ApiImplicitParam(name = "pageSize", value = "页数", defaultValue = "20")})
    @GetMapping("/comments")
    public R<PagedGridResult> queryPagedComments(@RequestParam String itemId, @RequestParam Integer level,
                                                 @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "20") Integer pageSize) {
        if (StringUtils.isBlank(itemId)) {
            return R.errorMsg("商品ID不能为空");
        }
        PagedGridResult pagedGridResult = itemService.queryPagedComments(itemId, level, page, pageSize);
        return R.ok(pagedGridResult);
    }

    @ApiOperation(value = "搜索商品列表", notes = "搜索商品列表", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "keywords", value = "关键字", required = true, example = "好吃"),
        @ApiImplicitParam(name = "sort", value = "排序", example = "c"),
        @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1"),
        @ApiImplicitParam(name = "pageSize", value = "页数", defaultValue = "10")})
    @GetMapping("/search")
    public R<PagedGridResult> searchItems(@RequestParam String keywords, @RequestParam String sort,
                                          @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        if (StringUtils.isBlank(keywords)) {
            return R.errorMsg("搜索关键字不能为空");
        }
        PagedGridResult pagedGridResult = itemService.searchItems(keywords, sort, page, pageSize);
        return R.ok(pagedGridResult);
    }

    @ApiOperation(value = "通过分类ID搜索商品列表", notes = "通过分类ID搜索商品列表", httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(name = "catId", value = "三级分类ID", required = true, example = "51"),
        @ApiImplicitParam(name = "sort", value = "排序", example = "c"),
        @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1"),
        @ApiImplicitParam(name = "pageSize", value = "页数", defaultValue = "20")})
    @GetMapping("/catItems")
    public R<PagedGridResult> catItems(@RequestParam Integer catId, @RequestParam String sort,
                                       @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "20") Integer pageSize) {
        if (catId == null) {
            return R.errorMsg("三级分类Id不能为空");
        }
        PagedGridResult pagedGridResult = itemService.searchItemsByThirdCat(catId, sort, page, pageSize);
        return R.ok(pagedGridResult);
    }

    @ApiOperation(value = "根据商品ids查询最新的商品数据", notes = "用于用户长时间未登录用户，刷新购物车中的数据（主要是商品价格）", httpMethod = "GET")
    @ApiImplicitParam(name = "itemSpecIds", value = "拼接的规格ids", required = true, example = "1001,1002,1003")
    @GetMapping("/refresh")
    public R<List<ShopCartVO>> refresh(@RequestParam String itemSpecIds) {
        if (StringUtils.isBlank(itemSpecIds)) {
            return R.ok();
        }
        List<ShopCartVO> list = itemService.queryItemsBySpecIds(itemSpecIds);
        return R.ok(list);
    }

}
