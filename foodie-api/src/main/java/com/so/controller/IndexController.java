package com.so.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.so.enums.YesOrNo;
import com.so.pojo.Carousel;
import com.so.service.CarouselService;
import com.so.utils.Rest;

import io.swagger.annotations.Api;
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
public class IndexController {
    @Autowired
    CarouselService carouselService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    /**
     * 获取首页轮播图
     * 
     * @author xuanweiyao
     * @date 2019/11/25 17:43
     */
    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
    @GetMapping("/carousel")
    public Rest<List<Carousel>> carousel() {
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.type);
        return Rest.ok(list);
    }

}
