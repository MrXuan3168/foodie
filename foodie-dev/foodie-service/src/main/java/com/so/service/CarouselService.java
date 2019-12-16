package com.so.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.so.pojo.Carousel;

/**
 * 应用模块名称：商品业务层
 * 
 * @author show
 * @since 2019/11/27 12:39
 */
public interface CarouselService {
    /**
     * 获取首页轮播图
     * 
     * @author show
     * @date 2019/11/27 12:41
     * @param isShow
     *            是否显示
     * @return void
     */
    List<Carousel> queryAll(Integer isShow);
}
