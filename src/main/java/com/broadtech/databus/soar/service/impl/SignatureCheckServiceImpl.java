package com.broadtech.databus.soar.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.broadtech.databus.soar.common.RestTemplateUtil;
import com.broadtech.databus.soar.constans.KeySecurityConstant;
import com.broadtech.databus.soar.service.ISignatureCheckService;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * 签名验签
 */
public class SignatureCheckServiceImpl implements ISignatureCheckService {

    @Override
    public JSONObject hashInit(Map<String, String> param) {
        /**
         * 参数名称	参数说明	in	是否必须	数据类型	schema
         hashMethod		query	false	integer
         publickey		query	false	string
         userID		query	false	string
         userIDLen		query	false	integer
         */
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        //接口文档中是否必须为否的，不需要给值
        /*map.set("hashMethod", Integer.valueOf(param.get("hashMethod")));
        map.set("publickey", param.get("publickey"));
        map.set("userID", param.get("userID"));
        map.set("userIDLen", Integer.valueOf(param.get("userIDLen")));*/
        String url = KeySecurityConstant.URL_PREFIX + "/HashInit";
        return RestTemplateUtil.post(map, url);
    }

    @Override
    public JSONObject asymmetricDecrypt(Map<String, String> param) {
        /**
         * 参数名称	参数说明	in	是否必须	数据类型	schema
         encData		query	false	string
         encDataLen		query	false	integer
         encryptMethod		query	false	integer
         keyIndex		query	false	integer
         keyValue		query	false	string
         */
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        String url = KeySecurityConstant.URL_PREFIX + "/AsymmetricDecrypt";
        return RestTemplateUtil.post(map, url);
    }

    @Override
    public JSONObject asymmetricEncrypt(Map<String, String> param) {
        /**
         * 参数名称	参数说明	in	是否必须	数据类型	schema
         encryptMethod		query	false	integer
         inData		query	false	string
         inDataLen		query	false	integer
         pubKey		query	false	string
         */
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        String url = KeySecurityConstant.URL_PREFIX + "/AsymmetricEncrypt";
        return RestTemplateUtil.post(map, url);
    }

    @Override
    public JSONObject digitalEnvelopeDecrypt(Map<String, String> param) {
        /**
         * 请求参数：

         参数名称	参数说明	in	是否必须	数据类型	schema
         asyEncryptMethod		query	false	integer
         envelope		query	false	string
         envelopeLen		query	false	integer
         keyIndex		query	false	integer
         keyValue		query	false	string
         synEncryptMethod		query	false	integer
         响应示例:

         {
         "data": "",
         "respValue": 0
         }
         */
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        String url = KeySecurityConstant.URL_PREFIX + "/DigitalEnvelopeDecrypt";
        return RestTemplateUtil.post(map, url);
    }

    @Override
    public JSONObject digitalEnvelopeEncrypt(Map<String, String> param) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        String url = KeySecurityConstant.URL_PREFIX + "/DigitalEnvelopeEncrypt";
        return RestTemplateUtil.post(map, url);
    }

    @Override
    public JSONObject exportCert(Map<String, String> param) {
        /**
         * 请求参数：

         参数名称	参数说明	in	是否必须	数据类型	schema
         identification		query	false	string
         响应示例:

         {
         "cert": "",
         "respValue": 0
         }
         */
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        String url = KeySecurityConstant.URL_PREFIX + "/ExportCert";
        return RestTemplateUtil.post(map, url);
    }

    @Override
    public JSONObject hash(Map<String, String> param) {
        /**
         * 请求参数：

         参数名称	参数说明	in	是否必须	数据类型	schema
         hashMethod		query	false	integer
         inData		query	false	string
         inDataLen		query	false	integer
         */
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        String url = KeySecurityConstant.URL_PREFIX + "/Hash";
        return RestTemplateUtil.post(map, url);
    }

    @Override
    public JSONObject hashFinal(Map<String, String> param) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        String url = KeySecurityConstant.URL_PREFIX + "/HashFinal";
        return RestTemplateUtil.post(map, url);
    }

    @Override
    public JSONObject HashInit(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject HashUpdate(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject Mac(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject MacFinal(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject MacInit(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject MacUpdate(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject ParseCert(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject SignData(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject SignDataFinal(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject SignDataInit(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject SignDataUpdate(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject SignMessage(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject SignMessageFinal(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject SignMessageInit(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject SignMessageUpdate(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject SymmetricDecrypt(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject SymmetricEncrypt(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject ValidateCert(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject VerifySignedData(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject VerifySignedDataFinal(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject VerifySignedDataInit(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject VerifySignedDataUpdate(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject VerifySignedMessage(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject VerifySignedMessageFinal(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject VerifySignedMessageInit(Map<String, String> param) {
        return null;
    }

    @Override
    public JSONObject VerifySignedMessageUpdate(Map<String, String> param) {
        return null;
    }


}
