package com.broadtech.databus.soar.controller.key;


import com.alibaba.fastjson.JSONObject;
import com.broadtech.databus.soar.common.ResultUtil;
import com.broadtech.databus.soar.pojo.SoarEventTypeResult;
import com.broadtech.databus.soar.service.IAlarmTypeService;
import com.broadtech.databus.soar.service.ISignatureCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 告警事件类型
 * @author leo.j
 * @since 2022-03-19
 */
@RestController
@RequestMapping("/SignatureCheck")
public class SignatureCheckController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignatureCheckController.class);

    @Autowired
    private ISignatureCheckService service;

    /**
     * 非对称私钥解密
     * @return
     */
    @RequestMapping(value = "/AsymmetricDecrypt", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> asymmetricDecrypt(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.asymmetricDecrypt(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }

    /**
     * 非对称公钥加密
     * @return
     */
    @RequestMapping(value = "/AsymmetricEncrypt", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> asymmetricEncrypt(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.asymmetricEncrypt(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 数字信封解密
     * @return
     */
    @RequestMapping(value = "/DigitalEnvelopeDecrypt", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> DigitalEnvelopeDecrypt(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.digitalEnvelopeDecrypt(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 数字信封封装
     * @return
     */
    @RequestMapping(value = "/DigitalEnvelopeEncrypt", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> digitalEnvelopeEncrypt(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.digitalEnvelopeEncrypt(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 导出证书
     * @return
     */
    @RequestMapping(value = "/ExportCert", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> exportCert(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.exportCert(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 单包摘要计算
     * @return
     */
    @RequestMapping(value = "/Hash", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> hash(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.hash(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包摘要结束
     * @return
     */
    @RequestMapping(value = "/HashFinal", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> hashFinal(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.hashFinal(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包摘要初始化
     * @return
     */
    @RequestMapping(value = "/hashInit", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> hashInit(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.HashInit(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包摘要更新
     * @return
     */
    @RequestMapping(value = "/HashUpdate", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> HashUpdate(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.HashUpdate(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 单包MAC计算
     * @return
     */
    @RequestMapping(value = "/Mac", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> Mac(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.Mac(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包MAC结束
     * @return
     */
    @RequestMapping(value = "/MacFinal", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> MacFinal(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.MacFinal(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包MAC初始化
     * @return
     */
    @RequestMapping(value = "/MacInit", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> MacInit(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.MacInit(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包MAC更新
     * @return
     */
    @RequestMapping(value = "/MacUpdate", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> MacUpdate(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.MacUpdate(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 解析证书
     * @return
     */
    @RequestMapping(value = "/ParseCert", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> ParseCert(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.ParseCert(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 单包数字签名
     * @return
     */
    @RequestMapping(value = "/SignData", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> SignData(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.SignData(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包数字签名结束
     * @return
     */
    @RequestMapping(value = "/SignDataFinal", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> SignDataFinal(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.SignDataFinal(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包数字签名初始化
     * @return
     */
    @RequestMapping(value = "/SignDataInit", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> SignDataInit(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.SignDataInit(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包数字签名更新
     * @return
     */
    @RequestMapping(value = "/SignDataUpdate", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> SignDataUpdate(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.SignDataUpdate(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 单包消息签名
     * @return
     */
    @RequestMapping(value = "/SignMessage", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> SignMessage(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.SignMessage(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包消息签名结束
     * @return
     */
    @RequestMapping(value = "/SignMessageFinal", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> SignMessageFinal(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.SignMessageFinal(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包消息签名初始化
     * @return
     */
    @RequestMapping(value = "/SignMessageInit", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> SignMessageInit(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.SignMessageInit(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包消息签名更新
     * @return
     */
    @RequestMapping(value = "/SignMessageUpdate", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> SignMessageUpdate(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.SignMessageUpdate(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 对称解密
     * @return
     */
    @RequestMapping(value = "/SymmetricDecrypt", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> SymmetricDecrypt(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.SymmetricDecrypt(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 对称加密
     * @return
     */
    @RequestMapping(value = "/SymmetricEncrypt", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> SymmetricEncrypt(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.SymmetricEncrypt(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 验证证书有效性
     * @return
     */
    @RequestMapping(value = "/ValidateCert", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> ValidateCert(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.ValidateCert(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 单包验证数字签名
     * @return
     */
    @RequestMapping(value = "/VerifySignedData", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> VerifySignedData(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.VerifySignedData(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包验证数字签名结束
     * @return
     */
    @RequestMapping(value = "/VerifySignedDataFinal", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> VerifySignedDataFinal(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.VerifySignedDataFinal(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包验证数字签名初始化
     * @return
     */
    @RequestMapping(value = "/VerifySignedDataInit", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> VerifySignedDataInit(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.VerifySignedDataInit(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包验证数字签名更新
     * @return
     */
    @RequestMapping(value = "/VerifySignedDataUpdate", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> VerifySignedDataUpdate(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.VerifySignedDataUpdate(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 单包验证消息签名
     * @return
     */
    @RequestMapping(value = "/VerifySignedMessage", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> VerifySignedMessage(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.VerifySignedMessage(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包验证消息签名结束
     * @return
     */
    @RequestMapping(value = "/VerifySignedMessageFinal", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> VerifySignedMessageFinal(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.VerifySignedMessageFinal(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }
    /**
     * 多包验证消息签名初始化
     * @return
     */
    @RequestMapping(value = "/VerifySignedMessageInit", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> VerifySignedMessageInit(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.VerifySignedMessageInit(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }

    /**
     * 多包验证消息签名更新
     * @return
     */
    @RequestMapping(value = "/VerifySignedMessageUpdate", method = RequestMethod.POST)
    public ResponseEntity<List<SoarEventTypeResult>> VerifySignedMessageUpdate(@RequestBody Map<String, String> param){
        JSONObject jsonObject = service.VerifySignedMessageUpdate(param);
        return new ResponseEntity(ResultUtil.success(jsonObject), HttpStatus.OK);
    }


}
