package com.broadtech.databus.soar.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 签名验签
 */
public interface ISignatureCheckService {

    /**
     * 多包摘要结束
     * @return
     */
    JSONObject hashInit(Map<String, String> param);

    /**
     * 非对称私钥解密
     * @return
     */
    JSONObject asymmetricDecrypt(Map<String, String> param);

    /**
     * 非对称公钥加密
     * @return
     */
    JSONObject asymmetricEncrypt(Map<String, String> param);

    /**
     * 数字信封解密
     * @return
     */
    JSONObject digitalEnvelopeDecrypt(Map<String, String> param);

    /**
     * 数字信封封装
     * @return
     */
    JSONObject digitalEnvelopeEncrypt(Map<String, String> param);

    /**
     * 导出证书
     * @return
     */
    JSONObject exportCert(Map<String, String> param);

    /**
     * 单包摘要计算
     * @return
     */
    JSONObject hash(Map<String, String> param);

    /**
     * 多包摘要结束
     * @return
     */
    JSONObject hashFinal(Map<String, String> param);




}
