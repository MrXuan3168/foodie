package com.foodie.api.controller.center;

import com.foodie.common.utils.R;
import com.foodie.pojo.pojo.Users;
import com.foodie.service.center.CenterUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "center - 用户中心", tags = {"用户中心展示的相关接口"})
@RestController
@RequestMapping("center")
public class CenterController {

    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "GET")
    @GetMapping("userInfo")
    public R<Users> userInfo(@ApiParam(name = "userId", value = "用户id", required = true) @RequestParam String userId) {

        Users user = centerUserService.queryUserInfo(userId);
        return R.ok(user);
    }

}
