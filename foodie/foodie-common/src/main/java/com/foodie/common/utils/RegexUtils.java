package com.foodie.common.utils;

/**
 * 正则表达式工具类
 *
 * @author jamie
 */
public class RegexUtils {
    /** 手机号码正则表达式 */
    private static final String MOBILE_REGEX =
        "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
    /** 邮箱号码正则表达式 */
    private static final String EMAIL_REGEX = "[\\w.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";

    /**
     * 手机号码格式校验
     * 
     * @param mobile 手机号码
     * @return boolean
     */
    public static boolean checkMobile(String mobile) {
        return mobile.matches(MOBILE_REGEX);
    }

    /**
     * 邮箱地址格式校验
     * 
     * @param email 邮箱地址
     * @return boolean
     */
    public static boolean checkEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

}
