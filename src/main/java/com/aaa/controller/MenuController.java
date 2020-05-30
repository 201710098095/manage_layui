package com.aaa.controller;

import com.aaa.biz.MenuBiz;
import com.aaa.entity.LayUITable;
import com.aaa.entity.LayUiTree;
import com.aaa.entity.Menu;
import com.aaa.entity.MyUserInfo;
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
 * @Date: 2020/5/28 0028 6:59
 * @Version 1.0
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuBiz menuBiz;
    @RequestMapping("/toShowMenu")
    public String toShowMenu() {
        return "menu/showMenu";
    }
    //tree
    @RequestMapping("/selectAllMenu")
    @ResponseBody
    public List<LayUiTree> selectAllMenu(){
        List<LayUiTree> layUiTrees = menuBiz.selectAllMenu();
        return layUiTrees;
    }

    //table
    @RequestMapping("/selectAllMenu2")
    @ResponseBody
    public LayUITable selectAllMenu2(){
        List<Menu> menus = menuBiz.selectAllMenu2();
        LayUITable layUITable = new LayUITable();
        layUITable.setCode(0);
        layUITable.setMsg("返回消息");
        layUITable.setCount(19);
        layUITable.setData(menus);
        return layUITable;
    }
    //添加菜单
    @RequestMapping("/saveMenu")
    @ResponseBody
    public Object saveUser(Menu menu){
        int i = menuBiz.insertSelective(menu);
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
    /**
     * 修改菜单信息
     * @param menu
     * @return
     */
    @RequestMapping("/editMenu")
    @ResponseBody
    public Object editUser(Menu menu){
        int i = menuBiz.updateByPrimaryKeySelective(menu);
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
    /**
     * 删除菜单信息
     * @param menuId
     * @return
     */
    @RequestMapping("/delMenu")
    @ResponseBody
    public Object delMenu( @RequestParam(value = "menuId") Integer  menuId){
        int i = menuBiz.deleteByPrimaryKey(menuId);
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