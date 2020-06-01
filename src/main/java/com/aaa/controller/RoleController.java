package com.aaa.controller;

import com.aaa.biz.RoleBiz;
import com.aaa.entity.LayUITable;
import com.aaa.entity.Role;
import com.aaa.util.MyConstants;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 陈建
 * @Date: 2020/5/29 0029 16:27
 * @Version 1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleBiz roleBiz;

    @RequestMapping("/toShowRole")
    public String  toShowRole(){
        return "role/showRole";
    }

    @RequestMapping("/showRole")
    @ResponseBody
    public LayUITable showRoleLayui() {
        //开始查询
        List<Role> roleList = roleBiz.selectAllRole();
        LayUITable layUiTable = new LayUITable();
        layUiTable.setCode(0);
        layUiTable.setMsg("返回消息");
        layUiTable.setData(roleList);
        return layUiTable;
    }

    @RequestMapping("/insertRole")
    @ResponseBody
    public Object insertRole(Role role){
        int i = roleBiz.insertSelective(role);
        Map map= new HashMap();
        if(i>0){
            map.put("code", MyConstants.successCode);
            map.put("message",MyConstants.saveSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.saveFailMsg);
        }
        return map;
    }
    @RequestMapping("/delRole")
    @ResponseBody
    public Object delRole( @RequestParam(value = "ids") String  ids){
        //将json字符串转换成list对象
        List<String> list= (List<String>) JSON.parse(ids);
        int i = roleBiz.delRoleByID(list);
        Map map= new HashMap<>();
        if(i>0){
            map.put("code",MyConstants.successCode);
            map.put("message",MyConstants.delSuccessMsg);
        }else {
            map.put("code",MyConstants.failCode);
            map.put("message",MyConstants.delFailMsg);
        }
        return map;
    }
    @RequestMapping("/editRole")
    @ResponseBody
    public Object editUser(Role role){
        int i = roleBiz.updateByPrimaryKeySelective(role);
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
}
