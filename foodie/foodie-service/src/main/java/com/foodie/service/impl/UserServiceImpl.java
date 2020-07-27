package com.foodie.service.impl;

import java.util.Date;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.foodie.pojo.bo.RegisterUserBO;
import com.foodie.common.enums.Sex;
import com.foodie.mapper.UsersMapper;
import com.foodie.pojo.pojo.Users;
import com.foodie.service.UserService;
import com.foodie.common.utils.DateUtil;
import com.foodie.common.utils.Md5Utils;

import tk.mybatis.mapper.entity.Example;

/**
 * 应用模块名称： 用户处理类
 * 
 * @author show
 * @since 2019/11/25 11:01
 */
@Service
public class UserServiceImpl implements UserService {
    private static final String USER_FACE = "https://image-show.oss-cn-shenzhen.aliyuncs.com/FoodieDefaultHead.jpg";
    @Autowired
    UsersMapper usersMapper;
    @Autowired
    Sid sid;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public boolean queryUsernameIsExist(String username) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username", username);
        Users result = usersMapper.selectOneByExample(userExample);
        return result != null;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Users createUser(RegisterUserBO registerUserBo) {
        Users users = new Users();
        users.setId(sid.nextShort());
        users.setUsername(registerUserBo.getUsername());
        try {
            users.setPassword(Md5Utils.getMd5Str(registerUserBo.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 默认用户昵称同用户名
        users.setNickname(registerUserBo.getUsername());
        // 默认头像
        users.setFace(USER_FACE);
        // 默认性别为 保密
        users.setSex(Sex.secret.type);
        // 设置默认生日
        users.setBirthday(DateUtil.stringToDate("1900-01-01"));
        users.setCreatedTime(new Date());
        users.setUpdatedTime(new Date());
        usersMapper.insert(users);
        return users;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
    public Users queryUserForLogin(String username, String password) {
        Example userExample = new Example(Users.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("username", username);
        userCriteria.andEqualTo("password", password);
        return usersMapper.selectOneByExample(userExample);
    }
}
