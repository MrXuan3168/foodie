package com.so.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.so.bo.UserBO;
import com.so.bo.UserLoginBO;
import com.so.pojo.Users;
import com.so.service.UserService;
import com.so.utils.CookieUtils;
import com.so.utils.JsonUtils;
import com.so.utils.Md5Utils;
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
    public ServerResponseResult register(@Validated @RequestBody UserBO userBo, HttpServletRequest request,
        HttpServletResponse response) {
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

        this.setNullProperty(user);
        // isEncode是否加密
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);
        // 3.请求成功，用户名没有重复
        return ServerResponseResult.ok(user);
    }

    /**
     * 用户登录
     * 
     * @author xuanweiyao
     * @date 2019/11/25 23:18
     * @param bo
     *            用户登录
     * @return com.so.utils.ServerResponseResult
     */
    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public ServerResponseResult login(@Validated @RequestBody UserLoginBO bo, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        String username = bo.getUsername();
        String password = bo.getPassword();
        Users user = userService.queryUserForLogin(username, Md5Utils.getMd5Str(password));
        if (user == null) {
            return ServerResponseResult.errorMsg("用户名和密码不正确");
        }
        this.setNullProperty(user);
        // isEncode是否加密
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);
        return ServerResponseResult.ok(user);

    }

    private void setNullProperty(Users users) {
        users.setPassword(null);
        users.setRealname(null);
        users.setMobile(null);
        users.setEmail(null);
        users.setCreatedTime(null);
        users.setUpdatedTime(null);
        users.setBirthday(null);
    }

}
