package com.so.vo;

import lombok.Data;

/**
 * 应用模块名称：6个最新商品的简单数据类型
 * 
 * @author show
 * @since 2019/11/28 23:52
 */
@Data
public class SimpleItemVO {
    private String itemId;
    private String itemName;
    private String itemUrl;
    private String createdTime;

}
