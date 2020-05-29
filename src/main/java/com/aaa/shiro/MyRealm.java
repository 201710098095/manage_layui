package com.aaa.shiro;

import com.aaa.biz.UserBiz;
import com.aaa.biz.impl.UserBizImpl;
import com.aaa.entity.MyUserInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @Author: 陈建
 * @Date: 2019/10/10 0010 14:05
 * @Version 1.0
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserBiz userBizImpl;

    /**
     *授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权开始了！");
        return null;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证开始了！");
        //开始校验用户名和密码
        //取出令牌token信息
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        //验证用户名
        MyUserInfo myUserInfo = userBizImpl.selectUserByUsername(usernamePasswordToken.getUsername());
        if(myUserInfo==null){
            return null;
        }
////第二个参数是密码，数据库中的密码
        String sqlpassword=myUserInfo.getPassword();
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(myUserInfo,sqlpassword,getName());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+simpleAuthenticationInfo);
        return simpleAuthenticationInfo;
    }
    public static void main(String[] args){
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());
    }
}

