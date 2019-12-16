package com.so.service.impl;

import java.util.Date;
import java.util.List;

import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.so.bo.AddressBO;
import com.so.enums.YesOrNo;
import com.so.mapper.UserAddressMapper;
import com.so.pojo.UserAddress;
import com.so.service.AddressService;

import tk.mybatis.mapper.entity.Example;

/**
 * 应用模块名称：地址业务层实现类
 * 
 * @author show
 * @since 2019/11/27 12:40
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private Sid sid;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public List<UserAddress> queryAll(String userId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        return userAddressMapper.select(userAddress);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addNewUserAddress(AddressBO bo) {
        // 1.判断用户是否存在地址，如果没有，则新增为"默认地址"
        int isDefault = 0;
        List<UserAddress> addressList = this.queryAll(bo.getUserId());
        if (addressList == null || addressList.isEmpty()) {
            isDefault = 1;
        }
        // 2.保存地址到数据库
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(bo, userAddress);
        userAddress.setId(sid.nextShort());
        userAddress.setIsDefault(isDefault);
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        userAddressMapper.insert(userAddress);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserAddress(AddressBO bo) {
        String addressId = bo.getAddressId();
        UserAddress pendingAddress = new UserAddress();
        BeanUtils.copyProperties(bo, pendingAddress);
        pendingAddress.setId(addressId);
        pendingAddress.setUpdatedTime(new Date());
        userAddressMapper.updateByPrimaryKeySelective(pendingAddress);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteUserAddress(String userId, String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddress.setUserId(userId);
        userAddressMapper.delete(userAddress);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserAddressToBeDefault(String userId, String addressId) {
        // 1.把该用户下所有的地址设置为非默认(减少数据库连接次数)
        UserAddress queryAddress = new UserAddress();
        queryAddress.setIsDefault(YesOrNo.NO.type);
        Example example = new Example(UserAddress.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        userAddressMapper.updateByExampleSelective(queryAddress, example);

        // 1. 查找默认地址，设置为不默认(多一次数据库连接数，减少数据库处理压力)
        // UserAddress queryAddress = new UserAddress();
        // queryAddress.setUserId(userId);
        // queryAddress.setIsDefault(YesOrNo.YES.type);
        // UserAddress ua = userAddressMapper.selectOne(queryAddress);
        // ua.setIsDefault(YesOrNo.NO.type);
        // userAddressMapper.updateByPrimaryKeySelective(ua);

        // 2.根据地址ID修改为默认的地址
        UserAddress defaultAddress = new UserAddress();
        defaultAddress.setId(addressId);
        defaultAddress.setUserId(userId);
        defaultAddress.setIsDefault(YesOrNo.YES.type);
        userAddressMapper.updateByPrimaryKeySelective(defaultAddress);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public UserAddress queryAddress(String userId, String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setId(addressId);
        userAddress.setUserId(userId);
        return userAddressMapper.selectOne(userAddress);
    }
}
