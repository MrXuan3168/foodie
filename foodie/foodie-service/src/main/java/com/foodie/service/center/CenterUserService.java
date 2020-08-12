package com.foodie.service.center;

import com.foodie.pojo.Users;
import com.foodie.pojo.bo.center.CenterUserBO;

/**
 * @author jamie
 */
public interface CenterUserService {

    /**
     * 根据用户id查询用户信息
     * @param userId 用户Id
     * @return com.foodie.pojo.Users
     */
    Users queryUserInfo(String userId);

    /**
     * 修改用户信息
     * @param userId       用户Id
     * @param centerUserBO 用户对象
     * @return com.foodie.pojo.Users
     */
    Users updateUserInfo(String userId, CenterUserBO centerUserBO);

    /**
     * 用户头像更新
     * @param userId  用户Id
     * @param faceUrl 头像url
     * @return com.foodie.pojo.Users
     */
    Users updateUserFace(String userId, String faceUrl);

}
