package com.foodie.service;

import com.foodie.pojo.bo.AddressBO;
import com.foodie.pojo.pojo.UserAddress;

import java.util.List;

/**
 * 应用模块名称：地址业务层
 * @author jamie
 * @since 2019/11/27 12:39
 */
public interface AddressService {

    /**
     * 根据用户ID查询用户的收货地址列表
     * @param userId
     * @return java.util.List<com.so.pojo.UserAddress>
     */
    List<UserAddress> queryAll(String userId);

    /**
     * 新增或者修改收货地址
     * @param bo
     */
    void addNewUserAddress(AddressBO bo);

    /**
     * 用户修改地址
     * @param bo
     * @return void
     */
    void updateUserAddress(AddressBO bo);

    /**
     * 用户删除地址
     * @param userId
     * @param addressId
     */
    void deleteUserAddress(String userId, String addressId);

    /**
     * 设置默认地址
     * @param userId
     * @param addressId
     */
    void updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * 根据用户id和地址id，查询具体的用户地址对象信息
     * @param userId
     * @param addressId
     * @return com.so.pojo.UserAddress
     */
    UserAddress queryAddress(String userId, String addressId);

}
