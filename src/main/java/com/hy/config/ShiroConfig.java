package com.hy.config;

import jdk.internal.dynalink.DefaultBootstrapper;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author shy Boy
 * @Date 2021/5/10 - 05 - 10 - 11:27
 * @Description: com.hy.config
 * @version: 1.0
 */
@Configuration
public class ShiroConfig {

    @Bean(value = "shiro")
    public ShiroFilterFactoryBean srhiroFilter(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
        /*
        设置安全管理器
        anon：无须认证登录即可访问
        authc：必须认证才可以访问
        perms：该资源必须得到 资源权限才能访问
        role：该资源必须得到角色权限才可以访问
         */

        shiroFilterFactory.setSecurityManager(securityManager);
        Map<String,String> map = new LinkedHashMap();
        map.put("/login/user","anon");
        map.put("/js/*","anon");
        map.put("/img/*","anon");
        map.put("/layui/**","anon");
        map.put("/css/*","anon");
        map.put("/font/*","anon");
        //指定所有url权限
        map.put("/**","authc");
        shiroFilterFactory.setFilterChainDefinitionMap(map);
        //跳转登录页面
        shiroFilterFactory.setLoginUrl("/test/login");
        return shiroFilterFactory;
    }
    //SecurityManager它是shiro框架的核心
    @Bean
    public DefaultWebSecurityManager securityManager(MyRealms myRealms){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealms);
        //设置缓存
        defaultWebSecurityManager.setCacheManager(ehCacheManager());
        return defaultWebSecurityManager;
    }
    //Realms.Shiro与数据之间的桥梁
    @Bean
    public MyRealms myRealms(){
        return new MyRealms();
    }

    //开启授权管理注解扫描
    @Bean
    public AuthorizationAttributeSourceAdvisor advisor(MyRealms myRealms){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager(myRealms));
        return advisor;
    }
    //解决权限注解不生效的问题
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }
    //创建缓存对象
    @Bean
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehCacheManager;
    }
}
