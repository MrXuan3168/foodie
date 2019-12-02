package com.so.bo;

import lombok.Data;

/**
 * 应用模块名称：用于创建订单的bo对象
 *
 * @author show
 * @since 2019/12/2 22:46
 */
@Data
public class SubmitOrderBO {
    private String userId;
    private String itemSpecIds;
    private String addressId;
    private String payMethod;
    private String leftMsg;
}
