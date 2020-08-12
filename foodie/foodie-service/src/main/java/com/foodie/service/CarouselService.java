package com.foodie.service;

import com.foodie.pojo.Carousel;

import java.util.List;

/**
 * 应用模块名称：商品业务层
 * @author jamie
 * @since 2019/11/27 12:39
 */
public interface CarouselService {

    /**
     * 获取首页轮播图
     * @param isShow 是否显示
     * @return void
     */
    List<Carousel> queryAll(Integer isShow);

}
