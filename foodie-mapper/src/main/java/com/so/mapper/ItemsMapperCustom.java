package com.so.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.so.vo.ItemCommentVO;

/**
 * 自定义Items
 * 
 * @author show
 * @date 2019/11/30 21:59
 */
public interface ItemsMapperCustom {
    /**
     * 获取评价详情
     * 
     * @author show
     * @date 2019/11/30 22:15
     * @param paramsMap
     * @return java.util.List<com.so.vo.ItemCommentVO>
     */
    List<ItemCommentVO> queryItemComments(@Param("paramsMap") Map<String, Object> paramsMap);

}