package com.foodie.common.enums;

/**
 * 是否 枚举
 * 
 * @author jamie
 * @date 2019/11/25 12:47
 */
public enum YesOrNo {
    /** 否 */
    NO(0, "否"),
    /** 是 */
    YES(1, "是");

    public final Integer type;
    public final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
