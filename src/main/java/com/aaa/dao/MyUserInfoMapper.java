package com.aaa.dao;

import com.aaa.entity.MyUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MyUserInfoMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(MyUserInfo record);

    int insertSelective(MyUserInfo record);

    MyUserInfo selectByPrimaryKey(Integer userid);


    int updateByPrimaryKeySelective(MyUserInfo record);

    int updateByPrimaryKey(MyUserInfo record);

    int delUserByID(@Param("ids") List<String> ids);

    MyUserInfo selectUserByUsername(String username);
    List<MyUserInfo> selectAllUser();
    List<String> findPermissionListByUserId(Integer userId);

}