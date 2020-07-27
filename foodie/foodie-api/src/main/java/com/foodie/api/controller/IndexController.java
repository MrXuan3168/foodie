package com.foodie.api.controller;

import java.util.List;

import com.foodie.pojo.pojo.Carousel;
import com.foodie.service.CarouselService;
import com.foodie.service.CategoryService;
import com.foodie.pojo.vo.CategoryVO;
import com.foodie.pojo.vo.NewItemsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.foodie.common.enums.YesOrNo;
import com.foodie.pojo.pojo.Category;
import com.foodie.common.utils.Rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 应用模块名称：
 * 
 * @author show
 * @since 2019/11/19 20:16
 */
@Api(value = "首页", tags = "首页展示的相关接口")
@RestController
@RequestMapping("index")
@CrossOrigin
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
    @GetMapping("/carousel")
    public Rest<List<Carousel>> carousel() {
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);
        return Rest.ok(list);
    }

    /**
     * 首页分类展示要求:<br>
     * 1.第一次刷新主页查询大分类，渲染展示到首页<br>
     * 2.如果鼠标上移到大分类，则加载其子分类的内容，如果已经存在子分类，则不需要加载(懒加载)<br>
     */
    @ApiOperation(value = "获取商品分类(一级分类)", notes = "获取商品分类(一级分类)", httpMethod = "GET")
    @GetMapping("/cats")
    public Rest<List<Category>> cats() {
        List<Category> list = categoryService.queryAllRootLevelCat();
        return Rest.ok(list);
    }

    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @ApiImplicitParam(name = "rootCatId", value = "一级分类id", example = "1", required = true)
    @GetMapping("/subCat/{rootCatId}")
    public Rest<List<CategoryVO>> getSubCatList(@PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return Rest.errorMsg("分类不存在");
        }
        List<CategoryVO> subCatList = categoryService.getSubCatList(rootCatId);
        return Rest.ok(subCatList);
    }

    @ApiOperation(value = "查询每个一级分类下的最新6个数据", notes = "查询每个一级分类下的最新6个数据", httpMethod = "GET")
    @ApiImplicitParam(name = "rootCatId", value = "一级分类id", example = "1", required = true)
    @GetMapping("/sixNewItems/{rootCatId}")
    public Rest<List<NewItemsVO>> getSixNewItems(@PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return Rest.errorMsg("分类不存在");
        }
        List<NewItemsVO> sixNewItemsLazy = categoryService.getSixNewItemsLazy(rootCatId);
        return Rest.ok(sixNewItemsLazy);
    }
}