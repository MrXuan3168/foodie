package com.foodie.service.impl;

import com.foodie.mapper.CarouselMapper;
import com.foodie.pojo.Carousel;
import com.foodie.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 应用模块名称：商品业务层实现类
 * @author jamie
 * @since 2019/11/27 12:40
 */
@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public List<Carousel> queryAll(Integer isShow) {
        Example example = new Example(Carousel.class);
        // 倒序
        example.orderBy("sort").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow", isShow);
        return carouselMapper.selectByExample(example);
    }

}
