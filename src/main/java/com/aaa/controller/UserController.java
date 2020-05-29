package com.aaa.controller;

import com.aaa.biz.UserBiz;
import com.aaa.entity.LayUITable;
import com.aaa.entity.MyUserInfo;
import com.aaa.shiro.ShiroUtil;
import com.alibaba.fastjson.JSON;
import com.aaa.util.MyConstants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {
    @Autowired
    private UserBiz userBizImpl;

    @RequestMapping("/toShowUserLayui")
    public String toShowUserLayui(){
        return "user/showUserLayui";
    }

    @RequestMapping("/toIndex")
    public String toIndex(){
        return "user/index";
    }

    @RequestMapping("/showUserLayui")
    @ResponseBody
    public LayUITable showUserLayui(int page,int limit){
        //开始分页,第一个参数是当前第几页，第二个参数是一页显示多少行
        PageHelper.startPage(page,limit);
        //开始查询
        List<MyUserInfo> userInfoList = userBizImpl.selectAllUser();
        //结束分页,pageInfo封装了分页之后所有数据
        PageInfo<MyUserInfo> pageInfo = new PageInfo(userInfoList);
        LayUITable layUITable = new LayUITable();
        layUITable.setCode(0);
        layUITable.setMsg("返回消息");
        //设置分页之后的返回值
        layUITable.setCount(pageInfo.getTotal());
        layUITable.setData(pageInfo.getList());
        return layUITable;
    }
    //登录路径
    @RequestMapping("/toLogin")
    public String toLogin(){
        //退出登录
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
    //登录
    @RequestMapping("/login")
    public String login(String login_name,String password, Model model){
        System.out.println(login_name+password);
        Subject subject = SecurityUtils.getSubject();
        //要把输入的密码转换成加密的密码在传
        MyUserInfo myUserInfo2 = userBizImpl.selectUserByUsername(login_name);
        if(myUserInfo2==null) {
            model.addAttribute("message","用户名错误");
            return "login";
        }
        //获取盐值
        String salt = myUserInfo2.getSalt();
        //有了盐值就可以获取加密后的密码
        String saltPassword = ShiroUtil.encryptionBySalt(salt,password);
        //用户名和加密后的密码封装成token
        UsernamePasswordToken token= new UsernamePasswordToken(login_name,saltPassword);
        try {
            //调用doGetAuthenticationInfo开始认证
            subject.login(token);
        }catch (UnknownAccountException e){
            model.addAttribute("message","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("message","密码错误");
            return "login";
        }
        model.addAttribute("login_name",login_name);
        return "user/index";
    }
    //保存用户
    @RequestMapping("/saveUser")
    @ResponseBody
    public Object saveUser(MyUserInfo userInfo){
        int i = userBizImpl.insertSelective(userInfo);
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
     * 修改用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping("/editUser")
    @ResponseBody
    public Object editUser(MyUserInfo userInfo){
        int i = userBizImpl.updateByPrimaryKeySelective(userInfo);
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
     * 删除用户信息
     * @param ids
     * @return
     */
    @RequestMapping("/delUser")
    @ResponseBody
    public Object delUser( @RequestParam(value = "ids") String  ids){
        //将json字符串转换成list对象
        List<String> list= (List<String>) JSON.parse(ids);
        int i = userBizImpl.delUserByID(list);
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


}
