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
     * @param userId 用户id
     * @return java.util.List<com.so.pojo.UserAddress>
     */
    List<UserAddress> queryAll(String userId);

    /**
     * 新增或者修改收货地址
     * @param bo 地址对象
     */
    void addNewUserAddress(AddressBO bo);

    /**
     * 用户修改地址
     * @param bo 地址对象
     * @return void
     */
    void updateUserAddress(AddressBO bo);

    /**
     * 用户删除地址
     * @param userId    用户id
     * @param addressId 地址id
     */
    void deleteUserAddress(String userId, String addressId);

    /**
     * 设置默认地址
     * @param userId    用户id
     * @param addressId 地址id
     */
    void updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * 根据用户id和地址id，查询具体的用户地址对象信息
     * @param userId    用户id
     * @param addressId 地址id
     * @return com.so.pojo.UserAddress
     */
    UserAddress queryAddress(String userId, String addressId);

}
