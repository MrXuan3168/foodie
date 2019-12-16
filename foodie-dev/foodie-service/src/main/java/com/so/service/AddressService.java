package com.so.service;

import java.util.List;

import com.so.bo.AddressBO;
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

    /**
     * 新增或者修改收货地址
     * 
     * @author show
     * @date 2019/12/1 17:00
     * @param bo
     */
    void addNewUserAddress(AddressBO bo);

    /**
     * 用户修改地址
     * 
     * @author show
     * @date 2019/12/1 22:30
     * @param bo
     * @return void
     */
    void updateUserAddress(AddressBO bo);

    /**
     * 用户删除地址
     * 
     * @author show
     * @date 2019/12/1 22:41
     * @param userId
     * @param addressId
     */
    void deleteUserAddress(String userId, String addressId);

    /**
     * 设置默认地址
     *
     * @author show
     * @date 2019/12/1 22:41
     * @param userId
     * @param addressId
     */
    void updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * 根据用户id和地址id，查询具体的用户地址对象信息
     * 
     * @author xuanweiyao
     * @date 2019/12/3 22:29
     * @param userId
     * @param addressId
     * @return com.so.pojo.UserAddress
     */
    UserAddress queryAddress(String userId, String addressId);
}
