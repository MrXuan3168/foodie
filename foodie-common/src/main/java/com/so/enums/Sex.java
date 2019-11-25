package com.so.enums;

/**
 * 性别 枚举
 * 
 * @author xuanweiyao
 * @date 2019/11/25 12:47
 */
public enum Sex {
    /** 性别 枚举 */
    woman(0, "女"), man(1, "男"), secret(2, "保密");

    public final Integer type;
    public final String value;

    Sex(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
