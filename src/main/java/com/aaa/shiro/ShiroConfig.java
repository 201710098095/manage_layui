package com.aaa.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: 陈建
 * @Date: 2019/10/10 0010 14:13
 * @Version 1.0
 * 创建三个bean
 */
@Configuration
public class ShiroConfig {
    /**
     * realm
     *
     */
    @Bean
    public MyRealm myRealm(){
        return new MyRealm();
    }
    /**
     * securityManager
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager= new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm());
        return defaultWebSecurityManager;
    }
    /**
     * shiroFilterFactorybean
     * 安全过滤器
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager());
/**
 * 认证过滤器的分类
 * anon:无需认证
 * authc:必须认证才能到达
 * user:使用rememberme的时候才用
 * perms：访问的资源需要某个权限才能到达
 * roles:访问的资源需要某个角色才能到达
 */
        Map<String, String> map = new LinkedHashMap<>();
//放行login
        map.put("/login","anon");
//过滤所有的请求
        map.put("/*","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
//修改登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        return shiroFilterFactoryBean;
    }
}
