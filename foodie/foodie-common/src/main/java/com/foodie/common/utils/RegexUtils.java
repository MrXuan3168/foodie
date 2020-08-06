package com.foodie.common.utils;

/**
 * 正则表达式工具类
 *
 * @author jamie
 */
public class RegexUtils {

    private static final String SPACE = " ";

    /** 手机号码正则表达式 */
    private static final String MOBILE_REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))" +
            "\\d{8}$";

    /** 邮箱号码正则表达式 */
    private static final String EMAIL_REGEX = "[\\w.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";

    /** 邮箱号码正则表达式 */
    private static final String IP_REGEX = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";

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

    /**
     * IP地址格式校验
     *
     * @param ip IP地址
     * @return boolean
     */
    public static boolean checkIp(String ip) {// 判断是否是一个IP
        boolean b = false;
        ip = trimSpaces(ip);
        if(ip.matches(IP_REGEX)){
            String[] s = ip.split("\\.");
            if(Integer.parseInt(s[0]) < 255){
                if(Integer.parseInt(s[1]) < 255){
                    if(Integer.parseInt(s[2]) < 255){
                        if(Integer.parseInt(s[3]) < 255){
                            b = true;
                        }
                    }
                }
            }
        }
        return b;
    }

    /**
     * 去掉IP字符串前后所有的空格
     *
     * @param ip IP地址
     * @return java.lang.String
     */
    public static String trimSpaces(String ip) {
        while(ip.startsWith(SPACE)){
            ip = ip.substring(1).trim();
        }
        while(ip.endsWith(SPACE)){
            ip = ip.substring(0, ip.length() - 1).trim();
        }
        return ip;
    }

}
