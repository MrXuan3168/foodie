package com.foodie.common.enums;

/**
 * 商品评价等级 枚举
 * 
 * @author show
 * @date 2019/11/25 12:47
 */
public enum CommentLevel {
    /** 是否 枚举 */
    GOOD(0, "好评"), NORMAL(1, "中评"), BAD(2, "差评");

    public final Integer type;
    public final String value;

    CommentLevel(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
