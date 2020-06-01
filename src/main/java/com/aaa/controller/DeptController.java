package com.aaa.controller;

import com.aaa.biz.DeptBiz;
import com.aaa.entity.Dept;
import com.aaa.entity.LayUITable;

import com.aaa.util.MyConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptBiz deptBiz;
    @RequestMapping("/toShowDept")
    public String toShowUserLayui(){
        return "dept/dept";
    }

    @RequestMapping("/showAllDept")
    @ResponseBody
    public LayUITable selectAllDept(){
        List<Dept> depts = deptBiz.selectAllDept();
        LayUITable layUITable = new LayUITable();
        layUITable.setCode(0);
        layUITable.setMsg("返回消息");
        layUITable.setData(depts);
        return layUITable;
    }
    @RequestMapping("/insertDept")
    @ResponseBody
    public Object insertDept(Dept dept){

        int i = deptBiz.insertSelective(dept);
        Map map = new HashMap<>();
        if(i>0){
            map.put("code", MyConstants.successCode);
            map.put("message", MyConstants.saveSuccessMsg);
        }else {
            map.put("code", MyConstants.failCode);
            map.put("message", MyConstants.saveFailMsg);
        }
        return map;
    }
    @RequestMapping("/updateDept")
    @ResponseBody
    public Object updateDept(Dept dept){
        int i = deptBiz.updateByPrimaryKeySelective(dept);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.editSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.editFailMsg);
        }
        return map;
    }
    @RequestMapping("/delDept")
    @ResponseBody
    public Object delMenu( @RequestParam(value = "deptId") Integer  deptId){
        System.out.println(deptId);
        int i = deptBiz.deleteByPrimaryKey(deptId);
        System.out.println(i);
        System.out.println("-----------------------");
        Map map= new HashMap<>();
        if(i>0){
            map.put("code", MyConstants.successCode);
            map.put("message",MyConstants.delSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.delFailMsg);
        }
        return map;
    }

}

