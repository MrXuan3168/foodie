package com.foodie.api.controller;

import com.foodie.common.utils.R;
import com.foodie.common.validation.Update;
import com.foodie.pojo.bo.AddressBO;
import com.foodie.pojo.pojo.UserAddress;
import com.foodie.service.AddressService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 应用模块名称：地址
 *
 * @author jamie
 * @since 2019/11/12 16:13
 */
@RestController
@Slf4j
@CrossOrigin
@Api(tags = "地址相关的API")
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 用户在确认订单页面，可以针对收货地址做如下操作：
     * 1.查询用户的所有收货地址列表
     * 2.新装收货地址
     * 3.删除收货地址
     * 4.修改收货地址
     * 5.设置默认地址
     */
    @ApiOperation(value = "查询用户的所有收货地址列表", notes = "查询用户的所有收货地址列表", httpMethod = "POST")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true, example = "1908189H7TNWDTXP")
    @PostMapping("/list")
    public R<List<UserAddress>> list(@RequestParam String userId) {
        if(StringUtils.isBlank(userId)){
            return R.errorMsg("用户ID不能为空");
        }
        List<UserAddress> list = addressService.queryAll(userId);
        return R.ok(list);
    }

    @ApiOperation(value = "新装收货地址", notes = "新装收货地址", httpMethod = "POST")
    @ApiOperationSupport(ignoreParameters = {"addressId"})
    @PostMapping("/add")
    public R<Object> add(@Validated @RequestBody AddressBO bo) {
        addressService.addNewUserAddress(bo);
        return R.ok();
    }

    @ApiOperation(value = "用户修改地址", notes = "用户修改地址", httpMethod = "POST")
    @PostMapping("/update")
    public R<Object> update(@Validated(Update.class) @RequestBody AddressBO bo) {
        addressService.updateUserAddress(bo);
        return R.ok();
    }

    @ApiOperation(value = "用户删除地址", notes = "用户删除地址", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户id", required = true, example = "1908189H7TNWDTXP"),
            @ApiImplicitParam(name = "addressId", value = "地址Id", required = true, example = "190825CG3AA14Y3C")})
    @PostMapping("/delete")
    public R<Object> delete(@RequestParam String userId, @RequestParam String addressId) {
        if(StringUtils.isBlank(userId)){
            return R.errorMsg("用户ID不能为空");
        }
        if(StringUtils.isBlank(addressId)){
            return R.errorMsg("地址Id不能为空");
        }
        addressService.deleteUserAddress(userId, addressId);
        return R.ok();
    }

    @ApiOperation(value = "设置默认地址", notes = "设置默认地址", httpMethod = "POST")
    @ApiImplicitParams({@ApiImplicitParam(name = "userId", value = "用户id", required = true, example = "1908189H7TNWDTXP"),
            @ApiImplicitParam(name = "addressId", value = "地址Id", required = true, example = "190825CG3AA14Y3C")})
    @PostMapping("/setDefault")
    public R<Object> setDefault(@RequestParam String userId, @RequestParam String addressId) {
        if(StringUtils.isBlank(userId)){
            return R.errorMsg("用户ID不能为空");
        }
        if(StringUtils.isBlank(addressId)){
            return R.errorMsg("地址Id不能为空");
        }
        addressService.updateUserAddressToBeDefault(userId, addressId);
        return R.ok();
    }

}
