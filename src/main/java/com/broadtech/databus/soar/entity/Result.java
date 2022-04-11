package com.broadtech.databus.soar.entity;

import com.alibaba.fastjson.JSON;

/**
 * 接口调用结果封装类，包含操作结果码，操作结果文言，返回数据体
 * @param <T>
 */
public class Result<T> {

    private int resultCode;
    private String resultMsg;
    private T data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
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
