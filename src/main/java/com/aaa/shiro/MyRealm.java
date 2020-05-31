package com.aaa.shiro;

import com.aaa.biz.UserBiz;
import com.aaa.biz.impl.UserBizImpl;
import com.aaa.entity.MyUserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
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
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取用户信息
        MyUserInfo myUserInfo = (MyUserInfo) SecurityUtils.getSubject().getPrincipal();
        //用户id
        int userid = myUserInfo.getUserid();

        System.out.println(userid);
        //获取用户信息,前提是在认证的时候将用户信息放入到Principal中
        List<String> perms = userBizImpl.findPermissionListByUserId(userid);
        System.out.println(perms);

        for(int i = 0;i < perms.size(); i ++){
            System.out.println(perms.get(i));
            simpleAuthorizationInfo.addStringPermission(perms.get(i));
//            simpleAuthorizationInfo.setRoles();
        }

//        List<String> permissions = new ArrayList<String>();
////        String perms="menu:edit";
        return simpleAuthorizationInfo;

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
        System.out.println(myUserInfo);

        if(myUserInfo==null){
            return null;
        }
////第二个参数是密码，数据库中的密码
        String sqlpassword=myUserInfo.getPassword();
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(myUserInfo,sqlpassword,getName());
        return simpleAuthenticationInfo;
    }
    public static void main(String[] args){
        System.out.println(UUID.randomUUID());
        System.out.println(UUID.randomUUID());
    }
}

