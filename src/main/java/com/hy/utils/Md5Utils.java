package com.hy.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.UUID;

/**
 * @Author shy Boy
 * @Date 2021/5/11 - 05 - 11 - 17:04
 * @Description: com.hy.utils
 * @version: 1.0
 */
public class Md5Utils {
    public static final String NAME="SHA-256";//加密算法
    public static final int HASH_NUMBER=16;//加密次数
    /*
        password:密码
        salt：盐

     */
    public static SimpleHash md5Encrypt(String password,String salt){

        return new SimpleHash(NAME,password,salt,HASH_NUMBER);
    }

    public static void main(String[] args) {
        String salt = UUID.randomUUID().toString();
        System.out.println(salt);
        System.out.println(md5Encrypt("123456",salt));
    }

}
