package com.hy.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hy.controller.PassWordCredentialsMatcher;
import com.hy.mapper.UsersMapper;
import com.hy.pojo.Users;
import com.hy.service.StudentService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author shy Boy
 * @Date 2021/5/10 - 05 - 10 - 11:55
 * @Description: com.hy.config
 * @version: 1.0
 */
public class MyRealms extends AuthorizingRealm {
    @Autowired
    private UsersMapper usersMapper ;

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权验证");
        //获取用户信息
        Users users = (Users) principalCollection.getPrimaryPrincipal();
        System.out.println(users.getUserName());

        //权限查询
        usersMapper.selectList(null);
        //当前用户权限标识
        Set set = new HashSet();
        set.add("login:user");
        //当前角色标识
        HashSet set1 = new HashSet();
        set1.add("login:user");

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //设置用户权限标识
        simpleAuthorizationInfo.setStringPermissions(set);
        //设置用户权限标识
        simpleAuthorizationInfo.setRoles(set1);
        return simpleAuthorizationInfo;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行登录验证");
        //获取登录信息
        UsernamePasswordToken user = (UsernamePasswordToken)authenticationToken ;
        //验证用户是否存在
        QueryWrapper<Users> queryWrapper = new QueryWrapper();
        queryWrapper.eq("userName",user.getUsername());
        Users users = usersMapper.selectOne(queryWrapper);

        //用户不存在
        if (users == null) {
            return null;
        }
        /*
            users:缓存用户信息
            user.getPassword()：密码验证
         */

        return new SimpleAuthenticationInfo(users,users.getPassWord(),"");
    }

    //自定义密码验证
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher){
        super.setCredentialsMatcher(new PassWordCredentialsMatcher());
    }
}
