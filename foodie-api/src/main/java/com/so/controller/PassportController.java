package com.so.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.so.bo.UserBO;
import com.so.pojo.Users;
import com.so.service.UserService;
import com.so.utils.ServerResponseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 应用模块名称：
 * 
 * @author show
 * @since 2019/11/19 20:16
 */
@Api(value = "注册登录", tags = "用户注册登录的相关接口")
@RestController
@RequestMapping("passport")
public class PassportController {
    @Autowired
    UserService userService;

    /**
     * 判断用户名是否存在
     * 
     * @author xuanweiyao
     * @date 2019/11/25 17:43
     * @param username
     *            用户名
     */
    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public ServerResponseResult usernameIsExist(@RequestParam String username) {
        // 1.判断用户名不能为空
        if (StringUtils.isBlank(username)) {
            return ServerResponseResult.errorMsg("用户名不能为空");
        }
        // 2.查找注册是用户名是否存在
        if (userService.queryUsernameIsExist(username)) {
            return ServerResponseResult.errorMsg("用户名已存在");
        }
        // 3.请求成功，用户名没有重复
        return ServerResponseResult.ok();
    }

    /**
     * @author xuanweiyao
     * @date 2019/11/25 13:14
     * @param userBo
     *            用户注册类
     */
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/register")
    public ServerResponseResult register(@Validated @RequestBody UserBO userBo) {
        // 判断两次密码是否一致
        if (!StringUtils.equals(userBo.getPassword(), userBo.getConfirmPassword())) {
            return ServerResponseResult.errorMsg("密码与确认密码不一致");
        }
        // 查询用户名是否存在
        boolean usernameIsExist = userService.queryUsernameIsExist(userBo.getUsername());
        if (usernameIsExist) {
            return ServerResponseResult.errorMsg("用户名已存在");
        }
        Users user = userService.createUser(userBo);
        // 3.请求成功，用户名没有重复
        return ServerResponseResult.ok(user);
    }
}
