package com.so.bo;

import lombok.Data;

/**
 * 应用模块名称： 用户新增或修改地址的BO
 *
 * @author show
 * @since 2019/12/1 16:56
 */
@Data
public class AddressBO {

    private String addressId;
    private String userId;
    private String receiver;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detail;
}
