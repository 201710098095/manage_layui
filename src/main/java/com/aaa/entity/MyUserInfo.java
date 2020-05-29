package com.aaa.entity;

import lombok.Data;

@Data
public class MyUserInfo {
    private Integer user_id;
    private String login_name;
    private String password;
    private String salt;

    public Integer getUserid() {
        return user_id;
    }

    public void setUserid(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return login_name;
    }

    public void setUsername(String login_name) {
        this.login_name = login_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }



}