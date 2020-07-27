package com.foodie.api.controller;

import com.foodie.pojo.bo.LoginUserBO;
import com.foodie.pojo.bo.RegisterUserBO;
import com.foodie.pojo.pojo.Users;
import com.foodie.service.UserService;
import com.foodie.pojo.vo.UserVO;
import com.foodie.common.utils.CookieUtils;
import com.foodie.common.utils.JsonUtils;
import com.foodie.common.utils.Md5Utils;
import com.foodie.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 应用模块名称：
 *
 * @author jamie
 * @since 2019/11/19 20:16
 */
@Api(value = "注册登录", tags = "用户注册登录的相关接口")
@RestController
@RequestMapping("passport")
@CrossOrigin
@Slf4j
public class PassportController {
    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @author jamie
     * @date 2019/11/25 17:43
     */
    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public R<Object> usernameIsExist(@RequestParam String username) {
        // 1.判断用户名不能为空
        if (StringUtils.isBlank(username)) {
            return R.errorMsg("用户名不能为空");
        }
        // 2.查找注册是用户名是否存在
        if (userService.queryUsernameIsExist(username)) {
            return R.errorMsg("用户名已存在");
        }
        // 3.请求成功，用户名没有重复
        return R.ok();
    }

    /**
     * @param bo 用户注册类
     * @author jamie
     * @date 2019/11/25 13:14
     */
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/register")
    public R<UserVO> register(@Validated @RequestBody RegisterUserBO bo) {
        // 判断两次密码是否一致
        if (!StringUtils.equals(bo.getPassword(), bo.getConfirmPassword())) {
            return R.errorMsg("密码与确认密码不一致");
        }
        // 查询用户名是否存在
        boolean usernameIsExist = userService.queryUsernameIsExist(bo.getUsername());
        if (usernameIsExist) {
            return R.errorMsg("用户名已存在");
        }
        Users user = userService.createUser(bo);
        UserVO userVO = UserVO.builder().build();
        BeanUtils.copyProperties(user, userVO);
        // isEncode是否加密
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);
        // 3.请求成功，用户名没有重复
        return R.ok(userVO);
    }

    /**
     * 用户登录
     *
     * @param bo 用户登录
     * @return com.so.utils.ServerResponseResult
     * @author jamie
     * @date 2019/11/25 23:18
     */
    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public R<UserVO> login(@Validated @RequestBody LoginUserBO bo) throws Exception {
        String username = bo.getUsername();
        String password = bo.getPassword();
        Users user = userService.queryUserForLogin(username, Md5Utils.getMd5Str(password));
        if (user == null) {
            return R.errorMsg("用户名和密码不正确");
        }
        UserVO userVO = UserVO.builder().build();
        BeanUtils.copyProperties(user, userVO);
        // isEncode是否加密
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(user), true);
        // TODO: 2019/12/1 生成用户token， 存入redis会话
        // TODO: 2019/12/1 同步购物车页面
        return R.ok(userVO);

    }

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    public R<Object> logout(@RequestParam String userId) {
        // 清除用户相关的信息的 cookie
        CookieUtils.deleteCookie(request, response, "user");
        // TODO: 2019/11/26 用户退出登录，需要清空购物车
        // TODO: 2019/11/26 分布式会话中需要清除用户数据
        return R.ok();
    }

}
