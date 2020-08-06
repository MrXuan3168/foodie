package com.foodie.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 *
 * @author jamie
 */
public class Md5Utils {

    /**
     * 对字符串进行md5加密
     *
     * @param strValue 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String getMd5Str(String strValue) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        return Base64.encodeBase64String(md5.digest(strValue.getBytes()));
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String md5 = getMd5Str("jamie");
        System.out.println(md5);
    }

}
