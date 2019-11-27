package com.so.enums;

/**
 * 是否 枚举
 * 
 * @author xuanweiyao
 * @date 2019/11/25 12:47
 */
public enum YesOrNo {
    /** 是否 枚举 */
    NO(0, "否"), YES(1, "是");

    public final Integer type;
    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
