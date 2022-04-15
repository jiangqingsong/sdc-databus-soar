package com.broadtech.databus.soar.entity;

import com.alibaba.fastjson.JSON;

/**
 * 接口调用结果封装类，包含操作结果码，操作结果文言，返回数据体
 * @param <T>
 */
public class Result<T> {

    private int code;
    private String msg;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString(){
        return JSON.toJSONString(this);
    }
}
