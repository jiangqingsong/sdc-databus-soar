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


    /**
     * 多包摘要初始化
     * @return
     */
    JSONObject HashInit(Map<String, String> param);

    /**
     * 多包摘要更新
     * @return
     */
    JSONObject HashUpdate(Map<String, String> param);
    /**
     * 单包MAC计算
     * @return
     */
    JSONObject Mac(Map<String, String> param);
    /**
     * 多包MAC结束
     * @return
     */
    JSONObject MacFinal(Map<String, String> param);
    /**
     * 多包MAC初始化
     * @return
     */
    JSONObject MacInit(Map<String, String> param);
    /**
     * 多包MAC更新
     * @return
     */
    JSONObject MacUpdate(Map<String, String> param);
    /**
     * 解析证书
     * @return
     */
    JSONObject ParseCert(Map<String, String> param);
    /**
     * 单包数字签名
     * @return
     */
    JSONObject SignData(Map<String, String> param);
    /**
     * 多包数字签名结束
     * @return
     */
    JSONObject SignDataFinal(Map<String, String> param);
    /**
     * 多包数字签名初始化
     * @return
     */
    JSONObject SignDataInit(Map<String, String> param);
    /**
     * 多包数字签名更新
     * @return
     */
    JSONObject SignDataUpdate(Map<String, String> param);
    /**
     * 单包消息签名
     * @return
     */
    JSONObject SignMessage(Map<String, String> param);
    /**
     * 多包消息签名结束
     * @return
     */
    JSONObject SignMessageFinal(Map<String, String> param);
    /**
     * 多包消息签名初始化
     * @return
     */
    JSONObject SignMessageInit(Map<String, String> param);
    /**
     * 多包消息签名更新
     * @return
     */
    JSONObject SignMessageUpdate(Map<String, String> param);
    /**
     * 对称解密
     * @return
     */
    JSONObject SymmetricDecrypt(Map<String, String> param);
    /**
     * 对称加密
     * @return
     */
    JSONObject SymmetricEncrypt(Map<String, String> param);
    /**
     * 验证证书有效性
     * @return
     */
    JSONObject ValidateCert(Map<String, String> param);
    /**
     * 单包验证数字签名
     * @return
     */
    JSONObject VerifySignedData(Map<String, String> param);
    /**
     * 多包验证数字签名结束
     * @return
     */
    JSONObject VerifySignedDataFinal(Map<String, String> param);
    /**
     * 多包验证数字签名初始化
     * @return
     */
    JSONObject VerifySignedDataInit(Map<String, String> param);
    /**
     * 多包验证数字签名更新
     * @return
     */
    JSONObject VerifySignedDataUpdate(Map<String, String> param);
    /**
     * 单包验证消息签名
     * @return
     */
    JSONObject VerifySignedMessage(Map<String, String> param);
    /**
     * 多包验证消息签名结束
     * @return
     */
    JSONObject VerifySignedMessageFinal(Map<String, String> param);
    /**
     * 多包验证消息签名初始化
     * @return
     */
    JSONObject VerifySignedMessageInit(Map<String, String> param);
    /**
     * 多包验证消息签名更新
     * @return
     */
    JSONObject VerifySignedMessageUpdate(Map<String, String> param);


}
