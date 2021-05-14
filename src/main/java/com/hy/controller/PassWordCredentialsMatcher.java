package com.hy.controller;

import com.hy.pojo.Users;
import com.hy.utils.Md5Utils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * @Author shy Boy
 * @Date 2021/5/11 - 05 - 11 - 17:23
 * @Description: com.hy.controller
 * @version: 1.0
 */
public class PassWordCredentialsMatcher extends SimpleCredentialsMatcher {
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info){
        //登陆时的密码
       char[] pass =(char[]) token.getCredentials();
       //用户信息
        Users users = (Users) info.getPrincipals().getPrimaryPrincipal();
        //加密登录时的密码
        String passWord = String.valueOf(Md5Utils.md5Encrypt(String.valueOf(pass), users.getSalt()));
        return this.equals(passWord,users.getPassWord());
    }
}
