package com.broadtech.databus.soar.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.broadtech.databus.soar.common.RestTemplateUtil;
import com.broadtech.databus.soar.constans.KeySecurityConstant;
import com.broadtech.databus.soar.pojo.key.KeyPairPO;
import com.broadtech.databus.soar.service.IKeyManageService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * 秘钥管理
 */
public class KeyManageServiceImpl implements IKeyManageService {
    @Override
    public JSONObject keyMgrAppAdd(Map<String, String> param) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

        /**
         * AppInfo

         参数名称	参数说明	in	是否必须	数据类型	schema
         appAddr		body	false	string
         appName		body	false	string
         createTime		body	false	string(date-time)
         id		body	false	string
         keypair		body	false	array	Keypair
         Keypair

         参数名称	参数说明	in	是否必须	数据类型	schema
         appId		body	false	string
         certId		body	false	string
         createTime		body	false	string(date-time)
         data		body	false	KeypairData	KeypairData
         id		body	false	string
         institution		body	false	string
         keyType		body	false	string
         lastChange		body	false	string(date-time)
         notAfter		body	false	string(date-time)
         notAfterStr		body	false	string
         notBefore		body	false	string(date-time)
         notBeforeStr		body	false	string
         revokeTime		body	false	string(date-time)
         status		body	false	integer(int32)
         statusStr		body	false	string
         KeypairData

         参数名称	参数说明	in	是否必须	数据类型	schema
         id		body	false	string
         privateKey		body	false	string(byte)
         publicKey		body	false	string(byte)
         symKey		body	false	string(byte)
         */
        map.set("appAddr", param.get("appAddr"));
        map.set("appName", param.get("appName"));
        map.set("createTime", param.get("createTime"));
        map.set("id", param.get("id"));
        //set keypair

        KeyPairPO keyPair = new KeyPairPO();
        keyPair.setAppId(param.get("appId"));
        keyPair.setCertId(param.get("certId"));
        keyPair.setCreateTime(param.get("createTime"));
        keyPair.setCreateTime(param.get("createTime"));
        //todo set其余字段

        String url = KeySecurityConstant.URL_PREFIX + "/keyMgr/app/add";
        return RestTemplateUtil.post(map, url);
    }
}
