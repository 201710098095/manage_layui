package com.aaa.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: 陈建
 * @Date: 2020/5/28 0028 11:44
 * @Version 1.0
 * 定义layui的树形菜单
 */

public class LayUiTree {

    private String title;
    private int id;
    private String field;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<LayUiTree> getChildren() {
        return children;
    }

    public void setChildren(List<LayUiTree> children) {
        this.children = children;
    }

    private boolean checked;
    private boolean spread;
    private String url;
    private List<LayUiTree> children;


}