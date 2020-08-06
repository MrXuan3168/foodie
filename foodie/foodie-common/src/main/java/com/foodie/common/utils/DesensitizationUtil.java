package com.foodie.common.utils;

/**
 * 通用脱敏工具类 可用于： 用户名 手机号 邮箱 地址等
 *
 * @author jamie
 */
public class DesensitizationUtil {

    private static final int SIZE = 6;

    private static final String SYMBOL = "*";

    public static void main(String[] args) {
        String name = commonDisplay("jamie网");
        String mobile = commonDisplay("13900000000");
        String mail = commonDisplay("admin@163.com");
        String address = commonDisplay("北京大运河东路888号");

        System.out.println(name);
        System.out.println(mobile);
        System.out.println(mail);
        System.out.println(address);
    }

    /**
     * 通用脱敏方法
     *
     * @param value 需要脱敏的字符串
     * @return 脱敏后字符串
     */
    public static String commonDisplay(String value) {
        if(null == value || "".equals(value)){
            return value;
        }
        int len = value.length();
        int pamaOne = len / 2;
        int pamaTwo = pamaOne - 1;
        int pamaThree = len % 2;
        StringBuilder stringBuilder = new StringBuilder();
        // 长度等于1
        if(len == 1){
            return SYMBOL;
        }
        // 长度少于等于2
        if(len <= 2){
            stringBuilder.append(SYMBOL);
            stringBuilder.append(value.charAt(len - 1));
            return stringBuilder.toString();
        }
        if(pamaTwo <= 0){
            stringBuilder.append(value, 0, 1);
            stringBuilder.append(SYMBOL);
            stringBuilder.append(value, len - 1, len);
            return stringBuilder.toString();
        }
        if(pamaTwo >= SIZE / 2 && SIZE + 1 != len){
            int pamaFive = (len - SIZE) / 2;
            stringBuilder.append(value, 0, pamaFive);
            for(int i = 0; i < SIZE; i++){
                stringBuilder.append(SYMBOL);
            }
            if((pamaThree == 0 && SIZE / 2 == 0) || (pamaThree != 0 && SIZE % 2 != 0)){
                stringBuilder.append(value, len - pamaFive, len);
            }else{
                stringBuilder.append(value, len - (pamaFive + 1), len);
            }
            return stringBuilder.toString();
        }

        int pamaFour = len - 2;
        stringBuilder.append(value, 0, 1);
        for(int i = 0; i < pamaFour; i++){
            stringBuilder.append(SYMBOL);
        }
        stringBuilder.append(value, len - 1, len);
        return stringBuilder.toString();
    }

}
