package com.aaa.biz.impl;

import com.aaa.biz.UserBiz;
import com.aaa.dao.MyUserInfoMapper;
import com.aaa.entity.MyUserInfo;
import com.aaa.shiro.ShiroUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @Author: 陈建
 * @Date: 2020/5/22 0022 15:49
 * @Version 1.0
 *
 * 请求=》controller=>service(biz)=>dao=>DB
 */
@Service
public class UserBizImpl  implements UserBiz {
    @Autowired
    private MyUserInfoMapper myUserInfoMapper;
    @Override
    public List<MyUserInfo> selectAllUser() {
        return myUserInfoMapper.selectAllUser();
    }

    @Override
    public MyUserInfo selectUserByUsername(String username) {
        return myUserInfoMapper.selectUserByUsername(username);
    }
    @Override
    public int delUserByID(List<String> ids) {
        return myUserInfoMapper.delUserByID(ids) ;
    }

    @Override
    public int updateByPrimaryKeySelective(MyUserInfo record) {
        return myUserInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int insertSelective(MyUserInfo record) {
        //密码加密
        //获取一个随机盐值
        String salt= UUID.randomUUID().toString();
        //用户密码
        String message=record.getPassword();
        //获取加密后的密码
        String encryption=ShiroUtil.encryptionBySalt(salt,message);
        //加密后存入用户信息中
        record.setPassword(encryption);
        System.out.println(encryption);
        record.setSalt(salt);
        return myUserInfoMapper.insertSelective(record);
    }
}
