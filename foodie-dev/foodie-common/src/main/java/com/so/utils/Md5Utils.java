package com.so.utils;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;

/**
 * MD5工具类
 * 
 * @author show
 * @date 2019/11/25 12:29
 */
public class Md5Utils {

    /**
     * 
     * @Title: MD5Utils.java
     * @Package com.imooc.utils
     * @Description: 对字符串进行md5加密
     */
    public static String getMd5Str(String strValue) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest(strValue.getBytes()));
    }

    public static void main(String[] args) {
        try {
            String md5 = getMd5Str("show");
            System.out.println(md5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
