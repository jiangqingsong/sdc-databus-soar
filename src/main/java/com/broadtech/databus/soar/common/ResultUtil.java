package com.broadtech.databus.soar.common;

import com.alibaba.fastjson.JSON;
import com.broadtech.databus.soar.entity.Result;
import com.broadtech.databus.soar.enums.ResultEnum;


public class ResultUtil {

    /**
     * 返回成功，传入返回体具体出參
     * @param object
     * @return
     */
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum._SUCCESS.getEnumCode());
        result.setMsg(ResultEnum._SUCCESS.getEnumMsg());
        result.setData(object);
        return result;
    }

    public static String successStr(Object object){
        Result result = new Result();
        result.setCode(ResultEnum._SUCCESS.getEnumCode());
        result.setMsg(ResultEnum._SUCCESS.getEnumMsg());
        result.setData(object);
        return JSON.toJSONString(result);
    }

    /**
     * 返回错误信息
     * @param exceptionEnum
     * @return
     */
    public static Result error(ResultEnum exceptionEnum){
        Result result = new Result();
        result.setCode(exceptionEnum.getEnumCode());
        result.setMsg(exceptionEnum.getEnumMsg());
        result.setData(null);
        return result;
    }

    public static String errorStr(ResultEnum exceptionEnum){
        Result result = new Result();
        result.setCode(exceptionEnum.getEnumCode());
        result.setMsg(exceptionEnum.getEnumMsg());
        result.setData(null);
        return JSON.toJSONString(result);
    }

    /**
     * 返回异常信息
     * @param exceptionMsg
     * @return
     */
    public static Result exception(String exceptionMsg){
        Result result = new Result();
        result.setCode(ResultEnum._SERVER_EXCEPTION.getEnumCode());
        result.setMsg(ResultEnum._SERVER_EXCEPTION.getEnumMsg() + ":" + exceptionMsg);
        result.setData(null);
        return result;
    }

    public static String exceptionStr(String exceptionMsg){
        Result result = new Result();
        result.setCode(ResultEnum._SERVER_EXCEPTION.getEnumCode());
        result.setMsg(ResultEnum._SERVER_EXCEPTION.getEnumMsg() + ":" + exceptionMsg);
        result.setData(null);
        return JSON.toJSONString(result);
    }

}
