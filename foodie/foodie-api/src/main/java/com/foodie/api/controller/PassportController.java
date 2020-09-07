package com.foodie.api.controller;

import com.foodie.common.utils.*;
import com.foodie.pojo.Users;
import com.foodie.pojo.bo.LoginUserBO;
import com.foodie.pojo.bo.RegisterUserBO;
import com.foodie.pojo.bo.ShopCartBO;
import com.foodie.pojo.vo.UserVO;
import com.foodie.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 应用模块名称：
 * @author jamie
 * @since 2019/11/19 20:16
 */
@Api(tags = "用户注册登录的相关API")
@RestController
@RequestMapping("passport")
@CrossOrigin
@Slf4j
public class PassportController extends BaseController {

    @Autowired
    UserService userService;

    @Autowired
    RedisUtils redisUtils;

    @ApiOperation(value = "用户名是否存在", notes = "判断用户名是否存在", httpMethod = "GET")
    @ApiImplicitParam(name = "username", value = "用户名", required = true)
    @GetMapping("/usernameIsExist")
    public R<Void> usernameIsExist(@RequestParam String username) {
        // 1.判断用户名不能为空
        if(StringUtils.isBlank(username)){
            return R.errorMsg("用户名不能为空");
        }
        // 2.查找注册是用户名是否存在
        if(userService.queryUsernameIsExist(username)){
            return R.errorMsg("用户名已存在");
        }
        // 3.请求成功，用户名没有重复
        return R.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("/register")
    public R<UserVO> register(@Validated @RequestBody RegisterUserBO bo, HttpServletRequest request,
                              HttpServletResponse response) {
        String username = bo.getUsername();
        String password = bo.getPassword();
        String confirmPassword = bo.getConfirmPassword();
        // 判断两次密码是否一致
        if(!StringUtils.equals(password, confirmPassword)){
            return R.errorMsg("密码与确认密码不一致");
        }
        // 查询用户名是否存在
        boolean usernameIsExist = userService.queryUsernameIsExist(username);
        if(usernameIsExist){
            return R.errorMsg("用户名已存在");
        }
        // 用户插入数据
        Users user = userService.createUser(bo);
        UserVO userVO = this.conventUserVo(user);
        // isEncode是否加密
        CookieUtils.setCookie(request, response, "user", JacksonUtils.objectToJson(userVO), true);
        // 3.请求成功，用户名没有重复
        this.synchShopcartData(userVO.getId(), request, response);
        return R.ok(userVO);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public R<UserVO> login(@Validated @RequestBody LoginUserBO bo, HttpServletRequest request,
                           HttpServletResponse response) throws Exception {
        String username = bo.getUsername();
        String password = bo.getPassword();
        Users user = userService.queryUserForLogin(username, Md5Utils.getMd5Str(password));
        if(user == null){
            return R.errorMsg("用户名和密码不正确");
        }
        UserVO userVO = this.conventUserVo(user);
        // isEncode是否加密
        CookieUtils.setCookie(request, response, "user", JacksonUtils.objectToJson(userVO), true);

        // 同步购物车数据
        this.synchShopcartData(userVO.getId(), request, response);
        return R.ok(userVO);

    }

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    public R<Void> logout(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {
        // 清除用户相关的信息的 cookie
        CookieUtils.deleteCookie(request, response, "user");
        //  用户退出登录，清除redis的会话信息
        redisUtils.del(REDIS_USER_TOKEN + ":" + userId);
        // 分布式会话中需要清除用户数据
        CookieUtils.deleteCookie(request, response, FOODIE_SHOP_CART);
        return R.ok();
    }

    /**
     * 注册登录成功后，同步cookie和redis中的购物车数据
     */
    private void synchShopcartData(String userId, HttpServletRequest request, HttpServletResponse response) {

        /**
         * 1. redis中无数据，如果cookie中的购物车为空，那么这个时候不做任何处理
         *                 如果cookie中的购物车不为空，此时直接放入redis中
         * 2. redis中有数据，如果cookie中的购物车为空，那么直接把redis的购物车覆盖本地cookie
         *                 如果cookie中的购物车不为空，
         *                      如果cookie中的某个商品在redis中存在，
         *                      则以cookie为主，删除redis中的，
         *                      把cookie中的商品直接覆盖redis中（参考京东）
         * 3. 同步到redis中去了以后，覆盖本地cookie购物车的数据，保证本地购物车的数据是同步最新的
         */

        // 从redis中获取购物车
        String shopcartJsonRedis = redisUtils.get(FOODIE_SHOP_CART + ":" + userId);

        // 从cookie中获取购物车
        String shopcartStrCookie = CookieUtils.getCookieValue(request, FOODIE_SHOP_CART, true);

        if(StringUtils.isBlank(shopcartJsonRedis)){
            // redis为空，cookie不为空，直接把cookie中的数据放入redis
            if(StringUtils.isNotBlank(shopcartStrCookie)){
                redisUtils.set(FOODIE_SHOP_CART + ":" + userId, shopcartStrCookie);
            }
            return;
        }
        // redis不为空，cookie不为空，合并cookie和redis中购物车的商品数据（同一商品则覆盖redis）
        if(StringUtils.isNotBlank(shopcartStrCookie)){
            /**
             * 1. 已经存在的，把cookie中对应的数量，覆盖redis（参考京东）
             * 2. 该项商品标记为待删除，统一放入一个待删除的list
             * 3. 从cookie中清理所有的待删除list
             * 4. 合并redis和cookie中的数据
             * 5. 更新到redis和cookie中
             */

            List<ShopCartBO> shopcartListRedis = JacksonUtils.jsonToList(shopcartJsonRedis, ShopCartBO.class);
            List<ShopCartBO> shopcartListCookie = JacksonUtils.jsonToList(shopcartStrCookie, ShopCartBO.class);
            // 定义一个待删除list
            List<ShopCartBO> pendingDeleteList = new ArrayList<>();
            for(ShopCartBO redisShopcart: shopcartListRedis){
                String redisSpecId = redisShopcart.getSpecId();
                for(ShopCartBO cookieShopcart: shopcartListCookie){
                    String cookieSpecId = cookieShopcart.getSpecId();
                    if(redisSpecId.equals(cookieSpecId)){
                        // 覆盖购买数量，不累加，参考京东
                        redisShopcart.setBuyCounts(cookieShopcart.getBuyCounts());
                        // 把cookieShopcart放入待删除列表，用于最后的删除与合并
                        pendingDeleteList.add(cookieShopcart);
                    }

                }
            }
            // 从现有cookie中删除对应的覆盖过的商品数据
            shopcartListCookie.removeAll(pendingDeleteList);
            // 合并两个list
            shopcartListRedis.addAll(shopcartListCookie);
            // 更新到redis和cookie
            CookieUtils.setCookie(request, response, FOODIE_SHOP_CART, JacksonUtils.objectToJson(shopcartListRedis),
                    true);
            redisUtils.set(FOODIE_SHOP_CART + ":" + userId, JacksonUtils.objectToJson(shopcartListRedis));
            return;
        }
        // redis不为空，cookie为空，直接把redis覆盖cookie
        CookieUtils.setCookie(request, response, FOODIE_SHOP_CART, shopcartJsonRedis, true);

    }

    /**
     * 转换对象，并生成token插入
     * @param user 用户对象
     * @return com.foodie.pojo.vo.UserVO
     * @author jamie
     * @date 2020/9/7 22:38
     */
    private UserVO conventUserVo(Users user) {
        UserVO userVO = UserVO.builder().build();
        BeanUtils.copyProperties(user, userVO);
        // 实现用的的redis会话
        String uniqueToken = UUID.randomUUID().toString().trim();
        redisUtils.set(REDIS_USER_TOKEN + ":" + user.getId(), uniqueToken);
        userVO.setUniqueToken(uniqueToken);
        return userVO;
    }

}
