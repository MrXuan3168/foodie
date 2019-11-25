package com.so.service;

import com.so.bo.UserBO;
import com.so.pojo.Users;

/**
 * 应用模块名称：
 * 
 * @author show
 * @since 2019/11/25 11:00
 */
public interface UserService {
    /**
     * 判断用户名是否存在
     *
     * @author show
     * @date 2019/11/25 11:02
     * @param username
     *            用户名
     * @return boolean
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * 
     * @author show
     * @date 2019/11/25 12:26
     * @param userBo
     *            前端入参对象
     * @return com.so.pojo.Users
     */
    Users createUser(UserBO userBo);

    /**
     * 用户登录
     * 
     * @author show
     * @date 2019/11/25 23:07
     * @param username
     *            用户名
     * @param password
     *            登录密码
     * @return com.so.pojo.Users
     */
    Users queryUserForLogin(String username, String password);
}
