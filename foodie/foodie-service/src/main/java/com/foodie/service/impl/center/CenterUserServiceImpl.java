package com.foodie.service.impl.center;

import com.foodie.mapper.UsersMapper;
import com.foodie.pojo.Users;
import com.foodie.pojo.bo.center.CenterUserBO;
import com.foodie.service.center.CenterUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author jamie
 */
@Service
public class CenterUserServiceImpl implements CenterUserService {

    @Autowired
    public UsersMapper usersMapper;

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    @Override
    public Users queryUserInfo(String userId) {
        Users user = usersMapper.selectByPrimaryKey(userId);
        user.setPassword(null);
        return user;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Users updateUserInfo(String userId, CenterUserBO centerUserBO) {
        Users updateUser = new Users();
        BeanUtils.copyProperties(centerUserBO, updateUser);
        updateUser.setId(userId);
        updateUser.setUpdatedTime(new Date());

        usersMapper.updateByPrimaryKeySelective(updateUser);

        return queryUserInfo(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Users updateUserFace(String userId, String faceUrl) {
        Users updateUser = new Users();
        updateUser.setId(userId);
        updateUser.setFace(faceUrl);
        updateUser.setUpdatedTime(new Date());
        usersMapper.updateByPrimaryKeySelective(updateUser);
        return queryUserInfo(userId);
    }

}
