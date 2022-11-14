package com.broadtech.databus.soar.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 秘钥管理
 */
public interface IKeyManageService {
    /**
     * 应用新增
     * @param param
     * @return
     */
    JSONObject keyMgrAppAdd(Map<String, String> param);
}
