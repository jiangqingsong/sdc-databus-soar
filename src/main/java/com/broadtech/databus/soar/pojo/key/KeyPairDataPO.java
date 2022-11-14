package com.broadtech.databus.soar.pojo.key;

import lombok.Data;

@Data
public class KeyPairDataPO {
    /**
     * 数名称	参数说明	in	是否必须	数据类型	schema
     id		body	false	string
     privateKey		body	false	string(byte)
     publicKey		body	false	string(byte)
     symKey		body	false	string(byte)
     */
    private String id;
    private String privateKey;
    private String publicKey;
    private String symKey;
}
