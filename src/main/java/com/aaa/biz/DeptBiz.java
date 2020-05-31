package com.aaa.biz;

import com.aaa.entity.Dept;

import java.util.List;

public interface DeptBiz {

    List<Dept> selectAllDept();
    int deleteByPrimaryKey(Integer deptId);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
}
