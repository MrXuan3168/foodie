package com.foodie.common.enums;

/**
 * 支付方式 枚举
 * @author jamie
 */
public enum PayMethod {
    /** 支付方式 */
    WE_CHAT(1, "微信"),
    ALI_PAY(2, "支付宝");

    public final Integer type;

    public final String value;

    PayMethod(Integer type, String value) {
        this.type = type;
        this.value = value;
    }

}
