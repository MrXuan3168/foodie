package com.so.utils;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;

/**
 * 应用模块名称：集合工具类
 * 
 * @author show
 * @since 2019/11/30 14:13
 */
public class ListUtils<T> {
    public void copyList(Object obj, List<T> list2, Class<T> classObj) {
        if ((!Objects.isNull(obj)) && (!Objects.isNull(list2))) {
            List list1 = (List)obj;
            list1.forEach(item -> {
                try {
                    T data = classObj.newInstance();
                    BeanUtils.copyProperties(item, data);
                    list2.add(data);
                } catch (InstantiationException | IllegalAccessException ignored) {
                }
            });
        }
    }
}