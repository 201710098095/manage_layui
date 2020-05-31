package com.aaa.biz;

import com.aaa.entity.MyUserInfo;

import java.util.List;

/**
 * @Author: 陈建
 * @Date: 2020/5/22 0022 15:47
 * @Version 1.0
 * 用户相关的业务方法
 */
public interface UserBiz {

    List<MyUserInfo> selectAllUser();
    MyUserInfo selectUserByUsername(String username);
    int insertSelective(MyUserInfo record);
    int delUserByID(List<String> ids);
    List<String> findPermissionListByUserId( Integer userId);

    int updateByPrimaryKeySelective(MyUserInfo record);

}
