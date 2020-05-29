package com.aaa.entity;

import lombok.Data;

import java.util.List;

/**
 * @Author: 陈建
 * @Date: 2020/5/22 0022 16:55
 * @Version 1.0
 */
@Data
public class LayUITable {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    private int code;
    private String msg;
    private long count;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    private List<?> data;
}
