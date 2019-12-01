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
import com.so.mapper.UserAddressMapper;
import com.so.pojo.UserAddress;
import com.so.service.AddressService;

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
    public void addNewUserAddress(AddressBO addressBo) {
        // 1.判断用户是否存在地址，如果没有，则新增为"默认地址"
        int isDefault = 0;
        List<UserAddress> addressList = this.queryAll(addressBo.getUserId());
        if (addressList == null || addressList.isEmpty()) {
            isDefault = 1;
        }

        // 2.保存地址到数据库
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressBo, userAddress);
        userAddress.setId(sid.nextShort());
        userAddress.setIsDefault(isDefault);
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        userAddressMapper.insert(userAddress);
    }
}
