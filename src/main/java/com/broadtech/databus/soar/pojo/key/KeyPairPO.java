package com.broadtech.databus.soar.pojo.key;

import lombok.Data;

@Data
public class KeyPairPO {
    /**
     * Keypair

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
     */
    private String appId;
    private String certId;
    private String createTime;
    private String data;
    private String id;
    private String institution;
    private String keyType;
    private String lastChange;
    private String notAfter;
    private String notAfterStr;
    private String notBefore;
    private String notBeforeStr;
    private String revokeTime;
    private Integer status;
    private String statusStr;
}
