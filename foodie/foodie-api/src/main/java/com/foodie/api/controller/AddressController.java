package com.foodie.api.controller;

import java.util.List;

import com.foodie.pojo.bo.AddressBO;
import com.foodie.pojo.pojo.UserAddress;
import com.foodie.service.AddressService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.foodie.common.utils.Rest;
import com.foodie.common.validation.Update;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
@Api(value = "地址相关接口Controller", tags = {"地址相关的api"})
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
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, example = "1908189H7TNWDTXP")
    @PostMapping("/list")
    public Rest<List<UserAddress>> list(@RequestParam String userId) {
        if (StringUtils.isBlank(userId)) {
            return Rest.errorMsg("用户ID不能为空");
        }
        List<UserAddress> list = addressService.queryAll(userId);
        return Rest.ok(list);
    }

    @ApiOperation(value = "新装收货地址", notes = "新装收货地址", httpMethod = "POST")
    @ApiOperationSupport(ignoreParameters = {"addressId"})
    @PostMapping("/add")
    public Rest<Object> add(@Validated @RequestBody AddressBO bo) {
        addressService.addNewUserAddress(bo);
        return Rest.ok();
    }

    @ApiOperation(value = "用户修改地址", notes = "用户修改地址", httpMethod = "POST")
    @PostMapping("/update")
    public Rest<Object> update(@Validated(Update.class) @RequestBody AddressBO bo) {
        addressService.updateUserAddress(bo);
        return Rest.ok();
    }

    @ApiOperation(value = "用户删除地址", notes = "用户删除地址", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true, example = "1908189H7TNWDTXP"),
        @ApiImplicitParam(name = "addressId", value = "地址Id", required = true, example = "190825CG3AA14Y3C")})
    @PostMapping("/delete")
    public Rest<Object> delete(@RequestParam String userId, @RequestParam String addressId) {
        if (StringUtils.isBlank(userId)) {
            return Rest.errorMsg("用户ID不能为空");
        }
        if (StringUtils.isBlank(addressId)) {
            return Rest.errorMsg("地址Id不能为空");
        }
        addressService.deleteUserAddress(userId, addressId);
        return Rest.ok();
    }

    @ApiOperation(value = "设置默认地址", notes = "设置默认地址", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户id", required = true, example = "1908189H7TNWDTXP"),
        @ApiImplicitParam(name = "addressId", value = "地址Id", required = true, example = "190825CG3AA14Y3C")})
    @PostMapping("/setDefault")
    public Rest<Object> setDefault(@RequestParam String userId, @RequestParam String addressId) {
        if (StringUtils.isBlank(userId)) {
            return Rest.errorMsg("用户ID不能为空");
        }
        if (StringUtils.isBlank(addressId)) {
            return Rest.errorMsg("地址Id不能为空");
        }
        addressService.updateUserAddressToBeDefault(userId, addressId);
        return Rest.ok();
    }

}
