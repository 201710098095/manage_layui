package com.aaa.biz.impl;

import com.aaa.biz.DeptBiz;
import com.aaa.dao.DeptMapper;
import com.aaa.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeptBizImpl implements DeptBiz {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> selectAllDept() {
        return deptMapper.selectAllDept();
    }

    @Override
    public int deleteByPrimaryKey(Integer deptId) {
        return deptMapper.deleteByPrimaryKey(deptId);
    }

    @Override
    public int insert(Dept record) {
        return deptMapper.insert(record);
    }

    @Override
    public int insertSelective(Dept record) {
        return deptMapper.insertSelective(record);
    }

    @Override
    public Dept selectByPrimaryKey(Integer deptId) {
        return deptMapper.selectByPrimaryKey(deptId);
    }

    @Override
    public int updateByPrimaryKeySelective(Dept record) {
        return deptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Dept record) {
        return deptMapper.updateByPrimaryKey(record);
    }
}
