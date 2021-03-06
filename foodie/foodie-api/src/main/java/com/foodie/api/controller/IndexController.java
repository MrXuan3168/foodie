package com.foodie.api.controller;

import com.foodie.common.enums.YesOrNo;
import com.foodie.common.utils.JacksonUtils;
import com.foodie.common.utils.R;
import com.foodie.common.utils.RedisUtils;
import com.foodie.pojo.Carousel;
import com.foodie.pojo.Category;
import com.foodie.pojo.vo.CategoryVO;
import com.foodie.pojo.vo.NewItemsVO;
import com.foodie.service.CarouselService;
import com.foodie.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用模块名称：
 * @author jamie
 * @since 2019/11/19 20:16
 */
@CrossOrigin
@Api(tags = "首页展示的相关接口")
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
    @GetMapping("/carousel")
    public R<List<Carousel>> carousel() {
        String carousel = redisUtils.get("carousel");
        if(StringUtils.isNotBlank(carousel)){
            List<Carousel> carousels = JacksonUtils.jsonToList(carousel, Carousel.class);
            return R.ok(carousels);
        }
        List<Carousel> carousels = carouselService.queryAll(YesOrNo.YES.type);
        redisUtils.set("carousel", JacksonUtils.objectToJson(carousels));
        return R.ok(carousels);
    }

    /**
     * 首页分类展示要求:
     * 1.第一次刷新主页查询大分类，渲染展示到首页
     * 2.如果鼠标上移到大分类，则加载其子分类的内容，如果已经存在子分类，则不需要加载(懒加载)
     */
    @ApiOperation(value = "获取商品分类(一级分类)", notes = "获取商品分类(一级分类)", httpMethod = "GET")
    @GetMapping("/cats")
    public R<List<Category>> cats() {
        String category = redisUtils.get("category");
        if(StringUtils.isNotBlank(category)){
            List<Category> categories = JacksonUtils.jsonToList(category, Category.class);
            return R.ok(categories);
        }
        List<Category> categories = categoryService.queryAllRootLevelCat();
        redisUtils.set("category", JacksonUtils.objectToJson(categories));
        return R.ok(categories);
    }

    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @ApiImplicitParam(name = "rootCatId", value = "一级分类id", example = "1", required = true)
    @GetMapping("/subCat/{rootCatId}")
    public R<List<CategoryVO>> getSubCatList(@PathVariable Integer rootCatId) {
        if(rootCatId == null){
            return R.errorMsg("分类Id rootCatId 不能为空");
        }
        String categoryKey = "category:" + rootCatId;
        String category = redisUtils.get(categoryKey);
        if(StringUtils.isNotBlank(category)){
            List<CategoryVO> categories = JacksonUtils.jsonToList(category, CategoryVO.class);
            return R.ok(categories);
        }
        List<CategoryVO> subCatList = categoryService.getSubCatList(rootCatId);
        redisUtils.set(categoryKey, JacksonUtils.objectToJson(subCatList));
        return R.ok(subCatList);
    }

    @ApiOperation(value = "查询每个一级分类下的最新6个数据", notes = "查询每个一级分类下的最新6个数据", httpMethod = "GET")
    @ApiImplicitParam(name = "rootCatId", value = "一级分类id", example = "1", required = true)
    @GetMapping("/sixNewItems/{rootCatId}")
    public R<List<NewItemsVO>> getSixNewItems(@PathVariable Integer rootCatId) {
        if(rootCatId == null){
            return R.errorMsg("分类Id rootCatId 不能为空");
        }
        List<NewItemsVO> sixNewItemsLazy = categoryService.getSixNewItemsLazy(rootCatId);
        return R.ok(sixNewItemsLazy);
    }

}
