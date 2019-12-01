package com.so.service;

import java.util.List;

import com.so.pojo.UserAddress;

/**
 * 应用模块名称：地址业务层
 * 
 * @author show
 * @since 2019/11/27 12:39
 */
public interface AddressService {
    /**
     * 根据用户ID查询用户的收货地址列表
     * 
     * @author show
     * @date 2019/12/1 16:48
     * @param userId
     * @return java.util.List<com.so.pojo.UserAddress>
     */
    List<UserAddress> queryAll(String userId);

}
