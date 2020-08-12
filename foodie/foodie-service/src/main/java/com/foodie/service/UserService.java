package com.foodie.service;

import com.foodie.pojo.Users;
import com.foodie.pojo.bo.RegisterUserBO;

/**
 * 应用模块名称：
 * @author jamie
 * @since 2019/11/25 11:00
 */
public interface UserService {

    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return boolean
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param registerUserBo 前端入参对象
     * @return com.so.pojo.Users
     */
    Users createUser(RegisterUserBO registerUserBo);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 登录密码
     * @return com.so.pojo.Users
     */
    Users queryUserForLogin(String username, String password);

}
