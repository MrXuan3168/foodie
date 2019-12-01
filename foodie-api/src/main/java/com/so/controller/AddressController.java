package com.so.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.so.pojo.UserAddress;
import com.so.service.AddressService;
import com.so.utils.Rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 应用模块名称：地址</br>
 *
 * @author show
 * @since 2019/11/12 16:13
 */
@RestController
@Slf4j
@CrossOrigin
@Api(value = "地址相关接口Controller", tags = {"地址相关接口相关的api"})
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * 用户在确认订单页面，可以针对收货地址做如下操作：<br>
     * 1.查询用户的所有收货地址列表<br>
     * 2.新装收货地址<br>
     * 3.删除收货地址<br>
     * 4.修改收货地址<br>
     * 5.设置默认地址
     */
    @ApiOperation(value = "查询用户的所有收货地址列表", notes = "查询用户的所有收货地址列表", httpMethod = "POST")
    @PostMapping("/list")
    public Rest<List<UserAddress>> list(@RequestParam String userId) {
        if (StringUtils.isBlank(userId)) {
            return Rest.errorMsg("用户ID不能为空");
        }
        List<UserAddress> list = addressService.queryAll(userId);
        return Rest.ok(list);
    }

}
